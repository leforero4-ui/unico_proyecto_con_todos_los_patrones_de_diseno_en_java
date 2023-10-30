package main.domain.model;

public class PlayerNaval extends Player {
	
	public PlayerNaval(final PlayerBuilder builder) {
		super(builder);
	}

	@Override
	public void draw() {
		this.drawable.out("PlayerNaval [name=" + name + ", type=" + type.getName() + ", typeEye=" + typeEye + ", typeHair=" + typeHair
				+ ", typeShirt=" + typeShirt + ", typePant=" + typePant + ", typeShoes=" + typeShoes + "]");
	}
}
