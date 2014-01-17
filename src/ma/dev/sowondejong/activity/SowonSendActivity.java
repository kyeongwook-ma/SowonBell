package ma.dev.sowondejong.activity;

import java.io.File;

import ma.dev.sowondejong.R;
import ma.dev.sowondejong.data.Img;
import ma.dev.sowondejong.data.SowonMessage;
import ma.dev.sowondejong.db.DBHelper;
import ma.dev.sowondejong.db.SowonDB.SowonDBScheme;
import ma.dev.sowondejong.network.NetworkController;
import ma.dev.sowondejong.network.NetworkRequest;
import ma.dev.sowondejong.network.NetworkRequest.OnProcessCompletedListener;
import ma.dev.sowondejong.network.UploadSowonRequest;
import ma.dev.sowondejong.util.ImageUtil;
import ma.dev.sowondejong.util.KakaoLink;
import ma.dev.sowondejong.util.SMSUtil;
import ma.dev.sowondejong.util.SowonAccountManager;
import ma.dev.sowondejong.util.StringUtil;
import ma.dev.sowondejong.view.PhraseRowView.OnPhraseClickListener;
import ma.dev.sowondejong.view.PhraseSelectDialog;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class SowonSendActivity extends SherlockFragmentActivity implements OnClickListener{


	private static final int PICK_CONTACT = 0;
	private static final int PICK_FROM_CAMERA = 1;
	private static final int PICK_FROM_FILE = 2;

	public enum SEND_MODE { SMS, KAKAO, FACEBOOK };
	private SEND_MODE sendMode;
	private ImageView ivJong, ivSendImg;
	private String userId, message, to, phoneNumber, name, path = "";
	private TextView tvName, tvReceived;
	private EditText edtContents;
	private ImageView ivSend , ivCamera, ivGallery, ivPhrase;
	private Context mContext = this;
	private RelativeLayout sendRelativeLayout;
	private BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;
	private Uri mImageCaptureUri = null;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_send);

		ivJong = (ImageView)findViewById(R.id.iv_send_jong);
		ivJong.setAlpha(50);

		tvName = (TextView)findViewById(R.id.tv_send_popup_to);
		tvName.setOnClickListener(this);

		tvReceived = (TextView)findViewById(R.id.tv_received_person);

		edtContents = (EditText)findViewById(R.id.edt_send_sowon_contents);

		edtContents.requestFocus();
		edtContents.setSelection(0);
		getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE); 


		ivSend = (ImageView)findViewById(R.id.iv_send);
		ivSend.setOnClickListener(this);


		ivCamera = (ImageView)findViewById(R.id.iv_send_camera);
		ivCamera.setOnClickListener(this);

		ivGallery = (ImageView)findViewById(R.id.iv_send_gallery);
		ivGallery.setOnClickListener(this);

		ivPhrase = (ImageView)findViewById(R.id.iv_send_phrase);
		ivPhrase.setOnClickListener(this);

		ivSendImg = (ImageView)findViewById(R.id.iv_send_img);

		sendRelativeLayout = (RelativeLayout)findViewById(R.id.rl_send_tab_offset);

		// getIntentFromDialog();		
		sendMode = SEND_MODE.KAKAO;


	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		AnimationSet animationSet = new AnimationSet(true);
		int offSet = sendRelativeLayout.getHeight();

		RotateAnimation r = new RotateAnimation( -10f, 8f, ivJong.getWidth()/2, offSet ); // HERE 
		r.setRepeatMode(Animation.REVERSE);
		r.setRepeatCount(Animation.INFINITE);
		r.setDuration(3000);
		r.setFillAfter(true); //HERE
		animationSet.addAnimation(r);

		ivJong.setAnimation(animationSet);

	}

	private void getIntentFromDialog() {

		Intent i = getIntent();

		String mode = i.getStringExtra("mode");

		if(mode.equals("KAKAO")) {
			tvReceived.setText("");
			sendMode = SEND_MODE.KAKAO;
		} else if(mode.equals("SMS")) {
			sendMode = SEND_MODE.SMS;
			i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			startActivityForResult(i, 0);			
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		

		
		if(resultCode == RESULT_OK){
			if(requestCode== PICK_FROM_CAMERA)  {

				try{

					if(data != null) {
						mImageCaptureUri =  data.getData();
					} 
										
					Cursor cur = getContentResolver().query(mImageCaptureUri, null, null, null, null);
										
					if( cur == null ) {
						path = mImageCaptureUri.getPath();
						ivSendImg.setImageURI(mImageCaptureUri);
						return;
					}
					

					if(cur.moveToNext()) {
						path = cur.getString(cur.getColumnIndex(MediaStore.MediaColumns.DATA));
						mImageCaptureUri = Uri.fromFile(new File(path));
					} 

				} catch(NullPointerException e) {}
			}


			else if(requestCode == PICK_FROM_FILE ){

				try {
					mImageCaptureUri = data.getData();
					ivSendImg.setImageURI(mImageCaptureUri);

				} catch(NullPointerException e) { e.printStackTrace(); }

			}
			else if(requestCode == PICK_CONTACT ) {

				try {
					Uri contactData = data.getData();
					Cursor c = managedQuery(contactData, null, null, null, null);

					if (c.moveToFirst()) {
						String id =   
								c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

						String hasPhone =
								c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

						if (hasPhone.equalsIgnoreCase("1")) {
							Cursor phones = getContentResolver().query( 
									ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
									ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id, 
									null, null);
							phones.moveToFirst();
							phoneNumber = phones.getString(phones.getColumnIndex("data1"));
							name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


							tvName.setText(name);

						}

					}
				} catch(NullPointerException e) {} 
			}
		}
	}

	@Override
	public void onClick(View v) {


		switch(v.getId()) {

		case R.id.iv_send :

			userId = SowonAccountManager.getUserId();
			message = edtContents.getText().toString();
			
			if(StringUtil.isEmptyString(phoneNumber)) {
				to = userId;
			} else {
				to = StringUtil.getPhoneSHA(phoneNumber);
			}
			
			if(StringUtil.isEmptyString(message)) {
				Toast.makeText(mContext, "내용을 입력해 주세요", Toast.LENGTH_SHORT).show();
				return;
			}			

			SowonMessage msg = new SowonMessage(
					userId, 
					userId, 
					to,
					message, 
					sendMode.name(), 
					StringUtil.getDateWithStrForm(), 
					false);

			try {

				BitmapDrawable bd = (BitmapDrawable) ivSendImg.getDrawable();
				Bitmap origin = bd.getBitmap();
				Bitmap resized = ImageUtil.resizeBitmapImage(origin, 400);

				byte[] imgByte = ImageUtil.bitmapToByteArray(resized);

				Img img = new Img(path, Base64.encodeToString(imgByte, Base64.DEFAULT));
				msg.setImg(img);

			} catch(NullPointerException e) {}
			saveMySowonToDB(msg);
			uploadSowon(msg);


			break;

		case R.id.iv_send_camera :
			Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

			mImageCaptureUri = Uri.fromFile(new File(Environment
					.getExternalStorageDirectory(), "tmp_avatar_"
							+ String.valueOf(System.currentTimeMillis())
							+ ".jpg"));

			camIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
					mImageCaptureUri);

			try {
				camIntent.putExtra("return-data", true);
				startActivityForResult(camIntent, PICK_FROM_CAMERA);

			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;

		case R.id.iv_send_gallery :

			Intent gallIntent = new Intent();

			gallIntent.setType("image/*");
			gallIntent.setAction(Intent.ACTION_GET_CONTENT);

			startActivityForResult(Intent.createChooser(gallIntent,
					"Complete action using"), PICK_FROM_FILE);

			break;

		case R.id.iv_send_phrase :
			PhraseSelectDialog dlg = new PhraseSelectDialog();
			dlg.setOnPhraseClickListener(new OnPhraseClickListener() {

				@Override
				public void onPhraseClick(String mData) {

					if(StringUtil.isEmptyString(edtContents.getText().toString()))
						edtContents.setText(mData);
					else
						edtContents.append("\n" + mData );
				}
			});

			dlg.show(getSupportFragmentManager(), "phrase_select");			

			break;

		case R.id.tv_send_popup_to :
			Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			startActivityForResult(i, 0);			
			break;

		}

	}

	private void uploadSowon(SowonMessage msg) {

		UploadSowonRequest uploadSowon = new UploadSowonRequest(msg);

		NetworkController.getInstance().enqueue(uploadSowon); 

		uploadSowon.setOnCompleteListener(new OnProcessCompletedListener() {

			@Override
			public void onCompleted(final NetworkRequest request) {

				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						String url = (String) request.getResult();

						if(StringUtil.isEmptyString(url));
						
						
						
						switch (sendMode) {
						case KAKAO:
							sendKakao(message, url);
							break;
						case SMS :
							SMSUtil.sendSMS(SowonSendActivity.this, phoneNumber, message, url);
							break; 

						default:
							break;
						}
					}
				});
			}
		});


	}

	private void saveMySowonToDB(SowonMessage msg) {
		ContentValues cv = new ContentValues();

		try {
			cv.put(SowonDBScheme.COLUMN_FROM, msg.getFrom());
			cv.put(SowonDBScheme.COLUMN_TO, msg.getTo());
			cv.put(SowonDBScheme.COLUMN_DATE, msg.getDate());
			cv.put(SowonDBScheme.COLUMN_SEND_TYPE, msg.getType());
			cv.put(SowonDBScheme.COLUMN_MESSAGE, msg.getMessage());
			cv.put(SowonDBScheme.COLUMN_IMG_PATH, msg.getImg().getName());

		} catch(NullPointerException e) { cv.put(SowonDBScheme.COLUMN_IMG_PATH, ""); } 
		finally { 

			DBHelper.getInstance().insert(SowonDBScheme.TABLE_NAME, cv);
		}
	}

	private void sendKakao(String message, String url) {

		KakaoLink kakaoLink = KakaoLink.getLink(getApplicationContext());

		// check, intent is available.
		if (!kakaoLink.isAvailableIntent())
			return;

		/**
		 * @param activity
		 * @param url
		 * @param message
		 * @param appId
		 * @param appVer
		 * @param appName
		 * @param encoding
		 */
		try {
			kakaoLink.openKakaoLink(this, 
					url, 
					message, 
					getPackageName(), 
					getPackageManager().getPackageInfo(getPackageName(), 0).versionName, 
					"소원대종"
					, 
					"UTF-8");
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void backToMainActivity() {
		Intent i = new Intent(SowonSendActivity.this, MainActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
		finish();
	}

	@Override
	protected void onResume() {
		super.onResume();

		smsSentReceiver=new BroadcastReceiver() {

			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(mContext, "문자메세지를 성공적으로 발송하였습니다.", 
							Toast.LENGTH_SHORT).show();	     
					backToMainActivity();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(mContext, "문자메세지 발송을 실패하였습니다.", 
							Toast.LENGTH_SHORT).show();	
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getBaseContext(), "서비스가 불가합니다.", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(getBaseContext(), "Null PDU", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(getBaseContext(), "개통지역에서 벗어났습니다.", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;


				}


			}
		};
		smsDeliveredReceiver=new BroadcastReceiver() {

			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch(getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "문자가 전송되었습니다.", Toast.LENGTH_SHORT).show();
					backToMainActivity();

					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(getBaseContext(), "전송 실패하였습니다.", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		};
		registerReceiver(smsSentReceiver, new IntentFilter("SMS_SENT"));
		registerReceiver(smsDeliveredReceiver, new IntentFilter("SMS_DELIVERED"));
	}

	@Override
	public void onPause() {
		super.onPause();
		unregisterReceiver(smsSentReceiver);
		unregisterReceiver(smsDeliveredReceiver);
	}

}




