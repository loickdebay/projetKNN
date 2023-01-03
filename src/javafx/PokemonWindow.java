package javafx;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * allows the pokemon window to be created
 */
public class PokemonWindow extends Stage {
	/**
	 * generated the pokemon window
	 * @throws IOException throws exception if the fxml file is not found
	 */
	public PokemonWindow() throws IOException {
		URL url = getClass().getResource("PokemonVisualizer.fxml");
		FXMLLoader loader = new FXMLLoader(url);
		Parent root = loader.load();
		PokemonWindowController controller = (PokemonWindowController)loader.getController();
		controller.listChoiceBoxesChoices();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("chart.css").toExternalForm());
		scene.getStylesheets().add(getClass().getResource("DarkMode.css").toExternalForm());
		this.setScene(scene);
		this.setTitle("Pokemon Visualizer");
		this.show();
	}

}
