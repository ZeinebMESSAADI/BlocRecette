<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
<title>Mon Blog de Recettes</title>
</head>
<body>

	<header id="header">
		<a href="index"><h1 id="titreBlog">Mon Blog de Recettes</h1></a>
		<div style="width: 300px; margin: 20px auto;">Bienvenue sur mon
			blog de recettes</div>
		<div id="loginBar">
			<div class="login">
			<a class="primaryBtn login" href="tag">Administration des tags</a>
			<c:choose>
					<c:when test="${empty sessionScope.membre.nom }">
						<a class="primaryBtn login" href="login">Se Connecter</a>
						<a class="primaryBtn login" href="inscription">Inscription</a>
					</c:when>
				<c:otherwise>
					<p>Bonjour ${sessionScope.membre.pseudo}</p>
					<a class="primaryBtn login" href="deconnexion">Se Deconnecter</a>
				</c:otherwise>
			</c:choose>
				
			</div>
		</div>
	</header>