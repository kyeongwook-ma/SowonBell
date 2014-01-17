package ma.dev.sowondejong.activity;

import java.util.ArrayList;

import ma.dev.sowondejong.R;
import ma.dev.sowondejong.data.SowonAdapter;
import ma.dev.sowondejong.data.SowonMessage;
import ma.dev.sowondejong.db.DBHelper;
import ma.dev.sowondejong.db.SowonDB.SowonDBScheme;
import ma.dev.sowondejong.view.BackTabFragment;
import ma.dev.sowondejong.view.FragmentController;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import de.timroes.swipetodismiss.SwipeDismissList;
import de.timroes.swipetodismiss.SwipeDismissList.UndoMode;

public class SowonListActivity extends SherlockFragmentActivity {

	private ListView sowonList;
	private SowonAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sowon);

		BackTabFragment backFrag = new BackTabFragment(MainActivity.class, R.drawable.wish_actionbar_title);

		FragmentController.getInstance().setFragManager(getSupportFragmentManager());
		
		FragmentController.getInstance().addFragment(R.id.frg_sowon_list, backFrag);
	
		sowonList = (ListView)findViewById(R.id.listview_sowon);
		mAdapter = new SowonAdapter(this, DBHelper.getInstance().getAllSowonMessage());
		
		sowonList.setAdapter(mAdapter);
		
		SwipeDismissList.OnDismissCallback callvack = new SwipeDismissList.OnDismissCallback() {
			// Gets called whenever the user deletes an item.
			public SwipeDismissList.Undoable onDismiss(AbsListView listView, final int position) {
				// Get your item from the adapter (mAdapter being an adapter for MyItem objects)
				final SowonMessage deletedItem = (SowonMessage) mAdapter.getItem(position);
				// Delete item from adapter
				mAdapter.remove(deletedItem);
				
				// Return an Undoable implementing every method
				return new SwipeDismissList.Undoable() {

					// Method is called when user undoes this deletion
					public void undo() {
						// Reinsert item to list
						mAdapter.add(deletedItem, position);
					}

					// Return an undo message for that item
					public String getTitle() {
						return "해당 메세지가 삭제되었습니다.";
					}

					// Called when user cannot undo the action anymore
					public void discard() {
						// Use this place to e.g. delete the item from database
							
						DBHelper.getInstance().delete(SowonDBScheme.TABLE_NAME, deletedItem.getId());
						Log.i("position", String.valueOf(position));
					}
				};
			}
		};
		
		UndoMode mode = UndoMode.SINGLE_UNDO;

		SwipeDismissList swipeList = new SwipeDismissList(sowonList, callvack, mode);
		swipeList.setAutoHideDelay(1000);
		
	}

	@Override
	public void onBackPressed() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

}
