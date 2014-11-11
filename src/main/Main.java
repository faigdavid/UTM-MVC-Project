package main;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		ConsoleView view = new ConsoleView();
		try {
			view.runConsoleView();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
