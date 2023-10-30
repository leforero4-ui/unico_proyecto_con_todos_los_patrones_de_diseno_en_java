package main.domain.model;

import main.application.driven.port.provider.Drawable;
import main.domain.model.valueobject.Type;
import main.domain.model.valueobject.WarriorType;

public abstract class Player {
	protected final String name;
	protected final Type type;
	protected final String typeEye;
	protected final String typeHair;
	protected final String typeShirt;
	protected final String typePant;
	protected final String typeShoes;
	protected final Drawable drawable;
	
	public Player(final PlayerBuilder builder) {
		this.name = builder.name() != null && builder.name() != "" ? builder.name() : "NN";
		this.type = builder.type() != null ? builder.type() : new WarriorType();
		this.typeEye = builder.typeEye() != null && builder.typeEye() != "" ? builder.typeEye() : "lentes";
		this.typeHair = builder.typeHair() != null && builder.typeHair() != "" ? builder.typeHair() : "corto";
		this.typeShirt = builder.typeShirt() != null && builder.typeShirt() != "" ? builder.typeShirt() : "franela";
		this.typePant = builder.typePant() != null && builder.typePant() != "" ? builder.typePant() : "largos";
		this.typeShoes = builder.typeShoes() != null && builder.typeShoes() != "" ? builder.typeShoes() : "tennis";
		this.drawable = builder.drawable();
	}
	
	public abstract void draw();

}
