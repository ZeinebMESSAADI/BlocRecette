<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="header.jsp"%>

<div id="global">
	<div id="categorie">
		<ul>
		
		<!-- pour rendre dynamique on utilise for Each -->
		<c:forEach items="${categories}" var="categorie">
		
			
			<li><a href="categorie?idCategorie=${categorie.idCategorie}">${categorie.nom}</a></li>
			</c:forEach>
		</ul>
	</div>
	
	
	<c:forEach items="${recettesFromCategorie}" var="recette">
	
	<article>
		<header>
			<img class="imgRecette" src="img/${recette.photo}"
				width="300px" height="242px" alt="Tartiflette" /> <a
				href="recette?id=${recette.idRecette}">
				<h1 class="titreRecette">${recette.titre}</h1>
			</a>
			<time> ${recette.dateCreation} </time>
		</header>
		<p>${recette.description}</p>
	</article>
	<hr />
	</c:forEach>
	
</div>



<%@ include file="footer.jsp"%>