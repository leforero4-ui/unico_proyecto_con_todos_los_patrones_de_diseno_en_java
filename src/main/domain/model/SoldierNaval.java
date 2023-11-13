package main.domain.model;

public class SoldierNaval extends Soldier {
	private final Clothe clothe;

	public SoldierNaval(final int life, final int attackLevel) {
		super(life, attackLevel);
		this.clothe = ClotheFactory.getClothe("clothe soldier naval", "lentes google", "corto", "manga larga", "largos", "aletas");
		this.life = life;
		this.attackLevel = attackLevel;
	}

	@Override
	public SoldierNaval clone() {
		return new SoldierNaval(this.life, this.attackLevel);
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
		return prefix + "SN|";
	}
	
}
