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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ingredient")

public class Ingredient {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "idRecette")
	private Integer idRecette;

	@Column(name = "nom")
	private String nom;

	@Column(name = "quantite")
	private Integer quantite;

	@Column(name = "unit")
	private String unit;

	// --------------- Ingredient/Recette -------------//
	@ManyToOne
	private Recette recette;

	/**
	 * @return the recette
	 */
	public Recette getRecette() {
		return recette;
	}

	public Ingredient() {
		super();
	}
	
	/**
	 * @param recette the recette to set
	 */
	public void setRecette(Recette recette) {
		this.recette = recette;
	}
	// ------------------------------------------------//

	public Ingredient(Integer id, Integer idRecette, String nom, Integer quantite, String unit) {
		super();
		this.id = id;
		this.idRecette = idRecette;
		this.nom = nom;
		this.quantite = quantite;
		this.unit = unit;
	}

	public Ingredient(Integer idRecette, String nom, Integer quantite, String unit) {
		super();
		this.idRecette = idRecette;
		this.nom = nom;
		this.quantite = quantite;
		this.unit = unit;
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
	 * @return the idRecette
	 */
	public Integer getIdRecette() {
		return idRecette;
	}

	/**
	 * @param idRecette the idRecette to set
	 */
	public void setIdRecette(Integer idRecette) {
		this.idRecette = idRecette;
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
	 * @return the quantite
	 */
	public Integer getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", idRecette=" + idRecette + ", nom=" + nom + ", quantite=" + quantite
				+ ", unit=" + unit + "]";
	}

}
