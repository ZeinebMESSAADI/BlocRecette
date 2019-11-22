<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<div id="global">
	<div id="categorie">
		<ul>
		
		<c:forEach items="${listOfCategorie}" var="categorie">
			<li><a href="categorie?idCategorie=${categorie.id}">${categorie.nom}</a></li>
		</c:forEach>
		</ul>
	</div>
	
	<c:forEach items="${listOfRecette}" var="recette">
	<article>
		<header>
			<img class="imgRecette" src="img/${recette.photo}" width="300px"
				height="242px" alt="Tartiflette" /> <a href="recette?id=${recette.id}">
				<p><c:forEach var="i" begin="1" end="${recette.moyenneNote}">
					<span class="fa fa-star checked"></span>
				</c:forEach>
				<c:forEach var="i" begin="${recette.moyenneNote+1}" end="5">
					<span class="fa fa-star "></span>
				</c:forEach></p>
				<h1 class="titreRecette">${recette.titre }</h1>
			</a>
			<time> ${recette.dateCreation} </time>
		</header>
		<p>${recette.description}</p>
	</article>
	</c:forEach>
	<hr />
	
</div>


<%@ include file="footer.jsp"%>