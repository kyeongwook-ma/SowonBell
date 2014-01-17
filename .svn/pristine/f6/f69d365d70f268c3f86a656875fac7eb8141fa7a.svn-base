package ma.dev.sowondejong.network;



import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Dev on 13. 8. 12.
 */
public abstract class NetworkRequest {

	protected URL url;
	protected Object result;
	protected boolean isCancel;
	private OnProcessCompletedListener mListener;

	public interface OnProcessCompletedListener {
		void onCompleted(NetworkRequest request);
	}

	public Object getResult() {
		return result;
	}

	protected abstract void parsing() ;

	void setUrl(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void setOnCompleteListener(OnProcessCompletedListener listener) {
		this.mListener = listener;
	}

	boolean isCanceled() {
		return isCancel;
	}

	void process() {
		
		new Thread () {
			public void run() {             
				parsing();
				mListener.onCompleted(NetworkRequest.this);
			};
		}.start();
	}
}