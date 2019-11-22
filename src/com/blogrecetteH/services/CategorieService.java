/**
 * 
 */
package com.blogrecetteH.services;


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
import com.blogrecetteH.models.Membre;
import com.blogrecetteH.models.Recette;
import com.blogrecetteH.utils.HibernateUtil;

/**
 * @author HB
 *
 */
public class CategorieService {
	


	
	
	public CategorieService() {
		// TODO Auto-generated constructor stub
	}


	// -------fonction get from id --------//
	public Categorie getCategorieFromId(int id) throws SQLException {
		
		Categorie categorie = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			categorie = session.get(Categorie.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categorie;
	}
	
	
	// -------fonction create --------//
	public Categorie createCategorie(Categorie categorie) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (categorie != null) {
				session.createQuery("categorie");
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return categorie;
	}
	
	
	// -------fonction getALL --------//
		public List<Categorie> getAllCategorie() throws Exception {
			
			List < Categorie > listOfCategorie = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				String hql= "from Categorie";
				Query query = session.createQuery(hql);
				listOfCategorie = (ArrayList<Categorie>)query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return listOfCategorie;
		}

		// -------fonction delete --------//

		public void deleteCategorie(Categorie categorie) throws SQLException {
			Transaction transaction = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				if (categorie != null) {
					session.delete(categorie);
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

		public void updateCategorie(Categorie categorie) throws SQLException {
			Transaction transaction = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				if (categorie != null) {
					session.update(categorie);
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
	public Categorie createCategorie(Categorie categorie) throws SQLException {
		String query = "INSERT INTO categorie (nom) " + "VALUES (?)";

		PreparedStatement pStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		pStatement.setString(1, categorie.getNom());
		pStatement.executeUpdate();

		ResultSet rSet = pStatement.getGeneratedKeys();
		if (rSet.next()) {
			categorie.setId(rSet.getInt(1));
		}

		return categorie;
	}

	public Categorie getCategorieFromId(int id) throws SQLException {
		Categorie categorie = null;
		String query = "SELECT * FROM categorie WHERE id= ? ";

		PreparedStatement pStatement = connection.prepareStatement(query);

		pStatement.setInt(1, id);

		ResultSet rSet = pStatement.executeQuery();

		if (rSet.next()) {

			categorie = new Categorie();

			categorie.setId(rSet.getInt("id"));
			categorie.setNom(rSet.getString("nom"));
		}

		return categorie;
	}


	public List<Categorie> getAll() throws SQLException {
		List <Categorie> categories = new ArrayList<>();


		String query = "SELECT * FROM categorie " ; 
		PreparedStatement pStatement = connection.prepareStatement (query);
		ResultSet rSet = pStatement.executeQuery();

		while (rSet.next()) {
			Categorie categorie = new Categorie();

			categorie.setId(rSet.getInt("id"));
			categorie.setNom(rSet.getString("nom"));


			categories.add(categorie);

		}
		return categories;
	}


	public void updateCategorie(Categorie categorie) throws SQLException {
		String query = "UPDATE categorie set Nom =?  WHERE id =? ";

		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setString(1, categorie.getNom());
		pStatement.setInt(2, categorie.getId());
		pStatement.executeUpdate();
	}

	public void deleteCategorie(Categorie categorie) throws SQLException {

		String query = "delete from categorie where id = ?";

		PreparedStatement pStatement = connection.prepareStatement(query);

		pStatement.setInt(1, categorie.getId());

		pStatement.executeUpdate();

	}

}
*/
