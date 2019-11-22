
package com.blogrecetteH.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogrecetteH.models.Tag;
import com.blogrecetteH.services.TagService;

/**
 * Servlet implementation class TagServlet
 */
@WebServlet("/tag")
public class TagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TagServlet() {
        super();
        
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TagService ts = new TagService();
		ArrayList<com.blogrecetteH.models.Tag> tags = ts.getAllTags();
		request.setAttribute("tags", tags);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/tag.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomTag = request.getParameter("name");
		String info = "";
		
		if (nomTag.isEmpty()) {
			info+="Veuillez saisir le nom du tag <br>";
		}
		
		TagService tagService = new TagService();
		Tag tagServlet = new Tag(nomTag);
	
		
		tagService.createTag(tagServlet);
		
		
		
		doGet(request, response);
	}

}
