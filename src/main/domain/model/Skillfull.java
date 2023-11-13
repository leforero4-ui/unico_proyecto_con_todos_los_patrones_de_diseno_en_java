package main.domain.model;

public interface Skillfull {
	int getEnhancedAttackLevel(int attackLevel);
	Skillfull getClone();
	String getIdentifier();
}
