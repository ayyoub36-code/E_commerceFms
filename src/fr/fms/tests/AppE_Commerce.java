package fr.fms.tests;

import java.util.Scanner;

import fr.fms.business.IE_ServicesImpl;
import fr.fms.dao.UserDao;

public class AppE_Commerce {

	public static UserDao daoUser = new UserDao();
	public static IE_ServicesImpl services = new IE_ServicesImpl();

	public static void main(String[] args) {
		// TODO requette pour aller chercher les customer qu'un user à !!
		Scanner scan = new Scanner(System.in);

		while (UserAuth.isConnected(scan, daoUser) == false) // verif connection
			UserAuth.isConnected(scan, daoUser);

		shoping(scan, services);
	}

	private static void shoping(Scanner scan, IE_ServicesImpl services) {
		long idCustomer = 0;
		int input = 0;
		while (input != 7) {
			menuWelcom(); // afficher le menu pricipal
			input = scan.nextInt();
			switch (input) {
			case 1: // lister les articles
				services.readAll().forEach(e -> System.out.println(e));
				break;
			case 2: // afficher un article
				System.out.println("Veuillez saisir l'id du produit qui vous interesse :");
				long idArticle = scan.nextLong();
				System.out.println(services.read(idArticle));
				break;
			case 3: // ajouter au panier
				System.out.println("Veuillez saisir l'id du produit que vous voulez ajouter :");
				idArticle = scan.nextLong();
				if (services.addOrderItem(idArticle))
					System.out.println(
							"L'article " + services.read(idArticle).getDescription() + " à été ajouter à votre panier");
				break;
			case 4: // enlever du panier
				System.out.println("Veuillez saisir l'id du produit que vous voulez enlever :");
				idArticle = scan.nextInt();
				services.deleteItemCart(idArticle);
				break;
			case 5: // voir le panier
				services.readCart();
				break;
			case 6: // choisir un compte client
				System.out.println("Choisissez dans la liste le client et l 'adresse :\n");
				daoUser.getUserCustomers(daoUser.getUser().getId()).forEach((e -> System.out.println(e)));
				idCustomer = scan.nextLong();
				break;
			case 7: // payer
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
		System.out.println(
				"\nBienvenu sur notre platforme de vente d'article IT !\n1/ Liste des produits\n2/ Afficher les détails d'un produit\n3/ Ajouter un produits au panier\n4/ Enlever un produit du panier\n5/ Voir le panier\n6/ choisir l'adresse de livraison\n7/ Passer la commande\n");
	}

}