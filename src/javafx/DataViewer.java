package javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.KNNSubject;
import utils.Observer;
import utils.Subject;

import java.io.IOException;
import java.net.URL;

/**
 * allows to create the DataViewer controller window
 */
public class DataViewer extends Stage implements Observer {
    @FXML
    private final DataViewerController controller;
    private KNNSubject knnsub;
    /**
     * Constructor for the DataViewer Window class
     * @param knnsub Subject to observe
     * @throws IOException exception
     */
    public DataViewer(KNNSubject knnsub) throws IOException {
        this.knnsub = knnsub;
        knnsub.attach(this);
        URL url = getClass().getResource("dataViewer.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        this.controller = (DataViewerController)loader.getController();
        controller.setDataLabel(knnsub.getKNN());
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("chart.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("DarkMode.css").toExternalForm());
        this.setScene(scene);
        this.setTitle("Data Viewer");
        this.show();
    }

    @Override
    public void update(Subject subj) {
        knnsub = (KNNSubject) subj;
        controller.setDataLabel(knnsub.getKNN());
    }

    @Override
    public void update(Subject subj, Object data) {
        knnsub = (KNNSubject) subj;
        controller.setDataLabel(knnsub.getKNN());
    }


}
