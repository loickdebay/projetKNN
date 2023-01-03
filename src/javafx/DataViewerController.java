package javafx;

import eu.iamgio.pokedex.exception.PokedexException;
import eu.iamgio.pokedex.pokemon.Pokemon;
import eu.iamgio.pokedex.pokemon.Sprite;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import knn.Iris;
import knn.KNN;
import javafx.scene.image.ImageView;
import knn.Titanic;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataViewerController {
    @FXML
    private Label dataLabel;
    @FXML
    private ImageView pokemonImageView;
    @FXML
    private ImageView firstTypeImage;
    @FXML
    private ImageView secondTypeImage;
    String missingPNG= "images/Pokemon/missing.png";

    public void setDataLabel(KNN dataLabel) {
        if(dataLabel instanceof knn.Pokemon){
            setPokemonImages(dataLabel);
        }
        if (dataLabel instanceof knn.Titanic) {
            setTitanicImages(dataLabel);
        }
        if (dataLabel instanceof knn.Iris){
            setIrisImages(dataLabel);
        }
    }

    public String getNonAnimated(String name) {
        return Pokemon.fromName(name.toLowerCase()).getSprite(Sprite.Type.FRONT_DEFAULT).getUrl();
    }

    public String getAnimated(String name){
        int id = Pokemon.fromName(name.toLowerCase()).getId();
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/"+id+".gif";
    }

    private boolean checkIfExists(String link) throws IOException {
        URL u = new URL(link);
        HttpURLConnection huc = (HttpURLConnection) u.openConnection();
        huc.setRequestMethod("GET");
        huc.setConnectTimeout(2000);
        huc.connect();
        int code = huc.getResponseCode();
        return code == 200;
    }

    public String getImagesLink(String name) throws IOException {
        if(checkIfExists(getAnimated(name))) {
            return getAnimated(name);
        }else{
            return getNonAnimated(name);
        }
    }
    public void setPokemonImages(KNN dataLabel){
        String imageURL;
        String imageURLfirstType;
        String imageURLsecondType;
        if(dataLabel!=null){
            try{
                imageURL = getImagesLink(((knn.Pokemon) dataLabel).getName());
                imageURLfirstType = "images/Pokemon/"+Pokemon.fromName(((knn.Pokemon) dataLabel).getName().toLowerCase()).getTypes().getFirst().getId()+".png";
                if(!((knn.Pokemon) dataLabel).getType2().equals("")){
                    imageURLsecondType = "images/Pokemon/"+Pokemon.fromName(((knn.Pokemon) dataLabel).getName().toLowerCase()).getTypes().getSecond().getId()+".png";
                }else{
                    imageURLsecondType=missingPNG;
                }
            }catch (PokedexException | IOException p){
                imageURL=missingPNG;
                imageURLfirstType=missingPNG;
                imageURLsecondType=missingPNG;
            }
        } else{
            imageURL=missingPNG;
            imageURLfirstType=missingPNG;
            imageURLsecondType=missingPNG;
        }
        try {
            this.dataLabel.setText(dataLabel.toStringJFX());
        } catch (NullPointerException e) {
            this.dataLabel.setText("No data selected");
        }
        Image imagePoke = new Image(imageURL);
        pokemonImageView.setImage(imagePoke);
        firstTypeImage.setImage(new Image(imageURLfirstType));
        secondTypeImage.setImage(new Image(imageURLsecondType));
    }
    public void setTitanicImages(KNN dataLabel){
        Titanic titanic = (Titanic) dataLabel;
        if(titanic!=null){
            this.dataLabel.setText(dataLabel.toStringJFX());
            if(titanic.isSurvived()){
                pokemonImageView.setImage(new Image("images/Titanic/survived.jpg"));
            }else{
                pokemonImageView.setImage(new Image("images/Titanic/notSurvived.jpg"));
            }
        }
        firstTypeImage.setImage(new Image(missingPNG));
        secondTypeImage.setImage(new Image(missingPNG));
    }
    public void setIrisImages(KNN dataLabel){
        Iris iris = (Iris) dataLabel;
        this.dataLabel.setText(dataLabel.toStringJFX());
        if(iris!=null){
            pokemonImageView.setImage(new Image(missingPNG));
            if(iris.getVariety().equals("Setosa")){
                pokemonImageView.setImage(new Image("images/Iris/setosa.jpg"));
            }
            if(iris.getVariety().equals("Virginica")){
                pokemonImageView.setImage(new Image("images/Iris/virginica.jpg"));
            }
            if(iris.getVariety().equals("Versicolor")){
                pokemonImageView.setImage(new Image("images/Iris/versicolor.jpg"));
            }
        }
        firstTypeImage.setImage(new Image(missingPNG));
        secondTypeImage.setImage(new Image(missingPNG));
    }
}
