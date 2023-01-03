package javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * allows the titanic window to be created
 */
public class TitanicWindow extends Stage {
    /**
     * Creates the titanic window
     * @throws IOException exception
     */
    public TitanicWindow() throws IOException {
        URL url = getClass().getResource("TitanicVisualizer.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        TitanicWindowController controller = (TitanicWindowController) loader.getController();
        controller.listChoiceBoxesChoices();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("chart.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("DarkMode.css").toExternalForm());
        this.setScene(scene);
        this.setTitle("Titanic Visualizer");
        this.show();
    }
}
