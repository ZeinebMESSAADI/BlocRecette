package com.blogrecetteH.models;

import javax.persistence.*;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private String nom;

	
	public Tag(String nom) {
		super();
		this.nom = nom;
	}
	
	public Tag() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getId() {
		return id;
	}
	
	
}