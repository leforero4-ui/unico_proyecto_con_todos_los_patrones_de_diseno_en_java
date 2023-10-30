package main.domain.model;

public class PlayerAir extends Player {
	
	public PlayerAir(final PlayerBuilder builder) {
		super(builder);
	}

	@Override
	public void draw() {
		this.drawable.out("PlayerAir [name=" + name + ", type=" + type.getName() + ", typeEye=" + typeEye + ", typeHair=" + typeHair
				+ ", typeShirt=" + typeShirt + ", typePant=" + typePant + ", typeShoes=" + typeShoes + "]");
	}
}
