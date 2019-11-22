/**
 * 
 */
package com.blogrecetteH.tests;

import static org.junit.Assert.*;


import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogrecetteH.models.Categorie;
import com.blogrecetteH.models.Recette;
import com.blogrecetteH.services.CategorieService;
import com.blogrecetteH.utils.HibernateUtil;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author HB
 *
 */
public class TestCategorieService extends TestCase  {
	
	private static SessionFactory sessionFactory = null;

	private static CategorieService categorieService = null;
	private static Categorie categorieTest = null;
	
	public  TestCategorieService(){
		
	}
	
	public TestCategorieService(String testName) {
		super (testName);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		sessionFactory = HibernateUtil.getSessionFactory();
		
		categorieService=new CategorieService();
		
		if (categorieTest ==null) {
			categorieTest=new Categorie("sucre");
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		
		categorieService= null;
	}

	@Test
	public void testCreateCategorie() throws Exception  {
		categorieTest=categorieService.createCategorie(categorieTest);
		Categorie categorieCree = categorieService.getCategorieFromId(categorieTest.getId());
		
		assertNotEquals((long) 0, (long) categorieTest.getId());
		assertEquals(categorieTest.getNom(), categorieCree.getNom());
		
	}

	
	@Test
	public void tesGetCategorieFromId() throws SQLException {
		
		Categorie categorieFromId= categorieService.getCategorieFromId(categorieTest.getId());
		
		assertEquals(categorieTest.getNom(), categorieFromId.getNom());
	}
	

	@Test
	public void testUpdateCategorie() throws Exception {
		// Créer un jeu de test (Arrange)
		categorieTest.setNom("categorieTest");
		//action
		categorieService.updateCategorie(categorieTest);
		Categorie categorieUpdate = categorieService.getCategorieFromId(categorieTest.getId());
		//assert
		assertEquals(categorieUpdate.getNom(), categorieTest.getNom());
		
	}
	
	@Test
	public void testDeleteCategorie() throws Exception {
		categorieService.deleteCategorie(categorieTest);
		categorieTest=categorieService.getCategorieFromId(categorieTest.getId());
	
		assertNull(categorieTest);
		
	}
	
	public static junit.framework.Test suite() {

		TestSuite suite = new TestSuite("Suite TestCategorieService");
		suite.addTest(new TestCategorieService("testCreateCategorie"));
		suite.addTest(new TestCategorieService("tesGetCategorieFromId"));
		suite.addTest(new TestCategorieService("testUpdateCategorie"));
		suite.addTest(new TestCategorieService("testDeleteCategorie"));
		return suite;

	}
	
}
