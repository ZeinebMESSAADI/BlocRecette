package com.blogrecetteH.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogrecetteH.models.Ingredient;
import com.blogrecetteH.models.Recette;
import com.blogrecetteH.services.IngredientService;
import com.blogrecetteH.services.RecetteService;


import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestIngredientService extends TestCase {
	
	
	private static IngredientService ingredientService = null;
	private static Ingredient ingredientTest = null;

	public TestIngredientService() {

	}
	
	public TestIngredientService(String testName) {
		super (testName);
	}
	
	
	
	
	
	@Before
	public void setUp() throws Exception {
		
		
		ingredientService = new IngredientService();
		if (ingredientTest == null) {
			ingredientTest = new Ingredient(1,"Pate", 2, "Kg");

		}
		
	}

	@After
	public void tearDown() throws Exception {
		
		ingredientService = null;
		
	}

	@Test
	public void testCreateIngredient() throws SQLException {
		ingredientTest = ingredientService.createIngredient(ingredientTest);
		Ingredient ingredientCree  = ingredientService.getIngredientFromId(ingredientTest.getId());
		assertNotEquals((long)0, (long)ingredientTest.getId());
		assertEquals (ingredientTest.getNom(), ingredientTest.getNom());
		assertEquals (ingredientTest.getQuantite(), ingredientTest.getQuantite());
		assertEquals (ingredientTest.getUnit(), ingredientTest.getUnit());
		
	}
	
	@Test
	public void tesGetIngredientFromId() throws SQLException {
		
		Ingredient ingredientFromId = ingredientService.getIngredientFromId(ingredientTest.getId());
		assertEquals(ingredientTest.getIdRecette() , ingredientFromId.getIdRecette());
		
	}

	@Test
	public void testUpdateIngredient() throws SQLException {
		ingredientTest.setIdRecette(1);
		ingredientTest.setNom("Legume");
		ingredientTest.setQuantite(3);
		ingredientTest.setUnit("g");
		
		
		//action
		ingredientService.updateIngredient(ingredientTest);
		Ingredient ingredientUpdate = ingredientService.getIngredientFromId(ingredientTest.getId());
		
		//assert
		assertEquals(ingredientUpdate.getIdRecette(), ingredientTest.getIdRecette());
		assertEquals(ingredientUpdate.getNom(), ingredientTest.getNom());
		assertEquals(ingredientUpdate.getQuantite(), ingredientTest.getQuantite());
		assertEquals(ingredientUpdate.getUnit(), ingredientTest.getUnit());
		
	}
	
	
	@Test
	public void testDeleteIngredient() throws SQLException {
		// Créer un jeu de test (Arrange)
		//action
		ingredientService.deleteIngredient(ingredientTest); // on la supprime
		ingredientTest = ingredientService.getIngredientFromId(ingredientTest.getId()); 
																																			
		// Assert
		assertNull(ingredientTest);
	}
	
	public static junit.framework.Test suite() {

		TestSuite suite = new TestSuite("Suite TestIngredientService");
		suite.addTest(new TestIngredientService("testCreateIngredient"));
		suite.addTest(new TestIngredientService("tesGetIngredientFromId"));
		suite.addTest(new TestIngredientService("testUpdateIngredient"));
		suite.addTest(new TestIngredientService("testDeleteIngredient"));
		return suite;

	}
	
}
