package fr.fms.tests;

import java.util.Scanner;

import fr.fms.business.IE_ServicesImpl;
import fr.fms.dao.DAO;
import fr.fms.dao.DAOFactory;
import fr.fms.dao.UserDao;
import fr.fms.entities.Category;
import fr.fms.entities.User;

public class AppE_Commerce {

	// TODO modéliser le transfert d une commande DB

	public static DAO<User> daoUser = DAOFactory.getUserDao();
	public static IE_ServicesImpl services = new IE_ServicesImpl();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while (UserAuth.isConnected(scan, daoUser) == false) // verif connection
			UserAuth.isConnected(scan, daoUser);

		shoping(scan, services);
	}

	private static void shoping(Scanner scan, IE_ServicesImpl services) {
		long idCustomer = 0;
		int input = 0;
		while (input != 9) {
			menuWelcom(); // afficher le menu pricipal
			input = scan.nextInt();
			switch (input) {
			case 1: // lister les articles
				services.readAll().forEach(e -> System.out.println(e));
				break;
			case 2: // afficher un article & sa category name
				System.out.println("Veuillez saisir l'id du produit qui vous interesse :");
				long idArticle = scan.nextLong();
				Category category = services.readCategory(services.read(idArticle).getIdCategory());
				System.out.println(services.read(idArticle) + " Catégorie : " + category.getName());
				break;

			case 3: // lister toutes les categories
				System.out.println("Liste des catégories de notre boutique :");
				services.readAllCategory().forEach(e -> System.out.println(e));
				break;

			case 4: // afficher les articles par category avec possibilité d ajout au panier
				System.out.println("Veuillez saisir l'id de la catégorie :");
				long idCategory = scan.nextLong();
				System.out.println("Liste des articles de cette catégorie : \n");
				services.readAllCategoryArticle(idCategory).forEach(c -> System.out.println(c));
				break;

			case 5: // ajouter au panier
				System.out.println("Veuillez saisir l'id du produit que vous voulez ajouter :");
				idArticle = scan.nextLong();
				if (services.addOrderItem(idArticle))
					System.out.println(
							"L'article " + services.read(idArticle).getDescription() + " à été ajouter à votre panier");
				break;
			case 6: // enlever du panier
				System.out.println("Veuillez saisir l'id du produit que vous voulez enlever :");
				idArticle = scan.nextInt();
				services.deleteItemCart(idArticle);
				break;
			case 7: // voir le panier
				services.readCart();
				break;
			case 8: // choisir un compte client
				System.out.println("Choisissez dans la liste le client et l 'adresse :\n");
				((UserDao) daoUser).getUserCustomers(((UserDao) daoUser).getUser().getId())
						.forEach((e -> System.out.println(e)));
				idCustomer = scan.nextLong();
				break;
			case 9: // payer
				services.readCart();
				System.out.println("Montant de la commande : " + services.pay() + "€");
				System.out.println("Saisissez le montant à payer à l'abri des regards !");
				double amount = scan.nextDouble();
				if (amount == services.pay())
					System.out.println("Vous serez livré un lundi à 8h");
				// stocker la commande
				services.createOrder(services.getCart(), idCustomer);
				break;
			default:
				System.out.println("Saisissez une valeur valide !");
				break;
			}
		}
	}

	private static void menuWelcom() {
		System.out.println("\nBienvenu sur notre platforme de vente d'article IT !" + "\n1/ Liste des produits"
				+ "\n2/ Afficher les détails d'un produit" + "\n3/ Afficher la liste des catégories"
				+ "\n4/ Afficher les articles par categorie" + "\n5/ Ajouter un produits au panier"
				+ "\n6/ Enlever un produit du panier" + "\n7/ Voir le panier" + "\n8/ choisir l'adresse de livraison"
				+ "\n9/ Passer la commande\n");
	}

}