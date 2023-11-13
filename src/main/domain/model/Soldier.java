package main.domain.model;

public abstract class Soldier extends Enemy {
	public Soldier(int life, int attackLevel) {
		super(life, attackLevel, new MultipleShots(2));
	}
	
	@Override
	public void receiveAttack(int attack) {
		this.life -= attack;
	}

	public abstract Soldier clone();
	public abstract String getTypeEye();
	public abstract String getTypeHair();
	public abstract String getTypeShirt();
	public abstract String getTypePant();
	public abstract String getTypeShoes();
}
