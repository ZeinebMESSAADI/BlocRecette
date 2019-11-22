/**
 * 
 */
package com.blogrecetteH.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "commenraire")
public class Commentaire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "auteur")
	private String auteur;

	@Column(name = "contenu")
	private String contenu;

	@Column(name = "note")
	private Integer note;

	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	// --------------- Commentaire/Recette -------------//
	@ManyToOne
	protected Recette recette;

	/**
	 * @return the recette
	 */
	public Recette getRecette() {
		return this.recette;
	}

	/**
	 * @param recette the recette to set
	 */
	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	// ------------------------------------------------//
	public Commentaire(Integer id, Recette recette, String auteur, String contenu, Integer note, Date dateCreation) {
		super();
		this.id = id;
		this.recette = recette;
		this.auteur = auteur;
		this.contenu = contenu;
		this.note = note;
		this.dateCreation = dateCreation;
		
	}

	public Commentaire(Recette recette, String auteur, String contenu, Integer note, Date dateCreation) {
		super();

		this.recette = recette;
		this.auteur = auteur;
		this.contenu = contenu;
		this.note = note;
		this.dateCreation = dateCreation;
	}
	
	public Commentaire(  String auteur, String contenu, Integer note, Date dateCreation) {
		super();

		
		this.auteur = auteur;
		this.contenu = contenu;
		this.note = note;
		this.dateCreation = dateCreation;
	}
	
	public Commentaire () {
	
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
	 * @return the auteur
	 */
	public String getAuteur() {
		return auteur;
	}

	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	/**
	 * @return the contenu
	 */
	public String getContenu() {
		return contenu;
	}

	/**
	 * @param contenu the contenu to set
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	/**
	 * @return the note
	 */
	public Integer getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(Integer note) {
		this.note = note;
	}

	/**
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public String toString() {
		return "Commentaire [id=" + id + ", auteur=" + auteur + ", contenu=" + contenu
				+ ", note=" + note + ", dateCreation=" + dateCreation + "]";
	}

}
