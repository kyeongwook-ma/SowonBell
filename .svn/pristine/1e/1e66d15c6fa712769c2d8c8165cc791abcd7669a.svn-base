package ma.dev.sowondejong.view;

import java.util.ArrayList;

import ma.dev.sowondejong.R;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FragmentController {

	private static FragmentController mInstance;
	
	static {
		mInstance = new FragmentController();
	}
	
	private FragmentController() { fragList = new ArrayList<Fragment>(); };
		
	private final ArrayList<Fragment> fragList;
	private FragmentManager frgManager ;

	public static FragmentController getInstance() {
		if(mInstance == null) {
			return mInstance = new FragmentController();
		}
		return mInstance;
	}
		
	public Fragment getFragment(int idx) {
		return fragList.get(idx);
	}
	
	public void addFragment(int id, Fragment fragment) {
	
		fragList.add(fragment);

		FragmentTransaction ft = frgManager.beginTransaction();
		ft.add(id, fragment);
		ft.commit();		
	}
	
	public int getFragmentIndex(Fragment fragment) {
		for(int i = 0; i < fragList.size(); ++i) {
			if(fragList.get(i).equals(fragment)) {
				return i;
			}
		}
		return -1;
	}
	
	public void setFragManager(FragmentManager manager) {
		this.frgManager = manager;
	}
	
	public Fragment findFragmentById(int res) {
		return frgManager.findFragmentById(res);
	}
	
	private void showFragment(int fragmentIndex, boolean addToBackStack) {

	    FragmentTransaction transaction = frgManager.beginTransaction();
	    for (int i = 0; i < fragList.size(); i++) {
	        if (i == fragmentIndex) {
	            transaction.show(fragList.get(i));
	        } else {
	            transaction.hide(fragList.get(i));
	        }
	    }
	    if (addToBackStack) {
	        transaction.addToBackStack(null);
	    }
	    transaction.commit();
	}
	public void replaceFragment(int newFragmentIndex, int layout) {

		Fragment newFragment = getFragment(newFragmentIndex);

		// replace fragment
		final FragmentTransaction transaction = frgManager.beginTransaction();

		transaction.replace(layout, newFragment);

		// Commit the transaction
		transaction.commit();

	}

}
