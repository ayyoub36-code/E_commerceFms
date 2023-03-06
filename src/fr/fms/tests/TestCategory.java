package fr.fms.tests;

import fr.fms.dao.DAO;
import fr.fms.dao.DAOFactory;
import fr.fms.entities.Category;

public class TestCategory {

	public static void main(String[] args) {
		DAO<Category> dao = DAOFactory.getCategoryDao();

		dao.readAll().forEach(e -> System.out.println(e));
//		dao.create(new Category("Imprimantes", "Pour toutes impressions "));
//		dao.readAll().forEach(e -> System.out.println(e));
		System.out.println("-----------------------");
		System.out.println(dao.read(4));
//		dao.delete(5);
//		dao.delete(6);

	}

}
