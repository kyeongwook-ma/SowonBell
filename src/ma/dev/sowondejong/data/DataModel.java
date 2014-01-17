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
	 * @?�성??  : 2013. 8. 23. 
	 * @?�성??  : Dev
	 * @�?��?�력  :
	 * @Method ?�명 :  DB로�????�이?��? 뽑아???�료구조??맞게 ??��?�는 추상 메소??
	 */
	protected abstract void extractDatafromDB();
	
	
	/**
	 * @Method Name  : saveToDB
	 * @?�성??  : 2013. 8. 23. 
	 * @?�성??  : Dev
	 * @�?��?�력  :
	 * @Method ?�명 : ?�료구조�??�째�?DB�???��?�는 추상 메소??
	 */
	protected abstract void saveToDB();

}
