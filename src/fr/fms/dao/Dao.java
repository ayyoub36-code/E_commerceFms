package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;

import fr.fms.bdd.BddSingleton;

public abstract class DAO<T> {

	// public Connection connection = BddConnection.getConnection();
	protected static Connection connection = null;

	public DAO(Connection connection) {
		DAO.connection = BddSingleton.getInstance().getConnection();
	}

	public abstract void create(T obj);

	public abstract T read(long id);

	public abstract boolean update(T obj);

	public abstract boolean delete(long id);

	public abstract ArrayList<T> readAll();

	public static Connection getConnection() {
		return connection;
	}

}
