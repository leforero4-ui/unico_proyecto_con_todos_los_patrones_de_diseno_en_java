package main.domain.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Supreme extends Enemy {
	
	private final List<Protective> protectors;
	
	protected Supreme(int life, int attackLevel, Skillfull skill) {
		super(life, attackLevel, skill);
		this.protectors = new ArrayList<>();
	}

	@Override
	public void acceptVisit(Visitor visitor) {
		visitor.visitSupreme(this);
	}

	@Override
	public void receiveAttack(int attack) {
		this.life -= this.notifyAttackToProtectors(attack);
	}
	
	public void addProtector(Protective protective) {
		this.protectors.add(protective);
	}
	
	public void removeProtector(Protective protective) {
		this.protectors.remove(protective);
	}
	
	private int notifyAttackToProtectors(int attack) {
		final List<Protective> protectorsTemp = new ArrayList<>(this.protectors);
		protectorsTemp.forEach(protective -> {
			protective.protect(this);
		});
		
		return attack - protectorsTemp.size();
	}

}
