package com.blogrecetteH.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecetteH.models.Membre;
import com.blogrecetteH.services.MembreService;
import com.mysql.jdbc.PreparedStatement;





/**
 * Servlet implementation class Inscription
 */
@WebServlet(name = "Inscription", urlPatterns = {"/inscription" })
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InscriptionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		String erreur = "";
		Date dateInscription=new Date(); // pour recuperer la date du serveur
		

		String nom = request.getParameter("nom");
		if (nom.isEmpty()) {
			erreur += " Veuillez saisir un nom  <br>";
		}

		String pseudo = request.getParameter("pseudo");
		if (pseudo.isEmpty()) {
			erreur += " Veuillez saisir un pseudo  <br>";
		}

		String email = request.getParameter("email");
		if (email.isEmpty()) {
			erreur += " Veuillez saisir un email  <br>";
		}
		
		 
		String mdp = request.getParameter("mdp");
		if (mdp.isEmpty()) {
			erreur += " Veuillez saisir un mot de passe  <br>";
		}

		Membre membre = new Membre( nom,  pseudo,  mdp,  email,  dateInscription);

		

		if (erreur.isEmpty()) {
			session.setAttribute("membre", membre);
			response.sendRedirect("index");
			try {
				
			
				MembreService membreService =  new MembreService();
				
				
				/*------------- Teste Create Utilisateur -------------*/
				membreService.createMembre (membre);
			
			}
				
			catch (Exception e){
				e.printStackTrace();
			}
			
		} 
		else {
			request.setAttribute("erreur", erreur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);

		}
	}

}
