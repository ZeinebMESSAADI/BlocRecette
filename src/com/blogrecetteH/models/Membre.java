package com.blogrecetteH.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "membre")
public class Membre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nom")
	private String nom;

	@Column(name = "pseudo")
	private String pseudo;

	@Column(name = "mdp")
	private String mdp;

	@Column(name = "email")
	private String email;

	@Temporal(TemporalType.DATE)
	private Date dateInscription;

	// ------ Recette/Membre -------//
	@OneToMany (mappedBy = "membre")
	protected Collection<Recette> recettes;

	public Membre() {
		this.recettes = new ArrayList<Recette>();
	}

	public void addRecette(Recette recette) {
		recettes.add(recette);
		recette.setMembre(this);
	}

	public void removeComment(Recette recette) {
		recettes.remove(recette);
		recette.setMembre(null);
	}

	public Collection<Recette> getRecettes() {
		return recettes;
	}
	//------------------------------------------//
	
	public Membre(Integer id, String nom, String pseudo, String mdp, String email, Date dateInscription) {
		this.id = id;
		this.nom = nom;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.email = email;
		this.dateInscription = dateInscription;

	}

	public Membre(String nom, String pseudo, String mdp, String email, Date dateInscription) {

		this.nom = nom;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.email = email;
		this.dateInscription = dateInscription;

	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the dateInscription
	 */
	public Date getDateInscription() {
		return dateInscription;
	}

	/**
	 * @param dateInscription the dateInscription to set
	 */
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	@Override
	public String toString() {
		return "membre [id=" + id + ", nom=" + nom + ", pseudo=" + pseudo + ", mdp=" + mdp + ", email=" + email
				+ ", dateInscription=" + dateInscription + "]";
	}

}