package main.application.driver.adapter.usecase.board;

import main.application.driver.port.usecase.iterator.Iterator;
import main.domain.model.Enemy;

public class BoardIterator implements Iterator<Enemy> {
    private final BigBoard bigBoard;
    private int row;
    private int column;

    public BoardIterator(BigBoard bigBoard) {
        this.bigBoard = bigBoard;
        this.row = 0;
        this.column = -1;
    }


	@Override
	public boolean hasNext() {
		int columnNext = this.column + 1;
		int rowNext = this.row;
        if (columnNext >= this.bigBoard.getColumns()) {
        	columnNext = 0;
        	rowNext++;
        }
        return rowNext < this.bigBoard.getRows()
				&& columnNext < this.bigBoard.getColumns()
				&& this.bigBoard.getEnemy(rowNext, columnNext) != null;
	}

	@Override
	public Enemy getNext() {
		if (!hasNext()) {
	        return null;
	    }
		this.column++;
        if (this.column >= this.bigBoard.getColumns()) {
        	this.column = 0;
        	this.row++;
        }
        return this.bigBoard.getEnemy(this.row, this.column);
	}


	@Override
	public String getAvatarNext() {
		if (!hasNext()) {
	        return null;
	    }
		this.column++;
        if (this.column >= this.bigBoard.getColumns()) {
        	this.column = 0;
        	this.row++;
        }
		return this.bigBoard.getAvatarSquare(this.row, this.column);
	}


	@Override
	public void reset() {
        this.row = 0;
        this.column = -1;
	}

}
