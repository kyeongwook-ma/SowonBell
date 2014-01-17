package ma.dev.sowondejong.activity;

import ma.dev.sowondejong.R;
import ma.dev.sowondejong.network.NetworkController;
import ma.dev.sowondejong.network.NetworkRequest;
import ma.dev.sowondejong.network.NetworkRequest.OnProcessCompletedListener;
import ma.dev.sowondejong.network.RegisterDeviceRequest;
import ma.dev.sowondejong.util.LauncherUtil;
import ma.dev.sowondejong.util.SharedPreferenceUtil;
import ma.dev.sowondejong.util.SowonAccountManager;
import ma.dev.sowondejong.util.StringUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);
		// getPhraseFromServer();
		registerGCM();

		LauncherUtil.go(this, MainActivity.class);
		// start Facebook Login

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}

	private void registerGCM() {


		if( SowonAccountManager.getUserId().equals("") ) {

			GCMRegistrar.checkDevice(this);
			GCMRegistrar.checkManifest(this);
			final String regId = GCMRegistrar.getRegistrationId(this);

			if(StringUtil.isEmptyString(regId)) {
				GCMRegistrar.register(this, "790029220762");		
			} 
			
			SowonAccountManager.createAccount(StringUtil.getMyPhoneNumber(this));

			RegisterDeviceRequest regRequest = new RegisterDeviceRequest(
					SowonAccountManager.getUserId());

			NetworkController.getInstance().enqueue(regRequest);

			regRequest.setOnCompleteListener(new OnProcessCompletedListener() {

				@Override
				public void onCompleted(final NetworkRequest request) {

					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							String result = (String) request.getResult();

							if(StringUtil.isEmptyString(result)) return ;

							if(!result.equals("ok-registered")) 
								Toast.makeText(getBaseContext(), "등록과정에 문제가 있습니다.\n"
										+ "문제가 지속되면 shwarzes89@gmail.com 으로 문의주십시오.", 
										Toast.LENGTH_LONG).show();
						}
					});


				}
			});
		}

	}

	/*private void getPhraseFromServer() {

		PhraseGetRequest phraseRequset = new PhraseGetRequest();
		NetworkController.getInstance().enqueue(phraseRequset);

		phraseRequset.setOnCompleteListener(new OnProcessCompletedListener() {

			@Override
			public void onCompleted(NetworkRequest request) {

				for ( int i = 0; i < phrases.size(); ++i ) {

					String phrase = phrases.get(i);
					ContentValues cv = new ContentValues();
					cv.put( PhraseDBScheme.COLUMN_PHRASE, phrase);

					if( DBHelper.getInstance().update( PhraseDBScheme.TABLE_NAME, cv, 
							PhraseDBScheme.COLUMN_PHRASE + " = ?", new String[] { phrase } ) == 0 ) { 

						DBHelper.getInstance().insert(PhraseDBScheme.TABLE_NAME, cv);
					}
				}
			}
		});*/
}

