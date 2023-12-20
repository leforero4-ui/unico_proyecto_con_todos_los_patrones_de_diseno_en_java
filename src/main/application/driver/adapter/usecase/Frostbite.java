package main.application.driver.adapter.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.domain.model.Command;

public class Frostbite {
	private static final int NUMBER_OF_TURNS_FROZEN = 5;
	private static final int PROBABILITY = 25;
	
	private final List<Command> commandsWaiting;
	private boolean frozen;
	private int turnsFrozen;
	
	public Frostbite() {
		this.commandsWaiting = new ArrayList<>();
		this.turnsFrozen = 0;
		this.frozen = false;
	}
	
	public boolean calculateFreezing() {
		this.randomFreezing();
		
		return this.frozen;
	}
	
	public boolean isFrozen() {
		return this.frozen;
	}
	
	public void plusTurnFrozen() {
		++this.turnsFrozen;
		
		this.validateFrozen();
	}
	
	public int getTurnsForDefrost() {
		return this.frozen ? NUMBER_OF_TURNS_FROZEN - this.turnsFrozen : 0;
	}
	
	private void validateFrozen() {
		if (this.frozen && (this.turnsFrozen == NUMBER_OF_TURNS_FROZEN)) {
			this.commandsWaiting.forEach(Command::execute);
			
			this.turnsFrozen = 0;
			this.frozen = false;
			this.commandsWaiting.clear();
		}
	}
	
	private void randomFreezing() {		
		Random random = new Random();
		int randomNumber = random.nextInt(100) + 1;
		this.frozen = randomNumber < PROBABILITY;
	}
	
	public void addCommand(final Command command) {
		this.commandsWaiting.add(command);
	}
	
	public void addCommands(final List<Command> commands) {
		this.commandsWaiting.addAll(commands);
	}

}
