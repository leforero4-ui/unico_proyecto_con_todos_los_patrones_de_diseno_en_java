package main.application.driver.adapter.usecase.board;

import java.util.List;

import main.application.driver.port.usecase.iterator.Collection;
import main.application.driver.port.usecase.iterator.Iterator;
import main.domain.model.Enemy;

public class BigBoard implements Collection<Enemy> {
    private final Enemy[][] squares;
    private static final int ROWS = 8;
    private static final int COLUMNS = 8;

    public BigBoard(List<Enemy> enemies) {
        squares = new Enemy[ROWS][COLUMNS];
        int indexEnemy = 0;
        SQUARE_LOOP: for (int row = 0; row < ROWS - 1; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (indexEnemy < enemies.size()) {
                	squares[row][column] = enemies.get(indexEnemy);
                    indexEnemy++;
                } else {
                    break SQUARE_LOOP;
                }
            }
        }
    }

    public int getRows() {
        return ROWS;
    }

    public int getColumns() {
        return COLUMNS;
    }

    public Enemy getEnemy(int row, int column) {
        return squares[row][column];
    }

	public String getAvatarSquare(int row, int column) {
		final Enemy enemy = squares[row][column];
		final StringBuilder avatarSquare = new StringBuilder();
		if (enemy != null) {
			avatarSquare.append("{");
			avatarSquare.append(row);
			avatarSquare.append("-");
			avatarSquare.append(column);
			avatarSquare.append(":");
			avatarSquare.append(enemy.getAvatar(""));
			final int avatarSquareLength = avatarSquare.length();
			if (avatarSquareLength > 0) {
				avatarSquare.deleteCharAt(avatarSquareLength - 1);
			}
			avatarSquare.append("}");
			if (column == COLUMNS - 1) {
				avatarSquare.append("\n");
			}
		}
		
		return avatarSquare.toString();
	}

	@Override
	public Iterator<Enemy> getIterator() {
		return new BoardIterator(this);
	}

}
