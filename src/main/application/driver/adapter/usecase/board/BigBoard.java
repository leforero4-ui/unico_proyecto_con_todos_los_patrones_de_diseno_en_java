package main.application.driver.adapter.usecase.board;

import java.util.List;

import main.application.driver.port.usecase.iterator.BoardCollection;
import main.application.driver.port.usecase.iterator.PatternsIterator;
import main.domain.model.Enemy;

public class BigBoard implements BoardCollection<Enemy> {
    private final Enemy[][] squares;
    private final PatternsIterator<Enemy> iterator;
    private static final int ROWS = 8;
    private static final int COLUMNS = 8;

    public BigBoard(final List<Enemy> enemies) {
        squares = new Enemy[ROWS][COLUMNS];
        int indexEnemy = 0;
        SQUARE_LOOP: for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (indexEnemy < enemies.size()) {
                	squares[row][column] = enemies.get(indexEnemy);
                    indexEnemy++;
                } else {
                    break SQUARE_LOOP;
                }
            }
        }
        this.iterator = new BoardIterator(this);
    }

	@Override
    public int getRows() {
        return ROWS;
    }

	@Override
    public int getColumns() {
        return COLUMNS;
    }

	@Override
    public Enemy getEnemy(final int row, final int column) {
        return squares[row][column];
    }

	@Override
	public void deleteEnemy(final int row, final int column) {
        SQUARE_LOOP: for (int rowCurrent = row; rowCurrent < ROWS; rowCurrent++) {
            for (int columnCurrent = column; columnCurrent < COLUMNS; columnCurrent++) {
            	if ((rowCurrent != ROWS - 1) || (columnCurrent != COLUMNS - 1)) {
            		final int rowNext;
            		final int columnNext = columnCurrent < COLUMNS - 1 ? columnCurrent + 1 : 0;
            		if (columnCurrent == COLUMNS - 1) {
                		rowNext = rowCurrent < ROWS - 1 ? rowCurrent + 1 : 0;
            		} else {
            			rowNext = rowCurrent;
            		}
                	final Enemy enemyNext = squares[rowNext][columnNext];
                	if (enemyNext == null) {
                    	squares[rowCurrent][columnCurrent] = null;
                		break SQUARE_LOOP;
                	}
                	squares[rowCurrent][columnCurrent] = enemyNext;
            	} else {
                	squares[rowCurrent][columnCurrent] = null;
                    break SQUARE_LOOP;
            	}
            }
        }
    }

	@Override
	public String getAvatarSquare(final int row, final int column) {
		final Enemy enemy = squares[row][column];
		final StringBuilder avatarSquare = new StringBuilder();
		if (enemy != null) {
			avatarSquare.append("{");
			avatarSquare.append(row);
			avatarSquare.append("-");
			avatarSquare.append(column);
			avatarSquare.append(":");
			final String avatar = enemy.getAvatar("");
			avatarSquare.append(!avatar.isEmpty() ? avatar.substring(0, avatar.length() - 1) : "");
			avatarSquare.append(":");
			avatarSquare.append(enemy.getLife());
			avatarSquare.append(":");
			avatarSquare.append(enemy.getAttackLevel(false));
			avatarSquare.append("}");
			if (column == COLUMNS - 1) {
				avatarSquare.append("\n");
			}
		}
		
		return avatarSquare.toString();
	}

	@Override
	public PatternsIterator<Enemy> getIterator() {
		return this.iterator;
	}

}
