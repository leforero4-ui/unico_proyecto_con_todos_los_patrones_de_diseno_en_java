package main.domain.model;

public interface Soldier extends Enemy {
	Soldier clone();
	String getTypeEye();
	String getTypeHair();
	String getTypeShirt();
	String getTypePant();
	String getTypeShoes();
}
