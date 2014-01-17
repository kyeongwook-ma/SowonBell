package ma.dev.sowondejong.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ma.dev.sowondejong.view.SowonRowView;
import ma.dev.sowondejong.view.SowonRowView.OnSowonClickListener;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SowonAdapter extends BaseAdapter implements OnSowonClickListener {

	private Context mContext = null;
	private ArrayList<SowonMessage> items = new ArrayList<SowonMessage>();
	private OnSowonClickListener mListener;

	public SowonAdapter(Context context, List<SowonMessage> items) {
		super();
		this.mContext = context;
		this.items = (ArrayList<SowonMessage>) items;
		LayoutInflater.from(mContext);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		SowonRowView itemView = (SowonRowView)convertView;

		if(itemView == null) {
			itemView = new SowonRowView(mContext);
			itemView.setOnSowonClickListener(this);
		}
		itemView.setData((SowonMessage)getItem(position));

		return itemView;
	}

	@Override
	public void onSowonClick(SowonMessage message) {
		if(mListener != null) {
			mListener.onSowonClick(message);
		}		
	}
	
	public int getCount() {
		return items.size();
	}

	public SowonMessage getItem(int position) {
		return items.get(position);
	}

	
	public long getItemId(int position) {
		return position;
	}

	public void remove(SowonMessage deletedItem) {
		items.remove(deletedItem);
		notifyDataSetChanged();
	}

	
	public void add(SowonMessage item, int position) {
		items.add(position, (SowonMessage) item);
		notifyDataSetChanged();
	}

	public void add(SowonMessage item) {
		items.add((SowonMessage) item);
		notifyDataSetChanged();		
	}

	public void add(List<SowonMessage> items) {
		items.addAll((Collection<? extends SowonMessage>) items);
		notifyDataSetChanged();
	}

	public  SowonMessage getItemByIndex(int position) {
		return (SowonMessage) items.get(position);
	}



}
