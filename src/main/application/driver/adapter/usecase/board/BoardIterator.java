package main.application.driver.adapter.usecase.board;

import main.application.driver.port.usecase.iterator.BoardCollection;
import main.application.driver.port.usecase.iterator.PatternsIterator;
import main.domain.model.Enemy;

public class BoardIterator implements PatternsIterator<Enemy> {
    private final BoardCollection<Enemy> boardCollection;
    private int row;
    private int column;

    public BoardIterator(final BoardCollection<Enemy> boardCollection) {
        this.boardCollection = boardCollection;
        this.row = 0;
        this.column = -1;
    }


	@Override
	public boolean hasNext() {
		int columnNext = this.column + 1;
		int rowNext = this.row;
        if (columnNext >= this.boardCollection.getColumns()) {
        	columnNext = 0;
        	rowNext++;
        }
        return rowNext < this.boardCollection.getRows()
				&& columnNext < this.boardCollection.getColumns()
				&& this.boardCollection.getEnemy(rowNext, columnNext) != null;
	}

	@Override
	public Enemy getNext() {
		if (!hasNext()) {
	        return null;
	    }
		this.column++;
        if (this.column >= this.boardCollection.getColumns()) {
        	this.column = 0;
        	this.row++;
        }
        return this.boardCollection.getEnemy(this.row, this.column);
	}
	
	@Override
	public void remove() {
		this.boardCollection.deleteEnemy(row, column);
	}


	@Override
	public String getAvatarNext() {
		if (!hasNext()) {
	        return null;
	    }
		this.column++;
        if (this.column >= this.boardCollection.getColumns()) {
        	this.column = 0;
        	this.row++;
        }
		return this.boardCollection.getAvatarSquare(this.row, this.column);
	}


	@Override
	public void reset() {
        this.row = 0;
        this.column = -1;
	}

}
