package model;

import java.io.IOException;

import mvc.ModelEventListener;
import GUIViews.GUIController;

public class Main {

	public static void main(String[] args) {

		ModelEventListener view = new GUIController();
		ModelController controller = new ModelController();
		controller.addModelEventListener(view);
		view.addViewEventListener(controller);
		try {
			view.runView();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
