package javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * allows to create the Iris controller window
 */
public class IrisWindow extends Stage {
    /**
     * Constructor for the Iris Window class
     * @throws IOException exception
     */
    public IrisWindow() throws IOException {
        URL url = getClass().getResource("IrisVisualizer.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        IrisWindowController controller = (IrisWindowController) loader.getController();
        controller.listChoiceBoxesChoices();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("chart.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("DarkMode.css").toExternalForm());
        this.setScene(scene);
        this.setTitle("Iris Visualizer");
        this.show();
    }
}
