package fr.fms.tests;

import fr.fms.bdd.BddSingleton;

public class TestSingleton {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BddSingleton x = BddSingleton.getInstance();
		BddSingleton y = BddSingleton.getInstance();

		System.out.println(x.hashCode());
		System.out.println(y.hashCode());

	}

}
