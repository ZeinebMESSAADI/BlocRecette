/**
 * 
 */
package com.blogrecetteH.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categorie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nom")
	private String nom;
	

	// ------ Categorie/Membre -------//
	@OneToMany (mappedBy = "categorie")
	protected Collection<Recette> recettes;

	public Categorie() {
		this.recettes = new ArrayList<Recette>();
	}

	public void addRecette(Recette recette) {
		recettes.add(recette);
		recette.setCategorie(this);
	}

	public void removeComment(Recette recette) {
		recettes.remove(recette);
		recette.setCategorie(null);
	}

	public Collection<Recette> getRecettes() {
		return recettes;
	}
	//------------------------------------------//
	
	

	public Categorie(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	
	

	public Categorie(String nom) {
		super();
		this.nom = nom;
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

	
	
		
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nom=" + nom + "]";
	}
	
	
	
	
}
