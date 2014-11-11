package main;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		ConsoleView view = new ConsoleView();
		Controller controller = new Controller(view);
		try {
			view.runConsoleView();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
