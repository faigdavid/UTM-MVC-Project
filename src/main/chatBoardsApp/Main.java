package chatBoardsApp;

import java.io.IOException;

import consoleView.ConsoleView;

public class Main {

	public static void main(String[] args) {

		ModelEventListener view = new ConsoleView();
		Controller controller = new Controller();
		controller.addModelEventListener(view);
		view.addViewEventListener(controller);
		try {
			view.runView();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
