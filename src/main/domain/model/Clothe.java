package main.domain.model;

public class Clothe {
	private final String typeEye;
	private final String typeHair;
	private final String typeShirt;
	private final String typePant;
	private final String typeShoes;
	
	public Clothe(String typeEye, String typeHair, String typeShirt, String typePant, String typeShoes) {
		this.typeEye = typeEye;
		this.typeHair = typeHair;
		this.typeShirt = typeShirt;
		this.typePant = typePant;
		this.typeShoes = typeShoes;
	}

	public String getTypeEye() {
		return this.typeEye;
	}

	public String getTypeHair() {
		return this.typeHair;
	}

	public String getTypeShirt() {
		return this.typeShirt;
	}

	public String getTypePant() {
		return this.typePant;
	}

	public String getTypeShoes() {
		return this.typeShoes;
	}
}
