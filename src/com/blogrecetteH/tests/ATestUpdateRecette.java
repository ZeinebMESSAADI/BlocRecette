/**
 * 
 */
package com.blogrecetteH.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.blogrecetteH.models.Membre;
import com.blogrecetteH.models.Recette;

/**
 * @author HB
 *
 */
public class ATestUpdateRecette {

	/**
	 * @param args
	 */
	public static void main(String args[]) throws Exception  {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		SessionFactory sessionFactory = sf;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		tx = session.beginTransaction();
		Recette recette = (Recette) session.load(Recette.class, 3);
		recette.setTitre("Omlette mis à jour");
		
		tx.commit();

	}

}
