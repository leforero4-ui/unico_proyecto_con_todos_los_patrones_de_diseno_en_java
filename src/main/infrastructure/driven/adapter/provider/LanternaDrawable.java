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
	private static final int SPLIT_TEXT = 70;
	private Screen screen;
	private WindowBasedTextGUI textGUI;

	public LanternaDrawable() {
		try {
			final DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
			final Terminal terminal = terminalFactory.createTerminal();
			this.screen = new TerminalScreen(terminal);
			this.textGUI = new MultiWindowTextGUI(this.screen);
		} catch (IOException e) {
            System.out.println(e.getMessage());
		}
	}

	@Override
	public String in(String text) {
		try {
			text = this.addLineBreaks(text);
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
			text = this.addLineBreaks(text);
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
	
    private String addLineBreaks(String input) {
        StringBuilder result = new StringBuilder();
        String[] lines = input.split("\\n");

        for (String line : lines) {
            StringBuilder currentLine = new StringBuilder();

            for (int i = 0; i < line.length(); i++) {
                char currentChar = line.charAt(i);

                if (currentLine.length() == SPLIT_TEXT) {
                    result.append(currentLine.toString().trim()).append("\r\n");
                    currentLine.setLength(0);
                }

                currentLine.append(currentChar);
            }

            if (currentLine.length() > 0) {
                result.append(currentLine.toString().trim()).append("\r\n");
            }
        }

        return result.toString();
    }

}
