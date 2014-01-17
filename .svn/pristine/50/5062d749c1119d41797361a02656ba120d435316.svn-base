package ma.dev.sowondejong.view;

import java.util.ArrayList;

import ma.dev.sowondejong.R;
import ma.dev.sowondejong.data.PhraseAdapter;
import ma.dev.sowondejong.data.Phrases;
import ma.dev.sowondejong.db.DBHelper;
import ma.dev.sowondejong.view.PhraseRowView.OnPhraseClickListener;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockDialogFragment;

public class PhraseSelectDialog extends SherlockDialogFragment implements OnItemClickListener, OnClickListener {

	private ListView phraseList;
	private ImageView ivBtnCancel, ivBtnSelect;
	private PhraseAdapter mAdapter;
	private OnPhraseClickListener mListener;
	private String selectedString;
	
	public PhraseSelectDialog() {
		setStyle(DialogFragment.STYLE_NO_TITLE, 0);	
	}

	public void setOnPhraseClickListener(OnPhraseClickListener listener) {
		this.mListener = listener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.phrase_select_dialog, null);

		phraseList = (ListView)v.findViewById(R.id.list_phrase);
		phraseList.setOnItemClickListener(this);

		ivBtnCancel = (ImageView)v.findViewById(R.id.iv_phrase_cancel);
		ivBtnCancel.setOnClickListener(this);

		ivBtnSelect = (ImageView)v.findViewById(R.id.iv_phrase_select);
		ivBtnSelect.setOnClickListener(this);

		ArrayList<String> phrases = new ArrayList<String>();

		for(String str : Phrases.phraseList) {
			phrases.add(str);
		}
		
		
		mAdapter = new PhraseAdapter( getActivity(), phrases);

		phraseList.setAdapter(mAdapter);


		return v;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
		selectedString = (String)mAdapter.getItem(position);
		v.setSelected(true);
	}

	@Override
	public void onClick(View v) {

		switch(v.getId()) {

		case R.id.iv_phrase_cancel :
			dismiss();
			break;

		case R.id.iv_phrase_select :
			mListener.onPhraseClick(selectedString);	
			dismiss();
			break;
		

		}
	}

}
