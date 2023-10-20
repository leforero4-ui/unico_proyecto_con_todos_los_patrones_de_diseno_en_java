package main.domain.model;

import main.application.driven.port.provider.Drawable;
import main.domain.model.valueobject.Type;

public class PlayerBuilder {
	private Drawable drawable;
	private String name;
	private Type type;
	private String typeEye;
	private String typeHair;
	private String typeShirt;
	private String typePant;
	private String typeShoes;

	public PlayerBuilder (final Drawable drawable) {
		this.drawable = drawable;
	}

	public Drawable drawable() {
		return this.drawable;
	}

	public PlayerBuilder name(final String name) {
		this.name = name;
		return this;
	}

	public String name() {
		return this.name;
	}

	public PlayerBuilder type(final Type type) {
		this.type = type;
		return this;
	}

	public Type type() {
		return this.type;
	}

	public PlayerBuilder typeEye(final String typeEye) {
		this.typeEye = typeEye;
		return this;
	}

	public String typeEye() {
		return this.typeEye;
	}

	public PlayerBuilder typeHair(final String typeHair) {
		this.typeHair = typeHair;
		return this;
	}

	public String typeHair() {
		return this.typeHair;
	}

	public PlayerBuilder typeShirt(final String typeShirt) {
		this.typeShirt = typeShirt;
		return this;
	}

	public String typeShirt() {
		return this.typeShirt;
	}

	public PlayerBuilder typePant(final String typePant) {
		this.typePant = typePant;
		return this;
	}

	public String typePant() {
		return this.typePant;
	}

	public PlayerBuilder typeShoes(final String typeShoes) {
		this.typeShoes = typeShoes;
		return this;
	}

	public String typeShoes() {
		return this.typeShoes;
	}
	
	public Player build() {
		return new Player(this);
	}
}
