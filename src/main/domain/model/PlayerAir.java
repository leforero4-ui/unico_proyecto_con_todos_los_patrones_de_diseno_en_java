package main.domain.model;

public class PlayerAir extends Player {
	
	public PlayerAir(final PlayerBuilder builder) {
		super(builder);
	}

	@Override
	public String getAvatar() {
		return "AA";
	}
}
