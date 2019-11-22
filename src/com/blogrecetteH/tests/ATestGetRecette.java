/**
 * 
 */
package com.blogrecetteH.tests;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * @author HB
 *
 */
public class ATestGetRecette {

	/**
	 * @param args
	 */
	public static void main(String args[]) throws Exception {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		SessionFactory sessionFactory = sf;
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			//String hql = "FROM Recette recette  "; on selectionne tous les ingredients dans notre BDD
			String hql = "FROM Recette recette where id=3 ";
			Query query = session.createQuery(hql);
			List results = query.getResultList();
			System.out.println(results);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

}
