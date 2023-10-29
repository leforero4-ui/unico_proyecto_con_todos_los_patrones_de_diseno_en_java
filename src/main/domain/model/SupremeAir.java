package main.domain.model;

import main.application.driven.port.provider.Drawable;
import main.domain.exception.CreationSupremeEnemyException;

public class SupremeAir extends Enemy {
	
	private static SupremeAir instance;
	
	private SupremeAir(final Drawable drawable) {
		super(200, 15, drawable, new InstantCure());
	}
	
	public static SupremeAir getInstance(final Drawable drawable) {
		if(instance == null) {
			instance = new SupremeAir(drawable);
		} else if (instance.drawable != drawable) {
			throw new CreationSupremeEnemyException();
		}
		
		return instance;
	}

	@Override
	public void draw() {
		this.drawable.out("SupremeAir [life=" + life + ", attackLevel=" + attackLevel + "]");
	}

	@Override
	public void move(int direction) {
		// TODO Auto-generated method stub
	}

}
