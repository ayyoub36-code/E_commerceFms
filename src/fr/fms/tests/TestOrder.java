package fr.fms.tests;

import java.time.LocalDate;

import fr.fms.business.IE_ServicesImpl;
import fr.fms.entities.Order;

public class TestOrder {

	public static void main(String[] args) {
		IE_ServicesImpl service = new IE_ServicesImpl();

		Order order1 = new Order(LocalDate.now(), 1);
		// service.createOrder(order1, 1);
		System.out.println(service.getLastOrderAdded());

	}

}
