package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Order;

public class OrderDao extends DAO<Order> {

	public OrderDao(Connection connection) {
		super(connection);
	}

	@Override
	public void create(Order obj) {
		// TODO Auto-generated method stub

	}

	public long createAndReturnId(Order obj) {
		long id = 0;
		// java.sql.Date.valueOf(java.time.LocalDate.now())
		String sql = "INSERT INTO T_Orders (OrderDate,IdCustomer) VALUES (?,?);";
		try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setDate(1, obj.getDate());
			ps.setLong(2, obj.getIdCustomer());
			if (ps.executeUpdate() == 1) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("mauvaise requette sql !");
		}

		return id;

	}

	@Override
	public Order read(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Order obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Order> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
