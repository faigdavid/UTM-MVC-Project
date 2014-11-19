package model;

import java.io.IOException;

import mvc.ModelEventListener;
import GUIViews.GUIMain;

public class Main {

	public static void main(String[] args) {

		ModelEventListener view = new GUIMain();
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
