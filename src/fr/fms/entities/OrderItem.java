package fr.fms.entities;

public class OrderItem {

	private long id;
	private long idArticle;
	private int quatity;
	private long idOrder;

	public OrderItem(long id, long idArticle, int quatity, long idOrder) {
		this.id = id;
		this.idArticle = idArticle;
		this.quatity = quatity;
		this.idOrder = idOrder;
	}

	public OrderItem(long idArticle, int quatity, long idOrder) {
		this.idArticle = idArticle;
		this.quatity = quatity;
		this.idOrder = idOrder;
	}

	public OrderItem(long idArticle, int quatity) {
		this.idArticle = idArticle;
		this.quatity = quatity;

	}

	public OrderItem() {

	}

	public long getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(long idArticle) {
		this.idArticle = idArticle;
	}

	public int getQuatity() {
		return quatity;
	}

	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}

	public long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(long idOrder) {
		this.idOrder = idOrder;
	}

	public long getId() {
		return id;
	}

}
