package main.infrastructure.driven.adapter.provider;

import main.application.driven.port.provider.Drawable;

import java.io.IOException;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class LanternaDrawable implements Drawable {
	private Screen screen;
	private WindowBasedTextGUI textGUI;

	public LanternaDrawable() {
		try {
			final DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
			final Terminal terminal = terminalFactory.createTerminal();
			this.screen = new TerminalScreen(terminal);
			this.textGUI = new MultiWindowTextGUI(screen);
		} catch (IOException e) {
            System.out.println(e.getMessage());
		}
	}

	@Override
	public String in(String text) {
		try {
        	this.screen.startScreen();
        	
        	return new TextInputDialogBuilder()
    		.setTitle("Juego:")
    		.setDescription(text)
    		//.setValidationPattern(Pattern.compile("[0-9]"), "You didn't enter a single number!")
    		.build()
    		.showDialog(this.textGUI);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		return null;
	}

	@Override
	public void out(String text) {
		try {
			this.screen.startScreen();
			
			new MessageDialogBuilder()
			.setTitle("Juego:")
			.setText(text)
			.build()
			.showDialog(this.textGUI);
		} catch (IOException e) {
            System.out.println(e.getMessage());
		}
	}

}
