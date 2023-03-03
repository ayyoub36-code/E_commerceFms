package fr.fms.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.Dao;
import fr.fms.entities.Article;
import fr.fms.entities.OrderItem;

public class IE_ServicesImpl implements IE_Services {

	public ArticleDao dao; // injection du dao
	public Connection connection = Dao.connection;

	private Map<Long, OrderItem> cart; // stockage du panier

	public IE_ServicesImpl() {
		cart = new HashMap<>();
		dao = new ArticleDao();
	}

	@Override
	public ArrayList<Article> readAll() {
		return dao.readAll();
	}

	@Override
	public Article read(long id) {
		return dao.read(id);
	}

	private OrderItem orderItem = null;

	/**
	 * ajouter un article
	 * 
	 * @param article
	 * @author Mehdioui_Ayyoub
	 */
	@Override
	public boolean addOrderItem(long idArticle) {
		int quantity = 1;
		// Article article = dao.read(idArticle);
		if (dao.readAll().get((int) idArticle) != null) {
			// id exist déja => augmente la quantity
			if (!cart.containsKey(idArticle)) {
				// creer un nouveau orderItem avec l idArticle
				orderItem = new OrderItem(idArticle, quantity);
			} else {
				orderItem.setQuatity(cart.get(idArticle).getQuatity() + 1);
			}
			cart.put(idArticle, orderItem);
			return true;
		} else
			System.out.println("Veuillez choisir un id valide ! ");
		return false;
	}

	/**
	 * afficher le contenu du panier
	 */
	@Override
	public void readCart() {
		if (!cart.isEmpty()) {
			System.out.println("Voici le contenu de votre panier : \n");
			display(cart);
		} else
			System.out.println("Ajouter des produits dans votre panier !");
	}

	/**
	 * supprimer un article du panier
	 * 
	 * @param id de l article
	 */
	@Override
	public void deleteItemCart(long id) { // TODO enlever du panier => quantity ?
		if (cart.containsKey(id)) {
			if (cart.get(id).getQuatity() > 1) {
				cart.get(id).setQuatity(cart.get(id).getQuatity() - 1);
			} else {
				cart.remove(id);
				System.out.println("Article supprimé de votre panier ");
			}
		} else
			System.out.println("Article introuvable !");

	}

	/**
	 * payer la commande
	 * 
	 * @return la somme totale double
	 */
	@Override
	public double pay() {
		double sum = 0;
		// lire cart
		for (Map.Entry<Long, OrderItem> set : cart.entrySet()) {
			Article article = dao.read(set.getValue().getIdArticle());
			sum += article.getPrice() * set.getValue().getQuatity();// calculer le montant
		}
		return sum;
	}

	public Map<Long, OrderItem> getCart() {
		return cart;
	}

	public void display(Map<Long, OrderItem> cart) {
		for (Entry<Long, OrderItem> set : cart.entrySet()) {
			Article article = dao.read(set.getValue().getIdArticle());
			System.out.println("Article : " + article.getId() + "  " + article.getDescription() + "     x"
					+ set.getValue().getQuatity() + "    Prix Unitaire : " + article.getPrice() + " €");
		}
	}

	@Override
	public ArrayList<Article> readAllCategoryArticle(long idCategory) {
		return dao.readAllCategory(idCategory);
	}

	@Override
	public void createOrder(Map<Long, OrderItem> cart, long idCustomer) { // stocker order and OrderItems
		if (idCustomer != 0) { // persister la commande(idCustomer) => {idOrder}
			String sql = "INSERT INTO T_Orders (OrderDate,IdCustomer) VALUES (?,?);";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
				ps.setLong(2, idCustomer);
				ps.executeQuery();

				long idOrder = getLastOrderAdded(); // recuperer l id du dernier order add bd
				System.out.println(idOrder);
				// persister les diferents itemsOrders => ListItemOrder(Order)
				String sqlOItem = "INSERT INTO T_OrderItems (IdArticle,Quantity,idOrder) VALUES (?,?,?);";
				for (Entry<Long, OrderItem> set : cart.entrySet()) {
					Article article = dao.read(set.getValue().getIdArticle());
					// boucle for pour persisiter tous les Orderitems
					try (PreparedStatement ps2 = connection.prepareStatement(sqlOItem)) {
						ps2.setLong(1, article.getId());
						ps2.setInt(2, set.getValue().getQuatity());
						ps2.setLong(3, idOrder);
						ps2.executeUpdate();
					}
				}

			} catch (SQLException e) {
				throw new RuntimeException("mauvaise requette sql !");
			}
		} else
			System.out.println("veuillez choisir le client !");

	}

	public long getLastOrderAdded() {
		long rs_Count = 0;
		String sql = "SELECT * FROM t_orders ORDER BY idOrder DESC LIMIT 1;";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			rs_Count = rs.getLong(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs_Count;
	}

}
