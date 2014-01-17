package ma.dev.sowondejong.util;
import android.app.Application;
import android.content.Context;


public class SowonDejonApp extends Application {

	private static Context mContext;
	
	public void onCreate() {
		mContext = this;
	}
	
	public static Context getContext(){
		return mContext;
	}
	


}