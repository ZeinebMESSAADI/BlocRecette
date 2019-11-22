/**
 * 
 */
package com.blogrecetteH.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.blogrecetteH.models.Recette;
import com.blogrecetteH.models.Tag;
import com.blogrecetteH.utils.HibernateUtil;

/**
 * @author HB
 *
 */
public class TagService {

	
	// -------fonction create --------//

	public Tag createTag(Tag tag) {
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (tag != null) {
               session.save(tag);
               session.flush();
            }
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
            e.printStackTrace();
        }
        return tag;

	}

		//-------fonction get from id --------//
	public Tag getTagById(int id) throws Exception{//TESTED
	 	Tag tag = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	tag = session.get(Tag.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tag;
	}
		
		
		// -------fonction getALL --------//
		public ArrayList<Tag> getAllTags() {
			ArrayList<Tag>allTags = new ArrayList<Tag>();
			 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				 String hql = "from Tag";
				 Query query = session.createQuery(hql);
				 allTags = (ArrayList<Tag>) query.getResultList();
			 } catch (Exception e) {
		            e.printStackTrace();
		     }
			return allTags;

		}


		// -------fonction delete --------//

		public void deleteTag(Tag tag) {
			Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            if (tag != null) {
	               session.remove(tag);
	               session.flush();
	            }
	            transaction.commit();
	        } catch (Exception e) {
	        	if(transaction != null) {
	        		transaction.rollback();
	        	}
	            e.printStackTrace();
	        }

		}

		// -------fonction update --------//

		public void updateTag(Tag tag) throws SQLException {
			Transaction transaction = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				if (tag != null) {
					session.update(tag);
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
