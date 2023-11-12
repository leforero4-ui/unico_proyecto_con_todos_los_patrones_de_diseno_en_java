package main.application.driver.port.usecase.iterator;

public interface Iterator<T> {
	boolean hasNext();
    T getNext();
    String getAvatarNext();
    void reset();
}
