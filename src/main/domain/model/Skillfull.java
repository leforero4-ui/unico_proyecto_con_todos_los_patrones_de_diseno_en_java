package main.domain.model;

public interface Skillfull {
	int getEnhancedAttackLevel(int attackLevel, boolean isAttacking);
	Skillfull getClone();
	String getIdentifier();
}
