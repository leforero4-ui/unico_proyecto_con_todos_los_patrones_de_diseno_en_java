package main.application.driver.port.usecase.iterator;

import main.domain.model.Enemy;

public interface BoardCollection<T> {
    int getRows();
    int getColumns();
    Enemy getEnemy(int row, int column);
	void deleteEnemy(int row, int column);
	String getAvatarSquare(int row, int column);
	PatternsIterator<T> getIterator();
}
