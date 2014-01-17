package ma.dev.sowondejong;

import java.util.Iterator;

import ma.dev.sowondejong.network.NetworkController;
import ma.dev.sowondejong.network.NetworkRequest;
import ma.dev.sowondejong.network.RegisterDeviceRequest;
import ma.dev.sowondejong.network.NetworkRequest.OnProcessCompletedListener;
import ma.dev.sowondejong.util.SharedPreferenceUtil;
import ma.dev.sowondejong.util.SowonAccountManager;
import ma.dev.sowondejong.util.SowonDejonApp;
import ma.dev.sowondejong.util.StringUtil;

import com.google.android.gcm.GCMBaseIntentService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


public class GCMIntentService extends GCMBaseIntentService {
	private static final String tag = "GCMIntentService";
	private static final String PROJECT_ID = "790029220762";
	//구글 api 페이지 주소 [https://code.google.com/apis/console/#project:긴 번호]
	//#project: 이후의 숫자가 위의 PROJECT_ID 값에 해당한다

	//public 기본 생성자를 무조건 만들어야 한다.
	public GCMIntentService(){ this(PROJECT_ID); }

	public GCMIntentService(String project_id) { super(project_id); }

	/** 푸시로 받은 메시지 */
	@Override
	protected void onMessage(Context context, Intent intent) {
		Bundle b = intent.getExtras();

		Iterator<String> iterator = b.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			String value = b.get(key).toString();
			Log.d(tag, "onMessage. "+key+" : "+value);
		}
	}

	/**에러 발생시*/
	@Override
	protected void on_error(Context context, String errorId) {
		Log.d(tag, "on_error. errorId : "+errorId);
	}

	/**단말에서 GCM 서비스 등록 했을 때 등록 id를 받는다*/
	@Override
	protected void onRegistered(Context context, String regId) {
		Log.d(tag, "onRegistered. regId : "+regId);
	}

	/**단말에서 GCM 서비스 등록 해지를 하면 해지된 등록 id를 받는다*/
	@Override
	protected void onUnregistered(Context context, String regId) {
		Log.d(tag, "onUnregistered. regId : "+regId);
	}

	@Override
	protected void onError(Context context, String errorId) {
		// TODO Auto-generated method stub

	}


}
