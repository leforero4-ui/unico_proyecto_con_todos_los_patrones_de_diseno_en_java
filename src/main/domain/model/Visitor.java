package main.domain.model;

public interface Visitor {
	void visitSoldier(Soldier soldier);
	void visitSquadron(Squadron squadron);
	void visitSupreme(Supreme supreme);
	void visitPlayer(Player player);
}
