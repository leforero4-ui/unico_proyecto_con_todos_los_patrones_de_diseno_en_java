package main.domain.model;

public class SupremeNaval extends Enemy {
	
	private static SupremeNaval instance;
	
	private SupremeNaval() {
		super(200, 15, new Bang(10));
	}
	
	public static SupremeNaval getInstance() {
		if(instance == null) {
			instance = new SupremeNaval();
		}
		
		return instance;
	}

	@Override
	public void receiveAttack(int attack) {
		this.life -= attack - 5;
	}

	@Override
	public String getAvatar(final String prefix) {
		return prefix + this.skill.getIdentifier() + "MN|";
	}

}
