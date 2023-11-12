package main.domain.model;

public class SupremeNaval extends Enemy {
	
	private static SupremeNaval instance;
	
	private SupremeNaval() {
		super(200, 15, new Poison());
	}
	
	public static SupremeNaval getInstance() {
		if(instance == null) {
			instance = new SupremeNaval();
		}
		
		return instance;
	}

	@Override
	public String getAvatar(final String prefix) {
		return prefix + "MN|";
	}

	@Override
	public void move(int direction) {
		// TODO Auto-generated method stub
	}

}
