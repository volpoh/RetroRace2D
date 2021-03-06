package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import map.Chunk;
import map.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * GUI of the Map.
 * @author Vlad Leonte Vasile
 * @version 0.2.0
 * @see Map
 * @since 02/05/2022
 */

//allora, to-do list di oggi:
/*
    - rendere più facile la creazione della mappa
    - renderla una funzione
    - migliorare il movimento della macchina
    - rendere fixed lo schermo, non permettendo di visionare lo spazio bianco
    - rimpicciolire la macchina perché ora come ora è troppo grande


 * ho aggiornato (messo anche su giT) la mappa con anche al funzione lenght che ho visto che volpo voleva
 tipo getLength()? si per ora ho fatto una cosa bruttissima che funziona, ma non è molto elegante
 */




public class GUI extends Application{
    /**
     * The map.
     */
    Map map;

    /**
     * Map Width.
     */
    int MAP_WIDTH;

    /**
     * Map Height.
     */
    int MAP_HEIGHT;

    /**
     * Starting Side of the Map.
     */
    char startingPoint;

    final static int xDimFinestra=1024;
    final static int yDimFinestra=700;

    @Override
    public void start(Stage stage) throws IOException {
        generateMap();
        GUICar sprite = new GUICar(startingPoint);
        GUIMap mapB = new GUIMap(this.map);

        mapB.background.setFitWidth(MAP_WIDTH*4);
        mapB.background.setFitHeight(MAP_HEIGHT*4);
        mapB.background.setPreserveRatio(true);

        mapB.background.setX(0);

        Group root = new Group(mapB.background, sprite.car);
        Scene scene = new Scene(root, xDimFinestra, yDimFinestra);


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                switch (event.getCode()){
                    case W:
                        case UP:{

                        if (sprite.getPositionY() < 250 && mapB.getYMap()<-720) {
                            mapB.setVelYB(15);
                            sprite.setVelY(0);

                        } else {
                            sprite.setVelY(-2);
                        }
                        break;
                    }
                    case S:
                        case DOWN:{
                        if (sprite.getPositionY() > 500){
                            mapB.setVelYB(-2);
                            sprite.setVelY(0);

                        } else {
                            sprite.setVelY(2);
                        }
                        break;
                    }

                    case A:
                        case LEFT:{
                            sprite.setVelX(-2);
                        break;
                    }

                    case D:
                        case RIGHT:{
                        sprite.setVelX(2);
                        break;
                    }


                }

                //System.out.println(event.getCode());
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case W:
                    case UP:{
                        sprite.setVelY(-sprite.velYDefault);
                        mapB.setVelYB(0);
                        break;
                    }
                    case S:
                    case DOWN:{
                        sprite.setVelY(0);
                        mapB.setVelYB(0);
                    }

                    case A:
                    case LEFT:{
                        sprite.setVelX(0);
                        break;
                    }

                    case D:
                    case RIGHT:{
                        sprite.setVelX(0);
                        break;
                    }
                }
            }
        });

        mapB.atB.start();
        sprite.atS.start();
        stage.setTitle("Car Race");
        stage.setScene(scene);
        stage.show();
    }

    public boolean generateMap() throws IOException {
        this.map = new Map(getParameters().getRaw().get(0));
        this.MAP_WIDTH = Chunk.CHUNK_WIDTH;
        this.MAP_HEIGHT = Chunk.CHUNK_WIDTH*this.map.length();
        List<String> chunkList = this.map.getChunksNames();

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

            startingPoint = chunkList.get(0).charAt(2);
            System.out.println();

            return true;
        }

        return false;
    }


    public static void main(String[] args) {
        System.out.println(Map.generateSeed());
        launch(args[0]);
    }
}