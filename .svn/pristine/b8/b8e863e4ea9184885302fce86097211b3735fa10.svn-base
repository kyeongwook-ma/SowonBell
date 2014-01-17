package ma.dev.sowondejong.view;

import ma.dev.sowondejong.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PhraseRowView  extends LinearLayout implements OnClickListener {

	private Context mContext;
	private TextView tvPhrase;
	private LinearLayout phraseRow;

	private String mData;

	public interface OnPhraseClickListener {
		public void onPhraseClick( String mData );
	}

	private OnPhraseClickListener mListener;

	public void setOnPhraseClickListener(OnPhraseClickListener listener) {
		this.mListener = listener;
	}

	public PhraseRowView( Context context ) {
		super(context);
		this.mContext = context;
		LayoutInflater.from(mContext).inflate(R.layout.phrase_row_view, this);

		tvPhrase = (TextView)findViewById(R.id.tv_phrase_contents);
	
	}

	@Override
	public void onClick(View v) {

		switch (v.getId())
		{
		case R.layout.phrase_row_view :
		
			if (mListener != null) 
				mListener.onPhraseClick(mData);
			break;
			

		}

	}

	public void setData( String phrase ) {
		this.mData = phrase;
		tvPhrase.setText(mData);	
	}

}
