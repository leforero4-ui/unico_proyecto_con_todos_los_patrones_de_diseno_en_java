package main.application.driver.port.usecase.iterator;

import main.domain.model.Visitor;

public interface PatternsIterator<T> {
	boolean hasNext();
    T getNext();
	void remove();
	void visit(Visitor visitor);
    String getAvatarNext();
    void reset();
}
