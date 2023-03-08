package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Article;

public class ArticleDao extends DAO<Article> {

	public ArticleDao(Connection connection) {
		super(connection);

	}

	/**
	 * creer un article
	 * 
	 * @param Article
	 * @author Mehdioui_Ayyoub
	 */
	@Override
	public void create(Article obj) {
		String sql = "INSERT INTO T_Articles (Description,Brand,UnitaryPrice,IdCategory) VALUES (?,?,?,?);";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, obj.getDescription());
			ps.setString(2, obj.getBrand());
			ps.setDouble(3, obj.getPrice());
			ps.setLong(4, obj.getIdCategory());
			if (ps.executeUpdate() == 1)
				throw new RuntimeException("insertion ok !");
		} catch (SQLException e) {
			throw new RuntimeException("mauvaise requette sql !");
		}
	}

	/**
	 * envoie un article
	 * 
	 * @param id de l article
	 */
	@Override
	public Article read(long id) {
		Article article = null;
		String sql = "Select * from T_articles where idArticle =? ;";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				article = new Article(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDouble(4), resultSet.getLong(5));
			}
		} catch (SQLException e) {
			throw new RuntimeException("mauvaise requette sql ! verifier l id saisie !");
		}
		return article;
	}

	/**
	 * mis à jour de l'article
	 * 
	 * @param Article
	 */
	@Override
	public boolean update(Article obj) {
		String sql = "UPDATE T_Articles set Description=?, Brand =?,UnitaryPrice=?,IdCategory=? where idArticle = ?;";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, obj.getDescription());
			ps.setString(2, obj.getBrand());
			ps.setDouble(3, obj.getPrice());
			ps.setLong(4, obj.getIdCategory());
			ps.setLong(5, obj.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	/**
	 * supprimer un article
	 * 
	 * @param id de l article
	 */
	@Override
	public boolean delete(long id) {
		String sql = "delete from t_Articles where idArticle = ? ;";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, id);
			if (ps.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	/**
	 * affiche tous les articles
	 */
	@Override
	public ArrayList<Article> readAll() {
		ArrayList<Article> articles = new ArrayList<>();
		String sql = "SELECT * FROM T_Articles";
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(sql)) {
				while (resultSet.next()) {
					articles.add(new Article(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getDouble(4), resultSet.getLong(5)));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return articles;
	}

	public ArrayList<Article> readAllCategory(long idCategory) {
		ArrayList<Article> articlesOfCategory = new ArrayList<>();
		String sql = "SELECT * FROM T_Articles WHERE idCategory=?;";
		try (PreparedStatement pr = connection.prepareStatement(sql)) {
			pr.setLong(1, idCategory);
			try (ResultSet resultSet = pr.executeQuery()) {
				while (resultSet.next()) {
					articlesOfCategory.add(new Article(resultSet.getLong(1), resultSet.getString(2),
							resultSet.getString(3), resultSet.getDouble(4), resultSet.getLong(5)));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return articlesOfCategory;
	}
}
