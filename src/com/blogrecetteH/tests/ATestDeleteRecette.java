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

import com.blogrecetteH.models.Membre;
import com.blogrecetteH.models.Recette;

/**
 * @author HB
 *
 */
public class ATestDeleteRecette {

	/**
	 * @param args
	 */
	public static void main(String args[]) throws Exception  {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		SessionFactory sessionFactory = sf;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		
		tx = session.beginTransaction();
		
		//String hql = "FROM Recette  where id=2";
		//Query query = session.createQuery(hql);
		//List avantSuppression = query.getResultList();
		
		Recette recette = (Recette) session.load(Recette.class, 2);
		session.delete(recette);
		session.flush();
		tx.commit();
		
		//Query query2 = session.createQuery(hql);
		//List apresSuppression = query2.getResultList();

	}

}
