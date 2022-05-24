package com.example.carrace12;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import map.Map;
import map.Chunk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static map.Chunk.CHUNK_WIDTH;

public class mapBackground {
    ImageView background = new ImageView();
    AnimationTimer atB;
    private double yMap;
    private double velY;
    public mapBackground(Map map) throws IOException {
        yMap = -(map.length()*CHUNK_WIDTH*4);
        System.out.println("mapB: " + yMap);
        InputStream stream = new FileInputStream("src/main/resources/map.png");
        Image img = new Image(stream);
        background.setImage(img);
        atB = new AnimationTimer() {
            @Override
            public void handle(long l) {
                yMap += velY;
                background.setY(yMap + ImplementazioneGrafica.yDimFinestra);
            }
        };
    }

    void setVelYB(int speed){
        velY=speed;
    }
}
