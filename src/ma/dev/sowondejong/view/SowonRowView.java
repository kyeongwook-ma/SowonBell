package ma.dev.sowondejong.view;

import java.io.IOException;
import java.io.InputStream;

import ma.dev.sowondejong.R;
import ma.dev.sowondejong.data.SowonMessage;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import static ma.dev.sowondejong.activity.SowonSendActivity.SEND_MODE.*;

public class SowonRowView extends LinearLayout implements OnClickListener {

	private Context mContext;
	private TextView tvTo, tvDate, tvContents;
	private ImageView ivImg; 

	private SowonMessage mData;

	public interface OnSowonClickListener {
		public void onSowonClick( SowonMessage message );
	}

	private OnSowonClickListener mListener;

	public void setOnSowonClickListener(OnSowonClickListener listener) {
		this.mListener = listener;
	}

	public SowonRowView( Context context ) {
		super(context);
		this.mContext = context;
		LayoutInflater.from(mContext).inflate(R.layout.sowon_row_list, this);

		tvTo = (TextView)findViewById(R.id.tv_wish_to);
		tvDate = (TextView)findViewById(R.id.tv_wish_date);
		tvContents = (TextView)findViewById(R.id.tv_wish_contents);
		ivImg = (ImageView)findViewById(R.id.iv_wish_img);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId())
		{
		case R.layout.sowon_row_list :

			if (mListener != null)
				mListener.onSowonClick(mData);
			break;

		}

	}

	public void setData( SowonMessage message ) {
		this.mData = message;

		String to = null;

		try {

			to = mData.getTo() + "님에게";

			// 카톡의 경우 처리
			if(mData.getType().equals(KAKAO.name())) 
				to = "카카오톡으로 발송되었습니다.";
			
		}
		catch(NullPointerException e) { to = ""; } 

		// temp
		tvTo.setText("");		
		tvDate.setText(mData.getDate());
		tvContents.setText(mData.getMessage());

		// get input stream
		InputStream ims = null;
		try {
			ims = mContext.getAssets().open(mData.getImg().getName());
		} catch (IOException e) { }
		// load image as Drawable
		Drawable d = Drawable.createFromStream(ims, null);
		// set image to ImageView
		ivImg.setImageDrawable(d);

	}
}