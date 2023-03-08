package fr.fms.dao;

import java.sql.Connection;

import fr.fms.bdd.BddSingleton;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.Order;
import fr.fms.entities.OrderItem;
import fr.fms.entities.User;

public class DAOFactory {

	protected static final Connection conn = BddSingleton.getInstance().getConnection();

	public static DAO<Article> getArticleDao() {
		return new ArticleDao(conn);
	}

	public static DAO<User> getUserDao() {
		return new UserDao(conn);
	}

	public static DAO<Category> getCategoryDao() {
		return new CategoryDao(conn);
	}

	public static DAO<Order> getOrderDao() {
		return new OrderDao(conn);
	}

	public static DAO<OrderItem> getOrderItemDao() {
		return new OrderItemDao(conn);
	}

}
