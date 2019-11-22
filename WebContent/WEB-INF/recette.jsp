<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

   
        
    <div id="global">
    
 
        <article>
            <header>
                <img class="imgRecette" src="img/${recette.photo}" alt="Tartiflette" />
            <h1 class="titreRecette">${recette.titre}</h1>
            <c:forEach var ="i" begin="1" end ="${recette.moyenneNote}">
			<span class="fa fa-star checked"></span> 
		</c:forEach>
		<c:forEach var="i" begin ="${recette.moyenneNote+1}" end ="5">
			<span class="fa fa-star "></span> 
		</c:forEach>
			<br>
             : ${avis} Avis
             </br>
                <time> ${recette.dateCreation } </time>
            </header>
            <p>${recette.description }</p>
        </article>
        
       
        <hr />
        
        
        <header>
            <h2 id="titreIngredient"> Ingrédients</h2>
            <ul>
            <c:forEach items="${ingredientByRecettes}" var= "ingredient">
                <li>${ingredient[0].quantite} ${ingredient[0].unit}  ${ingredient[0].nom}</li>
             </c:forEach>   
             
             
            </ul>
        </header>
        
        
        
        <h2 id="titreCommentaire">Commentaires</h2>
        <c:forEach items="${commentaireByrecettes}" var= "commentaire">
        <div class="divCommentaire">
            <p>${commentaire.auteur} : ${commentaire.contenu}</p>
            <p>Note : ${commentaire.note }</p>
            <p>${commentaire.dateCreation }</p>
            <hr>
        </div>
        </c:forEach>
        <form method="post" action="recette?id=${recette.id}">
            <input id="auteur" name="auteur" type="text"
                placeholder="Votre nom *" class="inputChamp" /><br />
            <textarea id="txtCommentaire" name="contenu" rows="4"
                placeholder="Votre commentaire *" class="inputTextArea"></textarea>
            <br /> <label for="note">Note</label><br /> <select name="note"
                id="note" class="select">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select> <br /> <input type="submit" value="Commenter" class="submitBtn" />
        </form>
        <div id="erreur">
        <p>${erreur}</p>
        <!-- Remplacer "Erreurs" par la variable erreurs -->

        </div>
    </div>
    
   
    
    <%@ include file="footer.jsp"%>





