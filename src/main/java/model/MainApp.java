package model;

import controller.MainsceneController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    private Parent root;
    private Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        root = Context.getInstance().showMainForm();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Выбор поставщиков");
        stage.show();
    }
    @Override
    public void stop() {
        MainsceneController mf = Context.getInstance().getMainformController();
        if (mf.dataaccessor.isConnected())
            mf.dataaccessor.setDisconnect();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
