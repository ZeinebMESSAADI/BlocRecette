package com.blogrecetteH.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecetteH.models.Membre;
import com.blogrecetteH.services.MembreService;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);//Return like

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		MembreService membreservice;



		String info = "";
		String success="Bienvenu";

		String pseudo = request.getParameter("pseudo");
		if (pseudo.isEmpty()) {
			info+="Veuillez saisir un pseudo<br>";
		}

		String mdp = request.getParameter("mdp");
		if (mdp.isEmpty()) {
			info+="Veuillez saisir un mot de passe<br>";
		}
		request.setAttribute("info", info);

		if (info.isEmpty()) {
			
				membreservice = new MembreService();
				Membre membre = new Membre(); 
				membre = membreservice.selectMembreByPseudoMdp(pseudo, mdp);
				
				if (membre.getMdp().contentEquals(mdp) && membre.getPseudo().contentEquals(pseudo)) {
					session.setAttribute("membre", membre);
					response.sendRedirect("index");
				}
				
				else {
					info+="Le pseudo ne correspond pas aucun mot de passe";
					request.setAttribute("info", info);
					
					this.doGet(request, response);
					}
			
		}else {
			this.doGet(request, response);
		}


	}
}
