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

import com.blogrecetteH.models.Categorie;
import com.blogrecetteH.models.Commentaire;
import com.blogrecetteH.models.Ingredient;
import com.blogrecetteH.models.Recette;
import com.blogrecetteH.utils.HibernateUtil;

/**
 * @author HB
 *
 */
public class CommentaireService {
	

	private Session session;



	public CommentaireService( ) {
	
	}

	// -------fonction create --------//
	public Commentaire createCommentaire(Commentaire commentaire) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (commentaire != null) {
				session.save(commentaire);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return commentaire;
	}
	
	// -------fonction get from id --------//
	public Commentaire getCommentaireFromId(int id) throws SQLException {
	
		Commentaire commentaire = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			commentaire = session.get(Commentaire.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentaire;
	}

	// -------fonction getCommentaireByRecette--------//
	public List<Commentaire> getCommentaireByRecette(int idRecette) throws Exception {     //selectionne tout les commentaire des recette
	      
	       List<Commentaire> commentaireByrecettes = null;
	       try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	   Query query =  session.createQuery("SELECT c from Commentaire c join c.recette r WHERE r.id = :id");
	    	   // On veut selectionner tous les commentaires dans la recette where idrecette=idcommentaire
	    	   query.setParameter("id", idRecette);
	    	  commentaireByrecettes = query.getResultList();
	       } catch (Exception e) {
	           e.printStackTrace(); 
	       }
	       return commentaireByrecettes;
	   }
	// -------fonction getALL --------//
	public List<Commentaire> getAllCommentaire() throws Exception {
	
		List < Commentaire > listOfCommentaire = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql= "from Commentaire";
			Query query = session.createQuery(hql);
			listOfCommentaire = (ArrayList<Commentaire>)query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			
			e.printStackTrace();
		}
		return listOfCommentaire;
	}

	// -------fonction delete --------//

	public void deleteCommentaire(Commentaire commentaire) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (commentaire != null) {
				session.delete(commentaire);
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

	public void updateCommentaire(Commentaire commentaire) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (commentaire != null) {
				session.update(commentaire);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	

}

/*
 * public Commentaire createCommentaire(Commentaire commentaire) throws
 * SQLException {
 * 
 * String query =
 * "INSERT INTO commentaire (idRecette, auteur, contenu, note, dateCreation) " +
 * "VALUES (?, ?, ?, ?, ?)";
 * 
 * PreparedStatement pStatement = connection.prepareStatement(query,
 * Statement.RETURN_GENERATED_KEYS); pStatement.setInt(1,
 * commentaire.getIdRecette()); pStatement.setString(2,
 * commentaire.getAuteur()); pStatement.setString(3, commentaire.getContenu());
 * pStatement.setInt(4, commentaire.getNote()); pStatement.setDate(5, new
 * java.sql.Date(commentaire.getDateCreation().getTime()));
 * 
 * pStatement.executeUpdate();
 * 
 * ResultSet rSet = pStatement.getGeneratedKeys(); if (rSet.next()) {
 * commentaire.setId(rSet.getInt(1)); }
 * 
 * return commentaire; }
 * 
 * public Commentaire getCommentaireFromId(int id) throws SQLException {
 * Commentaire commentaire = null;
 * 
 * String query = "SELECT * FROM commentaire WHERE id= ? ";
 * 
 * PreparedStatement pStatement = connection.prepareStatement(query);
 * 
 * pStatement.setInt(1, id);
 * 
 * ResultSet rSet = pStatement.executeQuery();
 * 
 * if (rSet.next()) {
 * 
 * commentaire = new Commentaire(); commentaire.setId(rSet.getInt("id"));
 * commentaire.setIdRecette(rSet.getInt("idRecette"));
 * commentaire.setAuteur(rSet.getString("auteur"));
 * commentaire.setContenu(rSet.getString("contenu"));
 * commentaire.setNote(rSet.getInt("note"));
 * commentaire.setDateCreation(rSet.getDate("dateCreation"));
 * 
 * } return commentaire; }
 * 
 * public void updateCommentaire(Commentaire commentaire) throws SQLException {
 * String query =
 * "UPDATE commentaire set idRecette =? , auteur =?, contenu =?, note =?, dateCreation=?  WHERE id =? "
 * ;
 * 
 * PreparedStatement pStatement = connection.prepareStatement(query);
 * pStatement.setInt(1, commentaire.getIdRecette()); pStatement.setString(2,
 * commentaire.getAuteur()); pStatement.setString(3, commentaire.getContenu());
 * pStatement.setInt(4, commentaire.getNote()); pStatement.setDate(5, new
 * java.sql.Date(commentaire.getDateCreation().getTime())); pStatement.setInt(6,
 * commentaire.getId());
 * 
 * pStatement.executeUpdate(); }
 * 
 * public void deleteCommentaire(Commentaire commentaire) throws SQLException {
 * 
 * String query = "delete from commentaire where id = ?";
 * 
 * PreparedStatement pStatement = connection.prepareStatement(query);
 * 
 * pStatement.setInt(1, commentaire.getId());
 * 
 * pStatement.executeUpdate();
 * 
 * }
 * 
 * public List<Commentaire> getCommentairesByIdRecette(int idRecette) throws
 * SQLException {
 * 
 * List<Commentaire> commentaires = new ArrayList<Commentaire>();
 * 
 * String query = "SELECT * FROM commentaire where idRecette=?";
 * PreparedStatement pStatement = connection.prepareStatement(query);
 * pStatement.setInt(1, idRecette); ResultSet rSet = pStatement.executeQuery();
 * 
 * while (rSet.next()) { Commentaire commentaire = new Commentaire();
 * 
 * commentaire.setId(rSet.getInt("id"));
 * commentaire.setIdRecette(rSet.getInt("idRecette"));
 * commentaire.setAuteur(rSet.getString("auteur"));
 * commentaire.setContenu(rSet.getString("contenu"));
 * commentaire.setNote(rSet.getInt("note"));
 * commentaire.setDateCreation(rSet.getDate("dateCreation"));
 * 
 * commentaires.add(commentaire);
 * 
 * }
 * 
 * return commentaires; }
 * 
 * public int moyenneCommentaire(int idRecette) throws SQLException { String
 * query= "SELECT floor(AVG(note)) as moy FROM commentaire WHERE idRecette=? ";
 * PreparedStatement ps = connection.prepareStatement(query); int moy = 0;
 * ps.setInt(1, idRecette ); ResultSet rSet= ps.executeQuery(); if (rSet.next())
 * { moy=rSet.getInt("moy"); } return moy; } }
 */