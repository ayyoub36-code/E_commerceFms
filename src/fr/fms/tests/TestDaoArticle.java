package fr.fms.tests;

import java.sql.Connection;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.DAO;
import fr.fms.dao.DAOFactory;
import fr.fms.entities.Article;

public class TestDaoArticle {

	public static void main(String[] args) {
		DAO<Article> dao = DAOFactory.getArticleDao();

		Connection cn1 = DAO.getConnection();
		Connection cn2 = DAO.getConnection();

		System.out.println(cn1.hashCode());
		System.out.println(cn2.hashCode());
		// afficher tous les articles
		dao.readAll().forEach(e -> System.out.println(e));

		// afficher un article
		System.out.println("\n" + dao.read(2));

		// creer un article
		Article article1 = new Article("clavier_pro_2", "linke", 175, 2);
		// dao.create(article1);

		// update un article
		Article article2 = dao.read(13);
		article2.setBrand("linked_out");
		article2.setPrice(91);
		if (dao.update(article2))
			System.out.println("article mis à jour !");
		// articles d'une category
		System.out.println("----------articles category--------------");
		((ArticleDao) dao).readAllCategory(1).forEach(e -> System.out.println(e));

		// supprimer un article
//		if (dao.delete(15))
//			System.out.println("article supprimer ");

	}

}
