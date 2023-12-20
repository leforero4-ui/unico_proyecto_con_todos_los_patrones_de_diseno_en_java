package main.domain.model;

import java.util.HashMap;
import java.util.Map;

import main.domain.model.Player.MementoPlayer;

public class CaretakerPlayer {
    private final Map<String, MementoPlayer> mementosPlayerByKey;
    
    public CaretakerPlayer() {
    	this.mementosPlayerByKey = new HashMap<>();
    }

    public void addMementoPlayerByKey(final String key, final MementoPlayer mementoPlayer) {
    	this.mementosPlayerByKey.put(key, mementoPlayer);
    }

    public MementoPlayer getMementoPlayerByKey(final String key) {
        return this.mementosPlayerByKey.get(key);
    }
}
