package main.application.driver.port.usecase.iterator;

public interface PatternsIterator<T> {
	boolean hasNext();
    T getNext();
	void remove();
    String getAvatarNext();
    void reset();
}
