package fr.fms.business;

import java.util.ArrayList;
import java.util.Map;

import fr.fms.entities.Article;
import fr.fms.entities.OrderItem;

public interface IE_Services {

	// lister tous les articles
	public ArrayList<Article> readAll();

	// voir un article
	public Article read(long id);

	// voir les articles d'une catégorie
	public ArrayList<Article> readAllCategoryArticle(long idCategory);

	// ajouter un article au panier
	public boolean addOrderItem(long idArticle);

	// voir le panier => {hashMap}
	public void readCart();

	// enlever un article du panier
	public void deleteItemCart(long id);

	// payer => {hashMap}
	public double pay();

	// stocker la commande dans la BD => store order & all orderItems
	public void createOrder(Map<Long, OrderItem> cart, long idCustomer);

}
