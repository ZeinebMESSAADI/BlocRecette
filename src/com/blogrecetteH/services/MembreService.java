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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.blogrecetteH.models.Commentaire;
import com.blogrecetteH.models.Ingredient;
import com.blogrecetteH.models.Membre;
import com.blogrecetteH.models.Recette;
import com.blogrecetteH.utils.HibernateUtil;

/**
 * @author HB
 *
 */
public class MembreService {

	public MembreService( ) { // pour obliger à se connecter directement
	}
	
	// -------fonction create --------//

		public Membre createMembre(Membre membre) throws SQLException {
			Transaction transaction = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				if (membre != null) {
					session.createQuery("membre");
					session.flush();
				}
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
			return membre;
		}

	//-------fonction get from id --------//
	public Membre getMembreFromId(int id) throws SQLException {
		Membre membre = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			membre = session.get(Membre.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return membre;
	}

	// -------fonction getALL --------//
	public List<Membre> getAllMembre() throws Exception {
		
		List < Membre > listOfMembre = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			listOfMembre = session.createQuery("from Membre").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfMembre;
	}

	// -------fonction delete --------//

	public void deleteMembre(Membre membre) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (membre != null) {
				session.delete(membre);
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

		public void updateMembre(Membre membre) throws SQLException {
			Transaction transaction = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				if (membre != null) {
					session.update(membre);
				}
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
		}
		
		
		// -------fonction selectMembreByPseudoMdp --------//
		public Membre selectMembreByPseudoMdp(String pseudo, String mdp) {//Il faudra cr�er un objet utilisateur afin d'affeter les r�sultats de la ligne � ce dernier
			Membre membre = null;
			 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				 String hql = "SELECT m FROM Membre m WHERE m.pseudo = :pseudo AND m.mdp = :mdp";
				 Query query = session.createQuery(hql);
				 query.setParameter("pseudo", pseudo);
				 query.setParameter("mdp", mdp);
				 ArrayList<Membre> membres = (ArrayList<Membre>) query.getResultList();
				 if(membres.isEmpty()) {
					 membre = null;
				 } else {
					 membre = membres.get(0);
				 }
				 
			 } catch (Exception e) {
		            e.printStackTrace();
		     }
			return membre;

		}	
}

/*public Membre createMember(Membre member) throws SQLException {
		String query = "INSERT INTO membre (nom, pseudo,mdp,email,dateInscription) " + "VALUES (?, ?, ?, ?, ?)";
		//INSERT INTO membre et non pas member parceque dans notre base est membre et non pas member

		// Prépare la requête et demande la récupération de la clé générée
		PreparedStatement pStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		// Renseigne les valeurs
		pStatement.setString(1, member.getNom());
		pStatement.setString(2, member.getPseudo());
		pStatement.setString(3, member.getMdp());
		pStatement.setString(4, member.getEmail());
		pStatement.setDate(5, new java.sql.Date(member.getDateInscription().getTime()));

		pStatement.executeUpdate(); // pour update,inserer, supprimer on utilise executeUpdate

		// Récupere la clé sous forme de ResulSet
		ResultSet rSet = pStatement.getGeneratedKeys(); // objet qui permet de contenir toutes les données à l'intérieur
		if (rSet.next()) {
			member.setId(rSet.getInt(1));
		}
		return member;
	}

	public Membre getMemberFromId(int id) throws SQLException {
		Membre member = null;
		String query = "SELECT * FROM membre WHERE id= ? ";
		PreparedStatement pStatement = connection.prepareStatement(query);

		pStatement.setInt(1, id);

		ResultSet rSet = pStatement.executeQuery();

		if (rSet.next()) {

			member = new Membre();

			member.setId(rSet.getInt("id"));
			member.setNom(rSet.getString("nom"));
			member.setPseudo(rSet.getString("pseudo"));
			member.setMdp(rSet.getString("Mdp"));
			member.setEmail(rSet.getString("email"));
			member.setDateInscription(rSet.getDate("dateInscription"));

		}

		return member;
	}

	/*public List<Member> getAll() throws SQLException {
		List<Member> members = new ArrayList<>();

		String query = "SELECT * FROM member ";
		PreparedStatement pStatement = connection.prepareStatement(query);
		ResultSet rSet = pStatement.executeQuery();

		while (rSet.next()) {
			Member member = new Member();
			member.setIdMember(rSet.getInt("idMember"));
			member.setNom(rSet.getString("nom"));
			member.setPseudo(rSet.getString("pseudo"));
			member.setMdp(rSet.getString("Mdp"));
			member.setEmail(rSet.getString("email"));
			member.setDateInscription(rSet.getDate("dateInscription"));

			members.add(member);

		}

		return members;
	}*/

/*public void updateMember(Membre member) throws SQLException {
		String query = "UPDATE membre set nom =? , pseudo =?, mdp =?, email =?, dateInscription =? WHERE id =? ";

		PreparedStatement pStatement = connection.prepareStatement(query);

		pStatement.setString(1, member.getNom());
		pStatement.setString(2, member.getPseudo());
		pStatement.setString(3, member.getMdp());
		pStatement.setString(4, member.getEmail());
		pStatement.setDate(5, new java.sql.Date(member.getDateInscription().getTime()));
		pStatement.setInt(6, member.getId());

		pStatement.executeUpdate();
	}

	public void deleteMember(Membre member) throws SQLException {

		String query = "delete from membre where id = ?";

		PreparedStatement pStatement = connection.prepareStatement(query);

		pStatement.setInt(1, member.getId());

		pStatement.executeUpdate();

	}

}*/
