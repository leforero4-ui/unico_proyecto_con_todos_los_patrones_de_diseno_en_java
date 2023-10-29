package main.domain.model;

import main.application.driven.port.provider.Drawable;
import main.domain.exception.CreationSupremeEnemyException;

public class SupremeNaval extends Enemy {
	
	private static SupremeNaval instance;
	
	private SupremeNaval(final Drawable drawable) {
		super(200, 15, drawable, new InstantCure());
	}
	
	public static SupremeNaval getInstance(final Drawable drawable) {
		if(instance == null) {
			instance = new SupremeNaval(drawable);
		} else if (instance.drawable != drawable) {
			throw new CreationSupremeEnemyException();
		}
		
		return instance;
	}

	@Override
	public void draw() {
		this.drawable.out("SupremeNaval [life=" + life + ", attackLevel=" + attackLevel + "]");
	}

	@Override
	public void move(int direction) {
		// TODO Auto-generated method stub
	}

}
