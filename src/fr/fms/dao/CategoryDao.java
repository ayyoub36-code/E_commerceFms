package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Category;

public class CategoryDao extends DAO<Category> {

	public CategoryDao(Connection connection) {
		super(connection);

	}

	@Override
	public void create(Category obj) {
		String sql = "INSERT INTO T_Categories (CatName,Description) VALUES (?,?);";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getDescription());
			if (ps.executeUpdate() == 1)
				System.out.println("insertion ok !\n");
		} catch (SQLException e) {
			throw new RuntimeException("mauvaise requette sql !");
		}

	}

	@Override
	public Category read(long id) {
		Category category = null;
		String sql = "Select * from T_categories where idCategory =? ;";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					category = new Category(rs.getLong(1), rs.getString(2), rs.getString(3));
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("mauvaise requette sql ! verifier l id saisie !");
		}
		return category;
	}

	@Override
	public boolean update(Category obj) {
		String sql = "UPDATE T_Categories set CatName=?, Description=? where idCategory = ?;";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getDescription());
			ps.setLong(3, obj.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete(long id) {
		String sql = "delete from T_Categories where idCategory = ? ;";
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
	public ArrayList<Category> readAll() {
		ArrayList<Category> categories = new ArrayList<>();

		String sql = "SELECT * FROM T_Categories";
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(sql)) {
				while (resultSet.next()) {
					long rs_IdCategory = resultSet.getLong(1);
					String rs_CatName = resultSet.getString(2);
					String rs_Description = resultSet.getString(3);
					categories.add(new Category(rs_IdCategory, rs_CatName, rs_Description));
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return categories;
	}

}
