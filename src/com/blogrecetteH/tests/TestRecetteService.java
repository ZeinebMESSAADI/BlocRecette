/**
 * 
 */
package com.blogrecetteH.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogrecetteH.models.Membre;
import com.blogrecetteH.models.Recette;
import com.blogrecetteH.services.RecetteService;
import com.blogrecetteH.utils.HibernateUtil;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author HB
 *
 */
public class TestRecetteService extends TestCase {
	private static SessionFactory sessionFactory = null;
	private static RecetteService recetteService = null;
	private static Recette recetteTest = null;

	public TestRecetteService() {

	}
	
	public TestRecetteService(String testName) {
		super (testName);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		sessionFactory = HibernateUtil.getSessionFactory();
		recetteService = new RecetteService();

		if (recetteTest == null) {
			recetteTest = new Recette(1, 1, "Pizza", "sur le bois", "Pizza_img", new Date());

		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		recetteService = null;
	}

	@Test
	public void testCreateRecette() throws SQLException {
		// Créer un jeu de test (Arrange)
		// Action
		recetteTest = recetteService.createRecette(recetteTest);
		Recette recetteCree = recetteService.getRecetteFromId(recetteTest.getId());
		// assert

		assertNotEquals((long)0, (long)recetteTest.getId());
		assertEquals(recetteTest.getIdMembre(), recetteCree.getIdMembre());
		assertEquals(recetteTest.getIdCategorie(), recetteCree.getIdCategorie());
		assertEquals(recetteTest.getTitre(), recetteCree.getTitre());
		assertEquals(recetteTest.getDescription(), recetteCree.getDescription());
		assertEquals(recetteTest.getPhoto(), recetteCree.getPhoto());
		assertEquals(recetteTest.getDateCreation().getDate(), recetteCree.getDateCreation().getDate());
	
	}
	
	@Test
	
	public void tesGetRecetteFromId() throws SQLException {
		
		
		Recette recetteFromId = recetteService.getRecetteFromId(recetteTest.getId());
		assertEquals(recetteTest.getIdMembre() , recetteFromId.getIdMembre());
		assertEquals(recetteTest.getIdCategorie() , recetteFromId.getIdCategorie());
		assertEquals(recetteTest.getTitre(), recetteFromId.getTitre());
		assertEquals(recetteTest.getDescription(), recetteFromId.getDescription());
		assertEquals(recetteTest.getPhoto(), recetteFromId.getPhoto());
		assertEquals(recetteTest.getDateCreation().getDate(), recetteFromId.getDateCreation().getDate());
	}
	
	
	@Test
	public void testUpdateRecette() throws SQLException {
		Date newDate = new Date("2019/11/05");
		// Créer un jeu de test (Arrange)
		
		recetteTest.setIdMembre(1);
		recetteTest.setIdCategorie(2);
		recetteTest.setTitre("pizzaTest");
		recetteTest.setDescription("pizzaHot");
		recetteTest.setPhoto("Image_test");
		recetteTest.setDateCreation(newDate);
		
		//action
		recetteService.updateRecette(recetteTest);
		Recette recetteUpdate = recetteService.getRecetteFromId(recetteTest.getId());
		
		//assert
		assertEquals(recetteUpdate.getIdMembre(), recetteTest.getIdMembre());
		assertEquals(recetteUpdate.getIdCategorie(), recetteTest.getIdCategorie());
		assertEquals(recetteUpdate.getTitre(), recetteTest.getTitre());
		assertEquals(recetteUpdate.getDescription(), recetteTest.getDescription());
		assertEquals(recetteUpdate.getPhoto(), recetteTest.getPhoto());
		assertEquals(recetteUpdate.getDateCreation().getDate(), recetteTest.getDateCreation().getDate());
		
	}

	@Test
	public void testDeleteRecette() throws SQLException {
		// Créer un jeu de test (Arrange)
		//action
		recetteService.deleteRecette(recetteTest); // on la supprime
		recetteTest = recetteService.getRecetteFromId(recetteTest.getId()); 
																				
																				
		// Assert
		assertNull(recetteTest);
	}
	
	
		
		
	public static junit.framework.Test suite() {

		TestSuite suite = new TestSuite("Suite TestRecetteService");
		suite.addTest(new TestRecetteService("testCreateRecette"));
		suite.addTest(new TestRecetteService("tesGetRecetteFromId"));
		suite.addTest(new TestRecetteService("testUpdateRecette"));
		suite.addTest(new TestRecetteService("testDeleteRecette"));
		return suite;

	}
	
}
