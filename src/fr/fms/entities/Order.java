package fr.fms.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {

	private long id;
	private Date date;
	private long IdCustomer;
	private List<OrderItem> items;

	public Order(long id, Date date, long IdCustomer, List<OrderItem> items) {

		this.id = id;
		this.date = date;
		this.IdCustomer = IdCustomer;
		this.setItems(new ArrayList<>());
	}

	public Order(Date date, long IdCustomer, List<OrderItem> items) {
		this.date = date;
		this.IdCustomer = IdCustomer;
		this.setItems(new ArrayList<>());
	}

	public Order(Date date, long IdCustomer) {
		this.date = date;
		this.IdCustomer = IdCustomer;

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public long getIdCustomer() {
		return IdCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		IdCustomer = idCustomer;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

}
