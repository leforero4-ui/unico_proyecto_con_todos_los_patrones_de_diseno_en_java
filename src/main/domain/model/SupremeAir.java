package main.domain.model;

public class SupremeAir extends Enemy {
	
	private static SupremeAir instance;
	
	private SupremeAir() {
		super(200, 15, new MultipleShots(3));
	}
	
	public static SupremeAir getInstance() {
		if(instance == null) {
			instance = new SupremeAir();
		}
		
		return instance;
	}

	@Override
	public void receiveAttack(int attack) {
		this.life -= attack - 4;
	}

	@Override
	public String getAvatar(final String prefix) {
		return prefix + this.skill.getIdentifier() + "MA|";
	}

}
