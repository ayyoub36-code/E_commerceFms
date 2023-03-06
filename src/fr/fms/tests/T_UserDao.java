package fr.fms.tests;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.fms.bdd.BddConnection;
import fr.fms.dao.DAO;
import fr.fms.dao.DAOFactory;
import fr.fms.entities.User;

public class T_UserDao {

	public static void main(String[] args) {

		DAO<User> dao = DAOFactory.getUserDao();

		dao.readAll().forEach(e -> System.out.println(e));

		User user1 = new User("cesar", "avécesar");
		// dao.create(user1);

		// update user
//		User userToModif = dao.read(getCount());
//		userToModif.setPassword("avecesarleplusbeau");
//		dao.update(userToModif);

		// delete
		if (dao.delete(2)) {
			System.out.println("suppression réussie !");
		}

	}

	public static int getCount() {
		int num = 0;
		String sql = "SELECT COUNT(*) FROM t_users;";
		try {
			PreparedStatement ps = BddConnection.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int rs_Count = rs.getInt(1);
				num = rs_Count;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

}
