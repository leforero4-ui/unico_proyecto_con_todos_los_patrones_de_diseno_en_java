package main.application.driver.adapter.usecase.mission;

import main.application.driver.port.usecase.iterator.BoardCollection;
import main.domain.model.Enemy;
import main.domain.model.Mission;
import main.domain.model.SquadronAir;
import main.domain.model.SquadronNaval;
import main.domain.model.SupremeAir;
import main.domain.model.SupremeNaval;

public class MiddleMission extends Mission {

	public MiddleMission(BoardCollection<Enemy> board) {
		super(board);
	}

	@Override
	protected boolean isMainObjectiveComplete() {
		boolean isWeakened = true;
		
		while (this.enemyIterator.hasNext()) {
			final Enemy enemy = this.enemyIterator.getNext();
			if(enemy instanceof SupremeAir || enemy instanceof SupremeNaval) {
				isWeakened = enemy.getLife() < 80;
				break;
			}
        }
		this.enemyIterator.reset();
		
		return isWeakened;
	}

	@Override
	protected boolean isSecondaryObjectiveComplete() {
		int squadronsAlive = 0;
		
		while (this.enemyIterator.hasNext()) {
			final Enemy enemy = this.enemyIterator.getNext();
			if(enemy instanceof SquadronAir || enemy instanceof SquadronNaval) {
				++squadronsAlive;
				break;
			}
        }
		this.enemyIterator.reset();
		
		return squadronsAlive <= 1;
	}

}
