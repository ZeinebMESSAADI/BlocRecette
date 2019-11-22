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
import com.blogrecetteH.services.MembreService;
import com.blogrecetteH.utils.HibernateUtil;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author HB
 *
 */
public class TestMembreService extends TestCase {

	private static SessionFactory sessionFactory = null;
	private static MembreService memberService = null;
	private static Membre memberTest = null;

	public TestMembreService() {

	}

	public TestMembreService(String testName) {
		super(testName);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before // la methode est appelée avant le test
	public void setUp() throws Exception {
		sessionFactory = HibernateUtil.getSessionFactory();
		
		memberService = new MembreService(); // on teste si on a crée un nouveau memberService
		
		if (memberTest==null) {
		memberTest = new Membre("memberTest", "Testo", "12345", "test@gmail.com", new Date());
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After // la methode est appelée après le test
	public void tearDown() throws Exception {
		
		memberService = null;
	}

	@Test // le // desactive tout le test
	public void testCreateMembre() throws SQLException {
		// Créer un jeu de test (Arrange)
		// Member member = new Member ("Zeineb", "Zeineb123","mdpZeineb123",
		// "zeineb.messaadi@gmail.com", new Date());

		// Action (action)
		memberTest = memberService.createMembre(memberTest);
		Membre memberCree = memberService.getMembreFromId(memberTest.getId());

		// Assert
		assertNotEquals((long)0, (long)memberTest.getId()); // on teste si l'IdMember est Not equals à 0

		assertEquals(memberTest.getNom(), memberCree.getNom());
		assertEquals(memberTest.getPseudo(), memberCree.getPseudo());
		assertEquals(memberTest.getMdp(), memberCree.getMdp());
		assertEquals(memberTest.getEmail(), memberCree.getEmail());
		assertEquals(memberTest.getDateInscription().getDate(), memberCree.getDateInscription().getDate());
	}

	@Test
	public void testUpdateMember() throws SQLException {

		Date newDate = new Date("2019/11/04");

		// Créer un jeu de test (Arrange)
		// Member member = new Member ("Zeineb Update", "Zeineb123
		// Update","mdpZeineb123", "zeineb.messaadi@gmail.com", newDate);
		// member.setIdMember(4);
		memberTest.setNom("memberTest Update");
		memberTest.setPseudo("Testo_update");
		memberTest.setMdp("54321");
		memberTest.setEmail("testUpdate@gmail.com");
		memberTest.setDateInscription(newDate);

		// Action (action)
		memberService.updateMembre(memberTest);
		Membre memberUpdate = memberService.getMembreFromId(memberTest.getId());

		// Assert
		assertEquals(memberUpdate.getNom(), memberTest.getNom());
		assertEquals(memberUpdate.getPseudo(), memberTest.getPseudo());
		assertEquals(memberUpdate.getMdp(), memberTest.getMdp());
		assertEquals(memberUpdate.getEmail(), memberTest.getEmail());
		assertEquals(memberUpdate.getDateInscription().getDate(), memberTest.getDateInscription().getDate());

	}

	@Test
	public void testDeleteMember() throws SQLException {
		// int id = 4;
		// Member member = null;
		// Créer un jeu de test (Arrange)

		// Action (action)
		// member =memberService.getMemberFromId(id); // on a récuperé le id
		memberService.deleteMembre(memberTest); // on la supprime
		memberTest = memberService.getMembreFromId(memberTest.getId()); // on la recupere encore une fois(sensé
																				// deeted) pour savoir s'il est dans la
																				// base ou nn

		// Assert
		assertNull(memberTest);

	}

	public static junit.framework.Test suite() {

		TestSuite suite = new TestSuite("Suite TestMemberService");
		suite.addTest(new TestMembreService("testCreateMembre"));
		suite.addTest(new TestMembreService("testUpdateMember"));
		suite.addTest(new TestMembreService("testDeleteMember"));
		return suite;

	}

}
