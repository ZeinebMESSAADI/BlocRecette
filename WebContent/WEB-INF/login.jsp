<%@ include file='header.jsp'%>

<div id="global">
	<h1>Login</h1>
	<div id="login">
		<form method="post" action="login">
			<input id="pseudo" name="pseudo" type="text" class="inputChamp"
				placeholder="Votre pseudo *"
				value='<c:out value = "${membre.pseudo}" />' /> <br> <input
				id="mdp" name="mdp" type="password" class="inputChamp"
				placeholder="Votre mot de passe *" /> <br /> <br /> <input
				type="submit" value="Je me connecte" class="submitBtn" />
		</form>
	</div>
	<div id="erreur">
		<p>${success}${membre.nom}</p>
	</div>
	<div id="erreur">
		<p>${info}</p>
	</div>
</div>

<%@ include file='footer.jsp'%>