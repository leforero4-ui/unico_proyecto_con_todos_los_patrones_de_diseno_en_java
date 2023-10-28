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

	@Override
	public String in(String text) {
		try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            Terminal terminal = terminalFactory.createTerminal();
            Screen screen = new TerminalScreen(terminal);
        	screen.startScreen();
        	
        	final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        	
        	return new TextInputDialogBuilder()
    		.setTitle("Juego:")
    		.setDescription(text)
    		//.setValidationPattern(Pattern.compile("[0-9]"), "You didn't enter a single number!")
    		.build()
    		.showDialog(textGUI);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		return null;
	}

	@Override
	public void out(String text) {
		try {
			// Setup terminal and screen layers
			Terminal terminal = new DefaultTerminalFactory().createTerminal();
			Screen screen = new TerminalScreen(terminal);
			screen.startScreen();

			// Setup WindowBasedTextGUI for dialogs
			final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
			
			new MessageDialogBuilder()
			.setTitle("Juego:")
			.setText(text)
			.build()
			.showDialog(textGUI);
		} catch (IOException e) {
            System.out.println(e.getMessage());
		}
	}

}
