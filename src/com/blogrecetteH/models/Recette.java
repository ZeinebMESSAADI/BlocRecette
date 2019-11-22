
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.omg.CORBA.PUBLIC_MEMBER;

@Entity
@Table(name = "recette")
public class Recette {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "idMembre")
	private Integer idMembre;

	@Column(name = "idCategorie")
	private Integer idCategorie;

	@Column(name = "titre")
	private String titre;

	@Column(name = "description")
	private String description;

	@Column(name = "photo")
	private String photo;

	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@Transient  // elle appartient au projet mais elle ne s'affiche pas à la BDD
	private int MoyenneNote; 
	
	
	
	
	

	// ------------ Recette/Membre -------------//
	@ManyToOne
	private Membre membre;

	/**
	 * @return the membre
	 */
	public Membre getMembre() {
		return membre;
	}

	/**
	 * @param membre the membre to set
	 */
	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	// --------------- Categorie/Recette -------------//
	@ManyToOne
	private Categorie categorie;

	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	// -------- Recette/Ingredient ---------//
	@OneToMany (mappedBy = "recette")
	protected Collection<Ingredient> ingredients;

	
	public void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
		ingredient.setRecette(this);
	}

	public void removeIngredient(Ingredient ingredient) {
		ingredients.remove(ingredient);
		ingredient.setRecette(null);
	}

	public Collection<Ingredient> getiIngredients() {
		return ingredients;
	}
	

	// --------- Recette/Commentaire ---------//
	@OneToMany(mappedBy = "recette")
	protected Collection<Commentaire> commentaires;

	public Recette() {
				this.commentaires = new ArrayList<Commentaire>();
				this.ingredients = new ArrayList<Ingredient>();
			}

	public void addCommentaire(Commentaire commentaire) {
		commentaires.add(commentaire);
		commentaire.setRecette(this);
		
	}

	public void removeCommentaire(Commentaire commentaire) {
		commentaires.remove(commentaire);
		commentaire.setRecette(null);
	}

	public Collection<Commentaire> getCommentaires() {
		return commentaires;
	}
	
//------------------------------//
	public Recette(Integer id, Integer idMembre, Integer idCategorie, String titre, String description, String photo,
			Date dateCreation) {
		super();
		this.id = id;
		this.idMembre = idMembre;
		this.idCategorie = idCategorie;
		this.titre = titre;
		this.description = description;
		this.photo = photo;
		this.dateCreation = dateCreation;
	}

	public Recette(Integer idMembre, Integer idCategorie, String titre, String description, String photo,
			Date dateCreation) {
		super();
		this.idMembre = idMembre;
		this.idCategorie = idCategorie;
		this.titre = titre;
		this.description = description;
		this.photo = photo;
		this.dateCreation = dateCreation;
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
	 * @return the idMembre
	 */
	public Integer getIdMembre() {
		return idMembre;
	}

	/**
	 * @param idMembre the idMembre to set
	 */
	public void setIdMembre(Integer idMembre) {
		this.idMembre = idMembre;
	}

	/**
	 * @return the idCategorie
	 */
	public Integer getIdCategorie() {
		return idCategorie;
	}

	/**
	 * @param idCategorie the idCategorie to set
	 */
	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
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
		return "Recette [id=" + id + ", idMembre=" + idMembre + ", idCategorie=" + idCategorie + ", titre=" + titre
				+ ", description=" + description + ", photo=" + photo + ", dateCreation=" + dateCreation + "]";
	}

	
	
	public void setMoyenneNote(int MoyenneNote) {
		this.MoyenneNote = MoyenneNote;
		
	}
	public int getMoyenneNote() {
		return MoyenneNote;
	}

}
