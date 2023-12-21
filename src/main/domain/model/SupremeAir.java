package main.domain.model;

import main.domain.model.skill.MultipleShots;

public class SupremeAir extends Supreme {
	
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
	public String getAvatar(final String prefix) {
		return prefix + this.skill.getIdentifier() + "JA|";
	}

}
