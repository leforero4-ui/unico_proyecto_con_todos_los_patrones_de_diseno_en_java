package main.domain.model;

import main.application.driven.port.provider.Drawable;
import main.domain.model.valueobject.Type;
import main.domain.model.valueobject.WarriorType;

public class Player {
	private final String name;
	private final Type type;
	private final String typeEye;
	private final String typeHair;
	private final String typeShirt;
	private final String typePant;
	private final String typeShoes;
	private final Drawable drawable;
	
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

	public void draw() {
		this.drawable.out("Player [name=" + name + ", type=" + type.getName() + ", typeEye=" + typeEye + ", typeHair=" + typeHair
				+ ", typeShirt=" + typeShirt + ", typePant=" + typePant + ", typeShoes=" + typeShoes + "]");
	}
}
