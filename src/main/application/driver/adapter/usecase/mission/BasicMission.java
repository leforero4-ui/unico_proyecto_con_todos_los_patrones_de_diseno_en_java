package main.application.driver.adapter.usecase.mission;

import main.application.driver.port.usecase.iterator.BoardCollection;
import main.domain.model.Enemy;
import main.domain.model.Mission;
import main.domain.model.SupremeAir;
import main.domain.model.SupremeNaval;

public class BasicMission extends Mission {

	public BasicMission(BoardCollection<Enemy> board) {
		super(board);
	}

	@Override
	protected boolean isMainObjectiveComplete() {
		boolean isWeakened = true;
		
		while (this.enemyIterator.hasNext()) {
			final Enemy enemy = this.enemyIterator.getNext();
			if(enemy instanceof SupremeAir || enemy instanceof SupremeNaval) {
				isWeakened = enemy.getLife() < 150;
				break;
			}
        }
		this.enemyIterator.reset();
		
		return isWeakened;
	}

	@Override
	protected boolean isSecondaryObjectiveComplete() {
		return true;
	}

	@Override
	protected boolean isTertiaryObjectiveComplete() {
		return true;
	}

}
