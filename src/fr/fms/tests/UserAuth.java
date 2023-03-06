package fr.fms.tests;

import java.util.Scanner;

import fr.fms.dao.DAO;
import fr.fms.dao.UserDao;
import fr.fms.entities.User;

public class UserAuth {

	public static boolean isConnected(Scanner scan, DAO<User> daoUser) {
		System.out.println("veuillez saisir votre login : ");
		String login = scan.next();
		System.out.println("veuillez saisir votre mot de passe : ");
		String password = scan.next();

		if (((UserDao) daoUser).verifUserAuth(login, password)) {

			return true;
		} else
			System.out.println("Veuillez saisir des identifiants valides !\n");
		return false;
	}

}
