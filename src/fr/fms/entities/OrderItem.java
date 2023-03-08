package fr.fms.entities;

public class OrderItem {

	private long id;
	private long idArticle;
	private int quantity;
	private long idOrder;

	public OrderItem(long id, long idArticle, int quatity, long idOrder) {
		this.id = id;
		this.idArticle = idArticle;
		this.quantity = quatity;
		this.idOrder = idOrder;
	}

	public OrderItem(long idArticle, int quatity, long idOrder) {
		this.idArticle = idArticle;
		this.quantity = quatity;
		this.idOrder = idOrder;
	}

	public OrderItem(long idArticle, int quatity) {
		this.idArticle = idArticle;
		this.quantity = quatity;

	}

	public OrderItem() {

	}

	public long getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(long idArticle) {
		this.idArticle = idArticle;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quatity) {
		this.quantity = quatity;
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
