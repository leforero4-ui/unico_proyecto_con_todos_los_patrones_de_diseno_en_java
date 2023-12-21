package main.application.driver.adapter.usecase.mission;

import main.application.driver.port.usecase.iterator.BoardCollection;
import main.domain.model.Enemy;
import main.domain.model.Fort;
import main.domain.model.Mission;
import main.domain.model.SupremeAir;
import main.domain.model.SupremeNaval;

public class HighMission extends Mission {

	public HighMission(BoardCollection<Enemy> board) {
		super(board);
	}

	@Override
	protected boolean isMainObjectiveComplete() {
		boolean isSupremeDead = true;
		
		while (this.enemyIterator.hasNext()) {
			final Enemy enemy = this.enemyIterator.getNext();
			if(enemy instanceof SupremeAir || enemy instanceof SupremeNaval) {
				isSupremeDead = enemy.getLife() <= 0;
				break;
			}
        }
		this.enemyIterator.reset();
		
		return isSupremeDead;
	}

	@Override
	protected boolean isSecondaryObjectiveComplete() {
		boolean isFortAlive = true;
		
		while (this.enemyIterator.hasNext()) {
			if(this.enemyIterator.getNext() instanceof Fort) {
				isFortAlive = false;
				break;
			}
        }
		this.enemyIterator.reset();
		
		return isFortAlive;
	}

}
