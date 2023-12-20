package main.domain.model;

import main.domain.model.skill.Bang;

public class SupremeNaval extends Supreme {
	
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
	public String getAvatar(final String prefix) {
		return prefix + this.skill.getIdentifier() + "MN|";
	}

}
