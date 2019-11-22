/**
 * 
 */
package com.blogrecetteH.tests;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.blogrecetteH.models.Commentaire;
import com.blogrecetteH.models.Membre;
import com.blogrecetteH.models.Recette;

class TestHibernate {

	protected Session session;
	protected SessionFactory sessionFactory;

	public static void main(String args[]) throws Exception {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		SessionFactory sessionFactory = sf;
		Session session = sessionFactory.openSession();

		// ------------Creation d'une recette ------------//
//		Commentaire commentaire = new Commentaire();
//		session.save(commentaire);
//		
//		
//		// ------------Creation d'une recette ------------//
//		Recette recette = new Recette(1, 1, "pates", "pates au sauce blanche", "pates_img", new Date());
//		session.save(recette);
//
//		// ------------Creation d'une carte d'identite ------------//
//		CarteIdentiteNationale carteIdentiteNationale = new CarteIdentiteNationale(06140);
//		session.save(carteIdentiteNationale);
//
//		// ------------Creation d'un membre ------------//
//		Membre membre = new Membre("Zeineb", "Zooba", "mdpZeineb", "Zeineb@hotmaail.fr", new Date());
//		// recette.setMembre(membre);
//
//		// membre.setCarteIdentiteNationale(carteIdentiteNationale); on a supprimé cette
//		// méthode car il n'y a plus
//
//		session.save(membre);

		sessionFactory.close();

		/*
		 * Transaction tx = null; try { tx = session.beginTransaction(); } catch
		 * (Exception e) { if (tx != null) { tx.rollback(); } throw e; } finally {
		 * session.close(); }
		 * 
		 * sessionFactory.close();
		 */
	}

}
