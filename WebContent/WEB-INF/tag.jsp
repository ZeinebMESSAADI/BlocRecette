<%@ include file='header.jsp'%>

<div id="global">
	<h1>Ajouter un tag</h1>
	<div id="login">
		<form method="post" action="tag">
			<input id="name" name="name" type="text" class="inputChamp"
				placeholder="Nom du tag"/> <br> <input
				type="submit" value="J'ajoute un tag !" class="submitBtn" />
		</form>
	</div>
	
		<c:forEach var="tag" items="${tags}">
               		 <li class="select"> - ${tag.nom}   <a href="delete-tag?id=${tag.id}" style="color:white;">Supprimer ce tag</a> 
              				
                	</li>
        </c:forEach>
</div>

 <%@ include file="footer.jsp"%>