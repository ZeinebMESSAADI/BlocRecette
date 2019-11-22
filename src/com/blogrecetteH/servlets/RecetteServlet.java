package com.blogrecetteH.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecetteH.models.Commentaire;
import com.blogrecetteH.models.Ingredient;
import com.blogrecetteH.services.CommentaireService;
import com.blogrecetteH.services.IngredientService;
import com.blogrecetteH.services.RecetteService;
import com.blogrecetteH.services.TagService;

/**
 * Servlet implementation class Recette
 */
@WebServlet(name = "Recette", urlPatterns = { "/recette" })
public class RecetteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecetteServlet() {
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

		int id = Integer.parseInt(request.getParameter("id"));
		int noteMoyenneByRecette;

		try {

			RecetteService recetteService = new RecetteService();
			com.blogrecetteH.models.Recette listrecetteByID = recetteService.getRecetteFromId(id);
			request.setAttribute("recette", listrecetteByID);

			IngredientService ingredientService = new IngredientService();
			List<Ingredient> listIngredientbyIdRecette = ingredientService.getIngredientByRecette(id);
			request.setAttribute("ingredientByRecettes", listIngredientbyIdRecette);

			CommentaireService commentaireService = new CommentaireService();
			List<Commentaire> listCommentairebyIdRecette = new ArrayList<Commentaire>();
			listCommentairebyIdRecette = commentaireService.getCommentaireByRecette(id);
			request.setAttribute("commentaireByrecettes", listCommentairebyIdRecette);

			
			noteMoyenneByRecette = recetteService.moyNoteRecetteByRecette(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("Notemoyenne", noteMoyenneByRecette);

			
			
			

			request.setAttribute("avis", listCommentairebyIdRecette.size());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/recette.jsp").forward(request, response);
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
		Date dateCreation = new Date();

		int idRecette = Integer.parseInt(request.getParameter("id")); 
		int note = Integer.parseInt(request.getParameter("note"));
		

		String auteur = request.getParameter("auteur");
		if (auteur.isEmpty()) {
			erreur += "Veuillez saisir un nom <br>";
		}
		String contenu = request.getParameter("contenu");
		if (contenu.isEmpty()) {
			erreur += "Veuillez saisir un commentaire <br>";
		}

	

		RecetteService rService = new RecetteService();
		com.blogrecetteH.models.Recette recette = null;
		try {
			recette = rService.getRecetteFromId(idRecette);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Commentaire commentaire = new Commentaire(recette, auteur, contenu, note, dateCreation);
		

		// request.setAttribute("erreur", erreur);

		if (erreur.isEmpty()) {
			try {

				CommentaireService commentaireService = new CommentaireService();
				commentaireService.createCommentaire(commentaire);
				request.setAttribute("commentaire", commentaire); // on enregistre dans la session un commrntaire
				
				this.doGet(request, response); // Pour aller a la page confirmation automatiquement
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}

		else {

			request.setAttribute("erreur", erreur); // envoyer au jsp (request)
			// this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request,
			// response);
			
			this.doGet(request, response);
		}
	}

}
