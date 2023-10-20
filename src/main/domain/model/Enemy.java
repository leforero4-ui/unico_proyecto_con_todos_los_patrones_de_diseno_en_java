package main.domain.model;

public interface Enemy {
	int getLife();
	int getAttackLevel();
	void draw();
	void move(int direction);
}
