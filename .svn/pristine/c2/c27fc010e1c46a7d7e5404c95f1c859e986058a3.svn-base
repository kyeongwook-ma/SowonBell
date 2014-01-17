package ma.dev.sowondejong.data;

import java.util.ArrayList;
import java.util.List;

import ma.dev.sowondejong.view.PhraseRowView;
import ma.dev.sowondejong.view.PhraseRowView.OnPhraseClickListener;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PhraseAdapter extends BaseAdapter implements OnPhraseClickListener {

	private Context mContext;
	private ArrayList<String> mData = new ArrayList<String>();
	
	private OnPhraseClickListener mListener;

	public PhraseAdapter(Context context, List<String> phraseList) {
		this.mContext = context;
		this.mData = (ArrayList<String>) phraseList;
	}
	
	public void setOnPhraseClickListener(OnPhraseClickListener listener) {
		this.mListener = listener;
	}
	
	public void addPhrase(String phrase) {
		mData.add(phrase);
		notifyDataSetChanged();
	}
	
	public void addPhrase(List<String> phraseList) {
		mData.addAll(phraseList);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {

		PhraseRowView itemView = (PhraseRowView)convertView;

		if(itemView == null) {
			itemView = new PhraseRowView(mContext);
			itemView.setOnPhraseClickListener(this);
		}
		itemView.setData((String)getItem(position));

		return itemView;
	}

	@Override
	public void onPhraseClick(String mData) {
		
		if(mListener != null) {
			mListener.onPhraseClick(mData);;
		}				
	}
	
	
}
