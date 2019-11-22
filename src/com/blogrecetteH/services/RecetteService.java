/**
 * 
 */
package com.blogrecetteH.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.blogrecetteH.models.Commentaire;
import com.blogrecetteH.models.Ingredient;
import com.blogrecetteH.models.Membre;
import com.blogrecetteH.models.Recette;
import com.blogrecetteH.utils.HibernateUtil;

import jdk.nashorn.internal.objects.annotations.Where;

/**
 * @author HB
 *
 */
public class RecetteService {

	// -------fonction create --------//

	public RecetteService() {
	}

	public Recette createRecette(Recette recette) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.createQuery("recette");
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return recette;
	}

	// -------fonction get from id --------//
	public Recette getRecetteFromId(int id) throws SQLException {

		Recette recette = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			recette = session.get(Recette.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		recette.setMoyenneNote(this.moyNoteRecetteByRecette(recette.getId()));
		
		return recette;
	}

	// -------fonction getRecettesFromIngredient --------//
	public List<Recette> getRecetteByIngredient(int idIngredient) throws SQLException { // recupère toute les recettes
																						// par categorie
		List<Recette> recettebyIngredients = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			recettebyIngredients = session.createQuery("from Recette").getResultList();
		} catch (Exception e) {
			e.printStackTrace();

			e.printStackTrace();
		}
		return recettebyIngredients;
	}

	// -------fonction getRecettesFromCategorie --------//
	public ArrayList<Recette> getRecetteByCategorie(int idCategorie) throws SQLException { // recupère toute les recettes par
		 ArrayList<Recette> recettebyCategories = new ArrayList<Recette> (); ;																			// categorie
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			 String hql = "SELECT recette from Recette recette join recette.categorie categorie where categorie.id = :id";
	           Query query = session.createQuery(hql);
	           query.setParameter("id", idCategorie);
	           recettebyCategories = (ArrayList<Recette>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		
		
		return recettebyCategories;
	}

	// -------fonction getALL --------//
	public List<Recette> getAllRecette() throws Exception {

		List<Recette> listOfRecette = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql= "from Recette";
			Query query = session.createQuery(hql);
			listOfRecette = (ArrayList<Recette>)query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		for (Recette recette:listOfRecette) {
		recette.setMoyenneNote(this.moyNoteRecetteByRecette(recette.getId()));
		
		}
		return listOfRecette;
	}

	// -------fonction delete --------//

	public void deleteRecette(Recette recette) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.delete(recette);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	// -------fonction update --------//

	public void updateRecette(Recette recette) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.update(recette);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	// -------fonction getRecettesFromTag --------//
	public List<Recette> getRecetteByTag(int idTag) throws SQLException { // recupère toute les recettes par categorie

		List<Recette> recettebyTags = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			recettebyTags = session.createQuery("from Tag").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return recettebyTags;
	}
	
	// -------fonction moyenne --------//
	public int moyNoteRecetteByRecette (int idRecette) {
        int note = 0;
        //On prepare le requette HQL
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
             String hql = "Select floor(avg (c.note))"
             		+ " From Recette r join r.commentaires c "
             		+ "where r.id = :id ";
             Query query = session.createQuery(hql);
                query.setParameter("id", idRecette);
               
                try {
   				 note = (int) query.getSingleResult();
   			 } catch (Exception e) {
   			
   				 note = 0;
   			 }
         } catch (Exception e) {
             e.printStackTrace();
        }
        return note;
    }
	
}



