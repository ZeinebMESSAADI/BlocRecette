package com.blogrecetteH.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecetteH.models.Membre;
import com.blogrecetteH.services.CategorieService;
import com.blogrecetteH.services.RecetteService;


/**
 * Servlet implementation class Acceuil
 */
@WebServlet(name = "Index", urlPatterns = { "", "/index" })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Object RecettegetAll = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
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

		/*
		 * HttpSession session = request.getSession(); Member member = (Member)
		 * session.getAttribute("member");
		 * 
		 * request.setAttribute("member", member);
		 * this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(
		 * request, response);
		 */

		//HttpSession session = request.getSession();

	
		try {
			
			
			
			/*--------------Appel la fonction RecetteService getAll  ------------ */
			RecetteService recetteService = new RecetteService(); // creer un objet recetteService
			List <com.blogrecetteH.models.Recette>  recettegetAll = recetteService.getAllRecette();

			request.setAttribute("listOfRecette", recettegetAll);
			
			
			/*--------------Appel la fonction CategorieService getAll  ------------ */
			CategorieService categorieService = new CategorieService();  // creer un objet categorieService
			List <com.blogrecetteH.models.Categorie>  categoriegetAll = categorieService.getAllCategorie();
			
			
			request.setAttribute("listOfCategorie",categoriegetAll);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 

		
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
