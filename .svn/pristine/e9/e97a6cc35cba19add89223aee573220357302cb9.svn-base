package ma.dev.sowondejong.activity;

import ma.dev.sowondejong.R;
import ma.dev.sowondejong.view.BackTabFragment;
import ma.dev.sowondejong.view.FragmentController;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class InfoActivity extends SherlockFragmentActivity {

	private TextView tvInfo, tvLink;
	private ImageView ivDnd;
	private String info = "'소원대종'은 한국의 전통 종소리를 들으며 소원을 기원하는 앱입니다. " +
			"무려 1200년 전 만들어진 국보 제29호 에밀레종(성덕대왕 신종)을 최초로 디지털로 복원해 그 깊고 웅장한 소리를 모바일에서 들을 수 있습니다. " +
			"힘들고 지칠 때, 국가의 안녕을 기원할 때 심금을 울리는 에밀레종을 한 번씩 울려주세요. 간절하게 바라면 소원이 이루어집니다." + "\n\n" 
			+ "종 소리와 함께 메시징 기능을 추가했습니다. 가족, 친지, 친구, 동료, 후원자에게 소원을 빌어주세요. " +
			"생일 축하, 승진 축하, 수능 대박 기원, 선거 승리 기원, 새해 맞이, 명절, 크리스마스 및 각종 경축일, 그리고 중요한 행사를 앞두고 응원 메시지를 보내세요. " +
			"카카오톡을 통해 종 소리와 메시지를 함께 보낼 수 있습니다. 마음을 정화시켜주는 소원대종의 종 소리가 여러분의 앞날을 밝혀줄 것입니다.";
	private int touchCount;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_info);

		tvInfo = (TextView)findViewById(R.id.tv_info);

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		tvInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				++touchCount;

				switch(touchCount) {
				case 10 :
					Toast.makeText(InfoActivity.this, "ㅋㅋㅋㅋㅋㅋㅋ",Toast.LENGTH_LONG).show();
					break;
				case 20 :
					Toast.makeText(InfoActivity.this, "당신은 이스터에그를 발견했습니다.",Toast.LENGTH_LONG).show();
					break;
				case 30 :
					AlertDialog.Builder alt_bld = new AlertDialog.Builder(InfoActivity.this);
				    alt_bld.setMessage("소프트웨어 공학 연구원 마경욱(shwarzes89@gmail.com)\n"
				    		+ "버그 발견하면 메일로 욕써주세요 ㅋ 로그 보내주면 더 좋고").setCancelable(
				        false).setPositiveButton("Yes",
				        new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            // Action for 'Yes' Button
				        }
				        }).setNegativeButton("No",
				        new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            // Action for 'NO' Button
				            dialog.cancel();
				        }
				        });
				    AlertDialog alert = alt_bld.create();
				    // Title for AlertDialog
				    alert.setTitle("개발자 정보 ㅋ");
				    // Icon for AlertDialog
				    alert.setIcon(R.drawable.launcher_icon);
				    alert.show();
				    touchCount = 0;
					break;
				}


			}
		});

		BackTabFragment backFrag = new BackTabFragment(MainActivity.class, R.drawable.info_title );

		FragmentController.getInstance().setFragManager(getSupportFragmentManager());

		FragmentController.getInstance().addFragment(R.id.frg_info, backFrag);

		tvInfo.setText(info);

		Uri uri = Uri.parse("http://dndmedia.co.kr/");
		final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		intent.addCategory(Intent.CATEGORY_BROWSABLE);
		
		
		tvLink = (TextView)findViewById(R.id.tv_hompi_link);
		tvLink.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(intent);
			}
		}); 
		
		ivDnd = (ImageView)findViewById(R.id.iv_info_logo);
		ivDnd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(intent);			
			}
		});
		
	}

	@Override
	public void onBackPressed() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}


}
