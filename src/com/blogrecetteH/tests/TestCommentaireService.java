package com.blogrecetteH.tests;

import static org.junit.Assert.*;


import java.sql.SQLException;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogrecetteH.models.Commentaire;

import com.blogrecetteH.services.CommentaireService;
import com.blogrecetteH.utils.HibernateUtil;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestCommentaireService extends TestCase {
	
	private static SessionFactory sessionFactory = null;
	private static CommentaireService commentaireService = null;
	private static Commentaire commentaireTest = null;

	public TestCommentaireService() {

	}

	public TestCommentaireService(String testName) {
		super(testName);
	}

	@Before
	public void setUp() throws Exception {
		sessionFactory = HibernateUtil.getSessionFactory();
	
		commentaireService = new CommentaireService();

		if (commentaireTest == null) {
			commentaireTest = new Commentaire("TonyMaguire", "tres bon plat", 5, new Date());

		}

	}

	@After
	public void tearDown() throws Exception {
		
		commentaireService = null;
	}

	@Test
	public void testCreateCommentaire() throws SQLException {
		// Créer un jeu de test (Arrange)
		// Action
		commentaireTest = commentaireService.createCommentaire(commentaireTest);
		Commentaire commentaireCree = commentaireService.getCommentaireFromId(commentaireTest.getId());
		// assert
		assertNotEquals((long)0, (long)commentaireTest.getId());
		
		assertEquals(commentaireTest.getAuteur(), commentaireCree.getAuteur());
		assertEquals(commentaireTest.getContenu(), commentaireCree.getContenu());
		assertEquals(commentaireTest.getNote(), commentaireCree.getNote());
		assertEquals(commentaireTest.getDateCreation().getDate(), commentaireCree.getDateCreation().getDate());

	}

	@Test

	public void tesGetCommentaireFromId() throws SQLException {
		Commentaire commentaireFromId = commentaireService.getCommentaireFromId(commentaireTest.getId());
		
		assertEquals(commentaireTest.getAuteur(), commentaireFromId.getAuteur());
		assertEquals(commentaireTest.getContenu(), commentaireFromId.getContenu());
		assertEquals(commentaireTest.getNote(), commentaireFromId.getNote());
		assertEquals(commentaireTest.getDateCreation().getDate(), commentaireFromId.getDateCreation().getDate());
	}

	@Test
	public void testUpdateCommentaire() throws SQLException {
		Date newDate = new Date("2019/11/05");
		
		commentaireTest.setAuteur("auteurTest");
		commentaireTest.setContenu("contenuTest");
		commentaireTest.setNote(3);
		commentaireTest.setDateCreation(newDate);

		// action
		commentaireService.updateCommentaire(commentaireTest);
		Commentaire updateCommentaire = commentaireService.getCommentaireFromId(commentaireTest.getId());

		// assert
		
		assertEquals(updateCommentaire.getAuteur(), commentaireTest.getAuteur());
		assertEquals(updateCommentaire.getContenu(), commentaireTest.getContenu());
		assertEquals(updateCommentaire.getNote(), commentaireTest.getNote());
		assertEquals(updateCommentaire.getDateCreation().getDate(), commentaireTest.getDateCreation().getDate());

	}

	@Test
	public void testDeleteCommentaire() throws SQLException {
		// Créer un jeu de test (Arrange)
		// action
		commentaireService.deleteCommentaire(commentaireTest); // on la supprime
		commentaireTest = commentaireService.getCommentaireFromId(commentaireTest.getId());

		// Assert
		assertNull(commentaireTest);
	}

	public static junit.framework.Test suite() {

		TestSuite suite = new TestSuite("Suite TestCommentaireService");
		suite.addTest(new TestCommentaireService("testCreateCommentaire"));
		suite.addTest(new TestCommentaireService("tesGetCommentaireFromId"));
		suite.addTest(new TestCommentaireService("testUpdateCommentaire"));
		suite.addTest(new TestCommentaireService("testDeleteCommentaire"));
		return suite;

	}

}
