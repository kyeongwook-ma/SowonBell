package ma.dev.sowondejong.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

/**
 * <pre>
 * util
 * LauncherUtil.java
 *
 * ?�명 :
 * </pre>
 *
 * @author : kwma
 * @version : v1.0
 * @since : 2013. 7. 22.
 */
public final class LauncherUtil {

    private static final int DISPLAY_TIME = 2000;
    private static Intent intent;

    /**
     * ?�정 ?�간?�안 ?�면??보여주고 ?�음 ?�티비티�??�동?�다.
     *
     * @param activity ?�출?�는�??�티비티
     * @param cls      ?�출?�는 쪽의 ?�래??     */
    public static void go(final Activity activity, final Class<?> cls) {
        Handler handler;

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                intent = new Intent(activity, cls);
                activity.startActivity(intent);
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                activity.finish();
            }
        }, DISPLAY_TIME);
    }

}
