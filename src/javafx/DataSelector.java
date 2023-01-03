package javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * creates the first window, the one that allows you to choose which type of Object you want to work with
 */
public class DataSelector extends Stage {
    /**
     * constructor for the DataSelector class
     * @throws IOException exception
     */
    public DataSelector() throws IOException {
        URL url = getClass().getResource("DataSelector.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("DarkMode.css").toExternalForm());
        this.setScene(scene);
        this.setTitle("Data Selector");
        this.show();
    }
}
