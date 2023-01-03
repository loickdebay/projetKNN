package javafx;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.KNNConnectable;
import utils.KNNSubject;

import java.io.IOException;

/**
 * launches the javafx application
 */
public class Mainfx extends Application{
    private static final KNNConnectable knnConnectable = new KNNConnectable(null);
    private static final KNNSubject knnsub = new KNNSubject(null);
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * starts the object picker
     * @param stage Used stage
     * @throws IOException exception
     */
    public void start(Stage stage) throws IOException {
        new DataViewer(knnsub);
        new DataSelector();
    }

    public static KNNConnectable getKNNConnectable(){
        return knnConnectable;
    }

    public static KNNSubject getKNNSubject(){
        return knnsub;
    }


}
