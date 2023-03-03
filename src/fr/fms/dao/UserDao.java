package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.fms.entities.Customer;
import fr.fms.entities.User;

public class UserDao implements Dao<User> {

	private User user;

	public User getUser() {
		return user;
	}

	@Override
	public void create(User obj) {
		String sql = "INSERT INTO T_Users (Login,Password) VALUES (?,?);";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());
			if (ps.executeUpdate() == 1)
				throw new RuntimeException("insertion ok !");
		} catch (SQLException e) {
			throw new RuntimeException("mauvaise requette sql !");
		}

	}

	@Override
	public User read(long id) {
		User user = null;
		String sql = "Select * from T_Users where idUser =? ;";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, id);
			try (ResultSet resultSet = ps.executeQuery()) {
				if (!resultSet.next())
					throw new RuntimeException("zéro résultat vérifier l id demander !");
				else {
					while (resultSet.next()) {
						long rs_IdUser = resultSet.getLong(1);
						String rs_Login = resultSet.getString(2);
						String rs_Password = resultSet.getString(3);

						user = new User(rs_IdUser, rs_Login, rs_Password);
					}
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("mauvaise requette sql ! verifier l id saisie !");
		}

		return user;
	}

	@Override
	public boolean update(User obj) {
		if (obj != null) {
			String sql = "UPDATE T_Users set Login=?, password =? where idUser = ?;";
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, obj.getLogin());
				ps.setString(2, obj.getPassword());
				ps.setLong(3, obj.getId());
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return false;
			}
		} else {
			throw new RuntimeException("Veuillez fournir un utilisateur !");
		}

	}

	@Override
	public boolean delete(long id) {
		String sql = "delete from t_Users where idUser = ? ;";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, id);
			if (ps.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public ArrayList<User> readAll() {
		// find all users
		ArrayList<User> users = new ArrayList<>();
		String sql = "SELECT * FROM T_Users";
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(sql)) {
				while (resultSet.next()) {
					long rs_IdUser = resultSet.getLong(1);
					String rs_Login = resultSet.getString(2);
					String rs_Password = resultSet.getString(3);
					users.add(new User(rs_IdUser, rs_Login, rs_Password));
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return users;
	}

	// trouver les customers d'un user
	public List<Customer> getUserCustomers(long idUser) {
		ArrayList<Customer> customers = new ArrayList<>();
		String sql = "SELECT * FROM T_Customers where idUser = ? ;";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, idUser);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					long rs_IdCustomer = rs.getLong(1);
					String rs_LastName = rs.getString(2);
					String rs_FirstName = rs.getString(3);
					String rs_Email = rs.getString(4);
					String rs_Adress = rs.getString(5);
					long rs_IdUser = rs.getLong(6);
					customers.add(
							new Customer(rs_IdCustomer, rs_LastName, rs_FirstName, rs_Email, rs_Adress, rs_IdUser));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;

	}

	// connection user verif login & password
	public boolean verifUserAuth(String login, String password) {
		// demander le login et chercher dans la bd le bon user
		String sql = "SELECT * FROM T_Users where login = ? ;";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, login);
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					long rs_IdUser = rs.getLong(1);
					String rs_Login = rs.getString(2);
					String rs_Password = rs.getString(3);
					user = new User(rs_IdUser, rs_Login, rs_Password);
					if (rs_Password.equals(password)) // comparer le password à password

						return true;
				} else
					System.out.println("pas d'utilisateur avec ce login !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
