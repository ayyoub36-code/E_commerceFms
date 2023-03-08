package fr.fms.business;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.DAO;
import fr.fms.dao.DAOFactory;
import fr.fms.dao.OrderDao;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.Order;
import fr.fms.entities.OrderItem;

public class IE_ServicesImpl implements IE_Services {

	public DAO<Article> dao; // injection du dao
	public DAO<Category> daoCategory;
	public DAO<Order> daoOrder;
	public DAO<OrderItem> daoOrderItem;
	public Connection connection = DAO.getConnection();

	private Map<Long, OrderItem> cart; // stockage du panier

	public IE_ServicesImpl() {
		cart = new HashMap<>();
		dao = DAOFactory.getArticleDao();
		daoOrder = DAOFactory.getOrderDao();
		daoOrderItem = DAOFactory.getOrderItemDao();
		daoCategory = DAOFactory.getCategoryDao();
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
		if (dao.readAll().get((int) idArticle) != null) {
			if (!cart.containsKey(idArticle)) {
				orderItem = new OrderItem(idArticle, quantity); // creer orderItem avec l idArticle
			} else { // id exist déja => quantity ++
				orderItem.setQuantity(cart.get(idArticle).getQuantity() + 1);
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
	public void deleteItemCart(long id) {
		if (cart.containsKey(id)) {
			if (cart.get(id).getQuantity() > 1) {
				cart.get(id).setQuantity(cart.get(id).getQuantity() - 1);
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
			sum += article.getPrice() * set.getValue().getQuantity();// calculer le montant
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
					+ set.getValue().getQuantity() + "    Prix Unitaire : " + article.getPrice() + " €");
		}
	}

	@Override
	public ArrayList<Category> readAllCategory() {
		return daoCategory.readAll();
	}

	@Override
	public ArrayList<Article> readAllCategoryArticle(long idCategory) {
		return ((ArticleDao) dao).readAllCategory(idCategory);
	}

	@Override
	public Category readCategory(long idCat) {
		return daoCategory.read(idCat);
	}

	@Override
	public void createOrder(Map<Long, OrderItem> cart, long idCustomer) { // stocker order and OrderItems
		long idOrder = 0;
		if (idCustomer != 0) { // je cree une commande avec l id customer
			idOrder = ((OrderDao) daoOrder)
					.createAndReturnId(new Order(java.sql.Date.valueOf(java.time.LocalDate.now()), idCustomer));
			for (Entry<Long, OrderItem> set : cart.entrySet()) {
				Article article = dao.read(set.getValue().getIdArticle()); // persisiter tous les Orderitems
				OrderItem oi = new OrderItem(article.getId(), set.getValue().getQuantity(), idOrder);
				daoOrderItem.create(oi);
			}
		} else
			System.out.println("veuillez choisir le client !");
	}
}
