package javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

/**
 * Manages the three choices on the type selector
 */
public class DataSelectorController {
    @FXML
    private Button choosePokemon;
    @FXML
    private Button chooseIris;

    /**
     * opens the Pokemon viewer
     * @param e Mouse Event when clicked on the Pokemon button
     * @throws IOException exception
     */
    public void openPokemonView(MouseEvent e) throws IOException {
        new PokemonWindow();
    }
    /**
     * opens the Iris viewer
     * @param e Mouse Event when clicked on the Iris button
     * @throws IOException exception
     */
    public void openIrisView(MouseEvent e) throws IOException {
        new IrisWindow();
    }
    /**
     * opens the Titanic viewer
     * @param e Mouse Event when clicked on the Titanic button
     * @throws IOException exception
     */
    public void openTitanicView(MouseEvent e) throws IOException {
        new TitanicWindow();
    }

}
