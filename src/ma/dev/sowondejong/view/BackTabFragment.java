package ma.dev.sowondejong.view;

import ma.dev.sowondejong.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragment;

@SuppressLint("ValidFragment")
public class BackTabFragment extends SherlockFragment implements OnClickListener {
	
	private ImageView ivBack, ivTitle;
	private Class backClass;
	private View v;
	private int layoutId;
	
	public BackTabFragment( Class backClass, int titleId ) {
		this.backClass = backClass;
		this.layoutId = titleId;
	}
	
	@Override
	public View getView() {
		return v;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		v = inflater.inflate( R.layout.sowon_tab_fragment, container, false );

		ivBack = (ImageView)v.findViewById(R.id.iv_back);
		ivBack.setOnClickListener(this);

		ivTitle =(ImageView)v.findViewById(R.id.iv_sowon_tab_title);
		ivTitle.setImageResource(layoutId);
		
		return v;
	}

	@Override
	public void onClick(View v) {

		switch(v.getId()) {

		case R.id.iv_back :
			startActivity(new Intent(getActivity(), backClass));
			getActivity().finish();
			break;

		}

	}


}

