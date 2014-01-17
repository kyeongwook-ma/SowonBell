package ma.dev.sowondejong.network;



import java.util.ArrayList;

/**
 * Created by Dev on 13. 8. 12.
 */
public class NetworkController {

	private static NetworkController mInstance;
	private ArrayList<NetworkRequest> mWorkList = new ArrayList<NetworkRequest>();

	public static NetworkController getInstance() {
		if(mInstance == null) {
			return new NetworkController();
		}
		return mInstance;
	}

	private NetworkController() { }

	public synchronized void enqueue(NetworkRequest request) {
	
		final int MAX_THREAD_SIZE = 5;
		
		if(mWorkList.size() <= MAX_THREAD_SIZE) {
			request.process();
			mWorkList.add(request);
			notify();
		}
	}

	public NetworkRequest dequeue() {

		while(mWorkList.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();;
			}
		}

		return mWorkList.remove(0);
	}

	public void remove(NetworkRequest request) {
		mWorkList.remove(request);
	}


}
