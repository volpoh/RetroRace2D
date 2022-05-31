package gui;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GUICar {
    private double positionX;
    private double positionY;
    double velYDefault = 0.5;
    private double velX = 0, velY=0;
    AnimationTimer atS;
    ImageView car = new ImageView();

    public GUICar(char startingPoint) throws IOException {
        InputStream stream = new FileInputStream("src/main/resources/car.png");
        Image img = new Image(stream);
        car.setImage(img);
        if(startingPoint == 'r'){ car.setX(GUI.xDimFinestra-200); }
        else if(startingPoint == 'l'){ car.setX(200); }
        car.setY(600);
        car.setPreserveRatio(true);
        positionX = car.getX();
        positionY = car.getY();
        car.setFitHeight(30);
        car.setFitWidth(30);

        atS = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if( ((car.getX()+velX)>0) && ((car.getX()+velX) < GUI.xDimFinestra-20)){
                    positionX += velX;
                }
                car.setX(positionX);

                if( (car.getY()+velY)<700){
                    positionY += velY;
                }


                car.setY(positionY);
            }
        };

    }

    void inclinazione (){}
    void setVelX(double speed){
        velX=speed;
    }
    void setVelY(double speed){
        velY=speed;
    }
    double getPositionX(){
        return positionX;
    }
    double getPositionY(){return positionY;}


/*
    void up(){
        positionY = positionY - speed;
        car.setY(positionY);
    }

    void down(){
        positionY = positionY + speed;
        car.setY(positionY);
    }
    void left(){
        positionX = positionX - speed;
        car.setX(positionX);
    }
    void right(){
        positionX = positionX + speed;
        car.setX(positionX);
    }
*/



}
