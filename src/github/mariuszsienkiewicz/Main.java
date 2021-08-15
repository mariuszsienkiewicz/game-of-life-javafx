package github.mariuszsienkiewicz;

import github.mariuszsienkiewicz.view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainView mainView = new MainView(primaryStage);
        mainView.display();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
