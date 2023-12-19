package main.domain.model;

public interface FavorableEnvironment {
	void setNextFavorableEnvironment(FavorableEnvironment favorableEnvironment);
	boolean canAttack(Enemy enemy);
	boolean canHandleAttack(Enemy enemy);
}
