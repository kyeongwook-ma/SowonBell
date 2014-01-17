package ma.dev.sowondejong.data;

import java.util.List;


public abstract class DataModel<T> {

	List<T> itemList;

	protected void setListData(List<T> datas) {
		this.itemList = datas;
	}

	void addItem(T item) {
		itemList.add(item);
	}
	
	void addItem(T item, int position) {
		itemList.add(position, item);
	}
	
	void setItem(T item, int position) {
		itemList.set(position, item);
	}
	
	T findItem(Object searchKey) {
		return null;
	}

	List<T> readAllItem() {
		return itemList;
	}
	
	void delItem(Object searchKey) {
		itemList.remove(searchKey);
	}
	
	void updateItem(T item, int position) {
		itemList.set(position, item);
	}

	/**
	 * @Method Name  : extractDatafromDB
	 * @?‘ì„±??  : 2013. 8. 23. 
	 * @?‘ì„±??  : Dev
	 * @ë³?²½?´ë ¥  :
	 * @Method ?¤ëª… :  DBë¡œë????°ì´?°ë? ë½‘ì•„???ë£Œêµ¬ì¡°??ë§ê²Œ ??¥?˜ëŠ” ì¶”ìƒ ë©”ì†Œ??
	 */
	protected abstract void extractDatafromDB();
	
	
	/**
	 * @Method Name  : saveToDB
	 * @?‘ì„±??  : 2013. 8. 23. 
	 * @?‘ì„±??  : Dev
	 * @ë³?²½?´ë ¥  :
	 * @Method ?¤ëª… : ?ë£Œêµ¬ì¡°ë¥??µì§¸ë¡?DBë¡???¥?˜ëŠ” ì¶”ìƒ ë©”ì†Œ??
	 */
	protected abstract void saveToDB();

}
