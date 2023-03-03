package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;

import fr.fms.bdd.BddSingleton;

public interface Dao<T> {

	// public Connection connection = BddConnection.getConnection();
	public Connection connection = BddSingleton.getInstance().connection;

	public void create(T obj);

	public T read(long id);

	public boolean update(T obj);

	public boolean delete(long id);

	public ArrayList<T> readAll();

}