/*
 * public Recette createRecette(Recette recette) throws SQLException {
 * 
 * String query =
 * "INSERT INTO recette (idMembre, idCategorie,titre,description,photo,dateCreation) "
 * + "VALUES (?, ?, ?, ?, ?, ?)";
 * 
 * PreparedStatement pStatement = connection.prepareStatement(query,
 * Statement.RETURN_GENERATED_KEYS); pStatement.setInt(1,
 * recette.getIdMembre()); pStatement.setInt(2, recette.getIdCategorie());
 * pStatement.setString(3, recette.getTitre()); pStatement.setString(4,
 * recette.getDescription()); pStatement.setString(5, recette.getPhoto());
 * pStatement.setDate(6, new
 * java.sql.Date(recette.getDateCreation().getTime()));
 * 
 * pStatement.executeUpdate();
 * 
 * ResultSet rSet = pStatement.getGeneratedKeys(); if (rSet.next()) {
 * recette.setId(rSet.getInt(1)); }
 * 
 * return recette; }
 * 
 * 
 * public Recette getRecetteFromId(int id) throws SQLException { Recette
 * recette=null;
 * 
 * String query = "SELECT * FROM recette WHERE id= ? ";
 * 
 * PreparedStatement pStatement = connection.prepareStatement(query);
 * 
 * pStatement.setInt(1, id);
 * 
 * ResultSet rSet = pStatement.executeQuery();
 * 
 * if (rSet.next()) {
 * 
 * recette = new Recette();
 * 
 * 
 * recette.setId(rSet.getInt("id"));
 * recette.setIdMembre(rSet.getInt("idMembre"));
 * recette.setIdCategorie(rSet.getInt("idCategorie"));
 * recette.setTitre(rSet.getString("titre"));
 * recette.setDescription(rSet.getString("description"));
 * recette.setPhoto(rSet.getString("photo"));
 * recette.setDateCreation(rSet.getDate("dateCreation")); }
 * 
 * return recette; }
 * 
 * public List <Recette> getRecettesFromCategorie(int idCategorie) throws
 * SQLException { List <Recette> recettes = new ArrayList<>(); String query =
 * "SELECT * FROM recette  WHERE idCategorie= ?" ;
 * 
 * PreparedStatement pStatement = connection.prepareStatement (query);
 * pStatement.setInt(1, idCategorie);
 * 
 * 
 * ResultSet rSet = pStatement.executeQuery();
 * 
 * while (rSet.next()) { Recette recette = new Recette();
 * 
 * recette.setId(rSet.getInt("id"));
 * recette.setIdMembre(rSet.getInt("idMembre"));
 * recette.setIdCategorie(rSet.getInt("idCategorie"));
 * recette.setTitre(rSet.getString("titre"));
 * recette.setDescription(rSet.getString("description"));
 * recette.setPhoto(rSet.getString("photo"));
 * recette.setDateCreation(rSet.getDate ("dateCreation"));
 * 
 * recettes.add(recette);
 * 
 * } return recettes; }
 * 
 * 
 * public List<Recette> getAll() throws SQLException { List <Recette> recettes =
 * new ArrayList<>();
 * 
 * 
 * String query = "SELECT * FROM recette " ; PreparedStatement pStatement =
 * connection.prepareStatement (query); ResultSet rSet =
 * pStatement.executeQuery();
 * 
 * while (rSet.next()) { Recette recette = new Recette();
 * 
 * recette.setId(rSet.getInt("id"));
 * recette.setIdMembre(rSet.getInt("idMembre"));
 * recette.setIdCategorie(rSet.getInt("idCategorie"));
 * recette.setTitre(rSet.getString("titre"));
 * recette.setDescription(rSet.getString("description"));
 * recette.setPhoto(rSet.getString("photo"));
 * recette.setDateCreation(rSet.getDate ("dateCreation"));
 * 
 * recettes.add(recette);
 * 
 * } return recettes; }
 * 
 * public void updateRecette (Recette recette) throws SQLException { String
 * query =
 * "UPDATE recette set idMembre =? , idCategorie =?, titre =?, description =?,"
 * + " photo =?, dateCreation=?  WHERE id =? ";
 * 
 * PreparedStatement pStatement = connection.prepareStatement(query);
 * pStatement.setInt(1,recette.getIdMembre()); pStatement.setInt(2,
 * recette.getIdCategorie()); pStatement.setString(3, recette.getTitre());
 * pStatement.setString(4, recette.getDescription()); pStatement.setString(5,
 * recette.getPhoto()); pStatement.setDate(6, new
 * java.sql.Date(recette.getDateCreation().getTime())); pStatement.setInt(7,
 * recette.getId());
 * 
 * pStatement.executeUpdate();
 * 
 * 
 * }
 * 
 * 
 * public void deleteRecette(Recette recette) throws SQLException {
 * 
 * String query = "delete from recette where id = ?";
 * 
 * PreparedStatement pStatement = connection.prepareStatement(query);
 * 
 * pStatement.setInt(1, recette.getId());
 * 
 * pStatement.executeUpdate();
 * 
 * }
 * 
 * 
 * }
 */
