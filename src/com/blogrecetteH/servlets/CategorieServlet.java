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

import com.blogrecetteH.services.CategorieService;
import com.blogrecetteH.services.RecetteService;

/**
 * Servlet implementation class Categorie
 */
@WebServlet(name = "Categorie", urlPatterns = { "/categorie" })
public class CategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategorieServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		HttpSession session = request.getSession();

		try {

			RecetteService recetteService = new RecetteService();
			int idCategorie = Integer.parseInt(request.getParameter("id"));

			List<com.blogrecetteH.models.Recette> getRecettesFromCategorie = recetteService.getRecetteByCategorie(idCategorie);

			CategorieService categorieService = new CategorieService();
			List<com.blogrecetteH.models.Categorie> categorieRecettes = categorieService.getAllCategorie();

			request.setAttribute("recettebyCategories", getRecettesFromCategorie);
			request.setAttribute("listOfCategorie", categorieRecettes);

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/categorie.jsp").forward(request, response);
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
