package main.domain.model;

public class SoldierAir extends Soldier {
	private final Clothe clothe;

	public SoldierAir(final int life, final int attackLevel) {
		super(life, attackLevel);
		this.clothe = ClotheFactory.getClothe("clothe soldier air", "lentes oscuros", "corto", "manga larga", "largos", "botas");
		this.life = life;
		this.attackLevel = attackLevel;
	}

	@Override
	public SoldierAir clone() {
		return new SoldierAir(this.life, this.attackLevel);
	}

	@Override
	public String getTypeEye() {
		return this.clothe.getTypeEye();
	}

	@Override
	public String getTypeHair() {
		return this.clothe.getTypeHair();
	}

	@Override
	public String getTypeShirt() {
		return this.clothe.getTypeShirt();
	}

	@Override
	public String getTypePant() {
		return this.clothe.getTypePant();
	}

	@Override
	public String getTypeShoes() {
		return this.clothe.getTypeShoes();
	}

	@Override
	public String getAvatar(final String prefix) {
		return prefix + "SA|";
	}

	@Override
	public void move(final int direction) {
		// TODO Auto-generated method stub
	}
	
	
}
