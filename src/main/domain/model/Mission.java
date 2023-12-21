package main.domain.model;

import main.application.driver.port.usecase.iterator.BoardCollection;
import main.application.driver.port.usecase.iterator.PatternsIterator;

public abstract class Mission {
	protected final BoardCollection<Enemy> board;
	protected final PatternsIterator<Enemy> enemyIterator;
	
	public Mission(BoardCollection<Enemy> board) {
		this.board = board;
		this.enemyIterator = this.board.getIterator();
	}
	
	public boolean isMissionComplete() {
		return this.isMainObjectiveComplete()
				&& this.isSecondaryObjectiveComplete()
				&& this.isTertiaryObjectiveComplete();
	}

	protected abstract boolean isMainObjectiveComplete();
	protected abstract boolean isSecondaryObjectiveComplete();
	
	protected boolean isTertiaryObjectiveComplete() {
		boolean isSoldierAlive = false;
		
		while (this.enemyIterator.hasNext()) {
			final Enemy enemy = this.enemyIterator.getNext();
			if(enemy instanceof SoldierAir || enemy instanceof SoldierNaval) {
				isSoldierAlive = true;
				break;
			}
        }
		this.enemyIterator.reset();
		
		return isSoldierAlive;
	}

}
