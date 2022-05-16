package com.example.carrace12;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.awt.color.ICC_Profile;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;

import map.Chunk;
import map.Way;
import map.Map;


/**
 * GUI of the Map.
 * @author Vlad Leonte Vasile
 * @version 0.2.0
 * @see map.Map
 * @since 02/05/2022
 */

//allora, to-do list:
/*
    - rendere più facile la creazione della mappa
    - renderla una funzione
    - migliorare il movimento della macchina
    - rendere fixed lo schermo, non permettendo di visionare lo spazio bianco
    - rimpicciolire la macchina perché ora come ora è troppo grande
 */

public class ImplementazioneGrafica extends Application{
    int newY, newX;
    int cameraX, cameraY;
    int incremento = 5;

    /**
     * Map of the game.
     */
    Map map;

    /**
     * Set containing the keys pressed.
     */
    Set<KeyCode> pressedKeys;

    /**
     * Map Width.
     */
    int MAP_WIDTH;

    /**
     * Map Height.
     */
    int MAP_HEIGHT;

    /**
     * Initializes the GUI.
     *
     * @author Marco Marrelli
     * @version 0.3.0
     * @since 12/05/2022
     * @param map, the map to be displayed
     */
    public void init(Map map) {
        this.map = map;
        this.pressedKeys = new HashSet<>();

        this.MAP_WIDTH = Chunk.CHUNK_WIDTH;
        this.MAP_HEIGHT = Chunk.CHUNK_WIDTH*map.length();
    }

    /**
     * Generates the graphic map and layout map.
     *
     * @author Marco Marrelli
     * @since 10/05/2022
     * @version 0.3.0
     * @return boolean, true if the map is generated, false otherwise.
     * @throws IOException
     */
    public boolean generateMap() throws IOException {
        List<String> chunkList = map.getChunksNames();

        if(chunkList == null){ return false; }

        int nimx = 0, nimy = 0;
        BufferedImage showedResult = new BufferedImage(MAP_WIDTH, MAP_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage layoutResult = new BufferedImage(MAP_WIDTH, MAP_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics showedG = showedResult.getGraphics();
        Graphics layoutG = layoutResult.getGraphics();

        for (String image : chunkList) {
            String showedPath = "src/main/resources/ChunkShowed/" + image + ".png";
            String layoutPath = "src/main/resources/ChunkLayouts/" + image + ".png";
            BufferedImage showedBI = ImageIO.read(new File(showedPath));
            BufferedImage layoutBI = ImageIO.read(new File(layoutPath));

            if(showedBI == null || layoutBI == null){ return false; }

            showedG.drawImage(showedBI, nimx, nimy, null);
            layoutG.drawImage(layoutBI, nimx, nimy, null);

            nimy += MAP_WIDTH;
            if (nimy > showedResult.getHeight() || nimy > layoutResult.getHeight()) {
                nimy = 0;
                nimx += MAP_WIDTH;
            }
        }

        boolean checkShowedMap = ImageIO.write(showedResult, "png", new File("src/main/resources/map.png"));
        boolean checkLayoutMap = ImageIO.write(layoutResult, "png", new File("src/main/resources/mapLayout.png"));

        if(checkShowedMap && checkLayoutMap){
            Collections.reverse(chunkList);
            System.out.println(chunkList);
            return true;
        }

        return false;
    }

    @Override
    public void start(Stage stage) throws IOException {
        init(new Map());
        generateMap();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        InputStream stream = new FileInputStream("src/main/resources/map.png");
        Image img = new Image(stream);
        ImageView background = new ImageView();
        background.setImage(img);
        //background.setX(-70);
        //background.setY(-700);
        background.setX(0);
        background.setY(0);
        background.setPreserveRatio(true);
        background.setFitWidth(MAP_WIDTH*2);
        background.setFitHeight(MAP_HEIGHT*2);

        stream = new FileInputStream("src/main/resources/car.png");
        img = new Image(stream);
        ImageView car = new ImageView();
        car.setImage(img);
        car.setX(300);
        car.setY(400);
        car.setPreserveRatio(true);


        TranslateTransition tr = new TranslateTransition(Duration.millis(3000));
        // tr.setNode(background);
        //tr.setDelay(Duration.millis(1000));
        tr.setByY(700);
        tr.setCycleCount(TranslateTransition.INDEFINITE);
        tr.setInterpolator(Interpolator.LINEAR);
        tr.play();

        Group root = new Group(background, car);
        Scene scene = new Scene(root, 600, 500);
        Camera cam = new ParallelCamera();
        cam.setNearClip(100);
        cam.setFarClip(200);

        //cam.setTranslateX(-100);
        // cam.setTranslateY(-15);
        scene.setCamera(cam);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
/*
                if (event.getCode() == S) {
                    newY = newY - incremento;
                }
                if (event.getCode() == W) {
                    newY = newY + incremento;
                }
                if (event.getCode() == D) {
                    newX = newX - incremento;
                }
                if (event.getCode() == A) {
                    newX = newX + incremento;
                }

                background.setTranslateX(newX);
                background.setTranslateY(newY);

 */
                pressedKeys.add(event.getCode());
                if(!pressedKeys.isEmpty()){
                    for(KeyCode key : pressedKeys){
                        System.out.println(key);
                        switch(key){
                            case W:
                            case UP:
                                newY = newY + incremento;
                                break;
                            case A:
                            case LEFT:
                                newX = newX + incremento;
                                break;
                            case S:
                            case DOWN:
                                newY = newY - incremento;
                                break;
                            case D:
                            case RIGHT:
                                newX = newX - incremento;
                                break;
                        }
                        background.setTranslateX(newX);
                        background.setTranslateY(newY);
                    }
                }
                //System.out.println(event.getCode());
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                pressedKeys.remove(event.getCode());
            }
        });

        stage.setTitle("Car Race");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws java.lang.IllegalStateException{
        launch();
    }
}