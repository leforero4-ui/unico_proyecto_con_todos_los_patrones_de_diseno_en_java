package main.domain.model;

public class SupremeAir extends Enemy {
	
	private static SupremeAir instance;
	
	private SupremeAir() {
		super(200, 15, new Poison());
	}
	
	public static SupremeAir getInstance() {
		if(instance == null) {
			instance = new SupremeAir();
		}
		
		return instance;
	}

	@Override
	public String getAvatar(final String prefix) {
		return prefix + "MA|";
	}

	@Override
	public void move(int direction) {
		// TODO Auto-generated method stub
	}

}
