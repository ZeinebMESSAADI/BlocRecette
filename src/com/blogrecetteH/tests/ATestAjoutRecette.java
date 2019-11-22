/**
 * 
 */
package com.blogrecetteH.tests;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.blogrecetteH.models.Ingredient;
import com.blogrecetteH.models.Recette;

/**
 * @author HB
 *
 */
public class ATestAjoutRecette {
	
	/**
	 * @author HB
	 *
	 */
	public static void main(String args[]) throws Exception {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		SessionFactory sessionFactory = sf;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Recette recette = new Recette(1,1,"Tartiflette","Recette de nos ancètres","Tartiflette_img",new Date());
			Recette recette1 = new Recette(1,1,"omlette","Recette vite fait","Omlette_img",new Date());
			session.save(recette);
			session.save(recette1);
			session.flush();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		sessionFactory.close();
	}
}
