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
    private double yMapStart;
    public mapBackground(Map map) throws IOException {
        yMap = -(map.length()*CHUNK_WIDTH*4);
        yMapStart = yMap;
        System.out.println("mapB: " + yMap);
        InputStream stream = new FileInputStream("src/main/resources/map.png");
        Image img = new Image(stream);
        background.setImage(img);
        atB = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if((yMap+velY)>=yMapStart && (yMap+velY)<=-720){
                    yMap += velY;
                    System.out.println("POSIZIONEEE:"+yMap);
                }
                background.setY(yMap + ImplementazioneGrafica.yDimFinestra);
            }
        };
    }

    void setVelYB(int speed){
        velY=speed;
    }
    double getYMapStart() {
        return yMapStart;
    }
    double getYMap(){
        return yMap;
    }
}
