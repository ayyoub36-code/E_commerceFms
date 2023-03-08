package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.entities.OrderItem;

public class OrderItemDao extends DAO<OrderItem> {

	public OrderItemDao(Connection connection) {
		super(connection);
	}

	@Override
	public void create(OrderItem obj) {
		String sqlOItem = "INSERT INTO T_OrderItems (IdArticle,Quantity,idOrder) VALUES (?,?,?);";
		try (PreparedStatement ps = connection.prepareStatement(sqlOItem)) {
			ps.setLong(1, obj.getIdArticle());
			ps.setInt(2, obj.getQuantity());
			ps.setLong(3, obj.getIdOrder());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("mauvaise requette sql !");
		}
	}

	@Override
	public OrderItem read(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(OrderItem obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<OrderItem> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
