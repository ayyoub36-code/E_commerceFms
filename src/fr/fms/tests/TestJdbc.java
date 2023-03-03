package fr.fms.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Article;

public class TestJdbc {

	public static void main(String[] args) {

		getAll();

		// test method create
		Article article1 = new Article("souris_top", "hp", 75, 1);
		// create(article1);

		// test method update
		Article article2 = getArticle(15);
		article2.setBrand("android");
		article2.setPrice(85);
		updateArticle(article2);
		// System.out.println(getArticle(15));

		System.out.println("\n\nListe articles après les modif : ");
		getAll();
		// delete(14);

		// method getArticle
		System.out.println(getArticle(3));

	}

	private static void getAll() {
		ArrayList<Article> articles = new ArrayList<>();

		String sql = "SELECT * FROM T_Articles";
		try (Statement statement = getConnection().createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(sql)) {
				while (resultSet.next()) {
					long rs_IdArticle = resultSet.getLong(1);
					String rs_Description = resultSet.getString(2);
					String rs_Brand = resultSet.getString(3);
					double rs_Price = resultSet.getDouble(4);
					long rs_IdCategory = resultSet.getLong(5);
					articles.add(new Article(rs_IdArticle, rs_Description, rs_Brand, rs_Price, rs_IdCategory));
				}
			}
			articles.forEach(e -> System.out.println(e));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// methode create
	public static void create(Article article) {
		String sql = "INSERT INTO T_Articles (Description,Brand,UnitaryPrice) VALUES (?,?,?);";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, article.getDescription());
			ps.setString(2, article.getBrand());
			ps.setDouble(3, article.getPrice());
			if (ps.executeUpdate() == 1)
				System.out.println("insertion ok !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Article getArticle(long idArticle) {
		Article article = null;
		String sql = "Select * from T_articles where idArticle =? ;";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setLong(1, idArticle);
			try (ResultSet resultSet = ps.executeQuery()) {
				while (resultSet.next()) {
					long rs_IdArticle = resultSet.getLong(1);
					String rs_Description = resultSet.getString(2);
					String rs_Brand = resultSet.getString(3);
					double rs_Price = resultSet.getDouble(4);
					long rs_IdCategory = resultSet.getLong(5);
					article = new Article(rs_IdArticle, rs_Description, rs_Brand, rs_Price, rs_IdCategory);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return article;

	}

	// mettre à jour un article
	public static void updateArticle(Article article) {

		String sql = "UPDATE T_Articles set Description=?, Brand =?,UnitaryPrice=? where idArticle = ?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, article.getDescription());
			ps.setString(2, article.getBrand());
			ps.setDouble(3, article.getPrice());
			ps.setLong(4, article.getId());
			ps.executeUpdate();
			System.out.println("mis à jour ok !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// delete article
	public static void delete(long idArticle) {
		String sql = "delete from t_Articles where idArticle = ? ;";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setLong(1, idArticle);
			if (ps.executeUpdate() == 1)
				System.out.println("delete ok !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// recuperer une connection
		String url = "jdbc:mariadb://localhost:3306/shop";
		String login = "root";
		String password = "fms2023";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

}
