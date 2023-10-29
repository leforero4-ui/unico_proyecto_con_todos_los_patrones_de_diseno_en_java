package main.domain.model;

import main.application.driven.port.provider.Drawable;

public abstract class Soldier extends Enemy {
	public Soldier(int life, int attackLevel, Drawable drawable) {
		super(life, attackLevel, drawable, new MultipleShots(2));
	}
	
	public abstract Soldier clone();
	public abstract String getTypeEye();
	public abstract String getTypeHair();
	public abstract String getTypeShirt();
	public abstract String getTypePant();
	public abstract String getTypeShoes();
}
