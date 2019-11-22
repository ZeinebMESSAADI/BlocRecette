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
import com.blogrecetteH.models.Recette;
import com.blogrecetteH.utils.HibernateUtil;

/**
 * @author HB
 *
 */
public class IngredientService {
	

	
	// -------fonction create --------//

		public  Ingredient createIngredient(Ingredient ingredient) throws SQLException {
			Transaction transaction = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				if (ingredient != null) {
					session.createQuery("ingredient");
					session.flush();
				}
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
			return ingredient;
		}
	//-------fonction get from id --------//
	public Ingredient getIngredientFromId(int id) throws SQLException {
		
		Ingredient ingredient = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			ingredient = session.get(Ingredient.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ingredient;
	}

	// -------fonction getIngredientByRecette --------//
	
	public ArrayList<Ingredient> getIngredientByRecette(int idRecette) throws SQLException {     //selectionne tout les commentaire des recette
	       
	       ArrayList<Ingredient> ingredientByRecettes = new ArrayList<Ingredient> (); ;
	       try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	           
	           String hql = "from Ingredient ingredient join ingredient.recette recette where recette.id = :id";
	           Query query = session.createQuery(hql);
	           query.setParameter("id", idRecette);
	           ingredientByRecettes = (ArrayList<Ingredient>) query.getResultList();
	       } catch (Exception e) {
	           e.printStackTrace();
	           e.printStackTrace();
	       }
	       return ingredientByRecettes;
	    }
	
	// -------fonction getALL --------//
	public List<Ingredient> getAllIngredient() throws Exception {
		
		List < Ingredient > listOfIngredient = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql= "from Ingredient";
			Query query = session.createQuery(hql);
			listOfIngredient = (ArrayList<Ingredient>)query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			e.printStackTrace();
		}
		return listOfIngredient;
	}

	// -------fonction delete --------//

	public void deleteIngredient(Ingredient ingredient) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (ingredient != null) {
				session.delete(ingredient);
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

			public void updateIngredient(Ingredient ingredient) throws SQLException {
				Transaction transaction = null;
				try (Session session = HibernateUtil.getSessionFactory().openSession()) {
					transaction = session.beginTransaction();
					if (ingredient != null) {
						session.update(ingredient);
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


/*public Ingredient createIngredient(Ingredient ingredient) throws SQLException {
		String query = "INSERT INTO ingredient (idRecette, nom,quantite,unit) " + "VALUES (?, ?, ?, ?)";

		PreparedStatement pStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		pStatement.setInt(1, ingredient.getIdRecette());
		pStatement.setString(2, ingredient.getNom());
		pStatement.setInt(3, ingredient.getQuantite());
		pStatement.setString(4, ingredient.getUnit());

		pStatement.executeUpdate();

		ResultSet rSet = pStatement.getGeneratedKeys();
		if (rSet.next()) {
			ingredient.setId(rSet.getInt(1));
		}

		return ingredient;
	}

	public Ingredient getIngredientFromId(int id) throws SQLException {
		Ingredient ingredient = null;

		String query = "SELECT * FROM ingredient WHERE id= ? ";

		PreparedStatement pStatement = connection.prepareStatement(query);

		pStatement.setInt(1, id);

		ResultSet rSet = pStatement.executeQuery();

		if (rSet.next()) {

			ingredient = new Ingredient();

			ingredient.setId(rSet.getInt("id"));
			ingredient.setIdRecette(rSet.getInt("idRecette"));
			ingredient.setNom(rSet.getString("nom"));
			ingredient.setQuantite(rSet.getInt("quantite"));
			ingredient.setUnit(rSet.getString("unit"));

		}

		return ingredient;
	}

	public void updateIngredient(Ingredient ingredient) throws SQLException {
		String query = "UPDATE ingredient set idRecette =? , nom =?, quantite =?, unit =?  WHERE id =? ";

		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, ingredient.getIdRecette());
		pStatement.setString(2, ingredient.getNom());
		pStatement.setInt(3, ingredient.getQuantite());
		pStatement.setString(4, ingredient.getUnit());
		pStatement.setInt(5, ingredient.getId());

		pStatement.executeUpdate();
	}

	public void deleteIngredient(Ingredient ingredient) throws SQLException {

		String query = "delete from ingredient where id = ?";

		PreparedStatement pStatement = connection.prepareStatement(query);

		pStatement.setInt(1, ingredient.getId());

		pStatement.executeUpdate();
	}

	public List<Ingredient> getIngredientsByIdRecette(int idRecette) throws SQLException {
		List<Ingredient> ingretients = new ArrayList<Ingredient>();

		String query = "SELECT * FROM ingredient where idRecette=?" ; 
		PreparedStatement pStatement = connection.prepareStatement (query);
		pStatement.setInt(1, idRecette);
		ResultSet rSet = pStatement.executeQuery();

		while (rSet.next()) {
			Ingredient ingredient = new Ingredient();

			ingredient.setId(rSet.getInt("id"));
			ingredient.setIdRecette(rSet.getInt("idRecette"));
			ingredient.setNom(rSet.getString("nom"));
			ingredient.setQuantite(rSet.getInt("quantite"));
			ingredient.setUnit(rSet.getString("unit"));

			ingretients.add(ingredient);

		}



		return ingretients;
	}

}*/
