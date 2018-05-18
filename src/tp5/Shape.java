package tp5;

import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * @author Slimane Siroukane
 */
public class Shape {
    private final int SHAPE_CIRCLE = 0;
    private final int SHAPE_RECTANGLE = 1;

    private final Color[] COLORS = {
            Color.RED,
            Color.BLUE,
            Color.GREEN,
            Color.YELLOW,
            Color.ORANGE,
            Color.PURPLE,
            Color.LIGHTBLUE,
            Color.PINK,
            Color.BLACK
    };

    private int shape;
    private int color;
    private int size;
    private int rotate_angle;
    private float zoom_value;
    private float speed;

    private Rectangle rectangle;
    private Circle circle;

    private RotateTransition rotateTransition;
    private ScaleTransition scaleTransition;

    Shape(int shape, int color, int size){
        this.shape = shape;
        this.color = color;
        this.size = size;

        if(isCircle()){
            Circle circle = new Circle(size / 2);
            circle.setFill(COLORS[color]);
            this.circle = circle;
        }else if(isRectangle()){
            Rectangle rectangle = new Rectangle(size, size);
            rectangle.setFill(COLORS[color]);
            this.rectangle = rectangle;
        }
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

    public Circle getCircle(){
        return circle;
    }

    public boolean isRectangle(){
        return shape == SHAPE_RECTANGLE;
    }

    public boolean isCircle(){
        return shape == SHAPE_CIRCLE;
    }

    public void setRotateAngle(int angle){
        rotate_angle = angle;
    }

    public void setZoomValue(float zoom){
        zoom_value = zoom;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void setAnimation(){
        if(isRectangle()){
            rotateTransition = new RotateTransition(Duration.millis(speed), rectangle);
            rotateTransition.setByAngle(rotate_angle);
            rotateTransition.setCycleCount(Timeline.INDEFINITE);
            rotateTransition.setAutoReverse(true);
        }else if(isCircle()){
            scaleTransition = new ScaleTransition(Duration.millis(speed), circle);
            scaleTransition.setByX(zoom_value);
            scaleTransition.setByY(zoom_value);
            scaleTransition.setCycleCount(Timeline.INDEFINITE);
            scaleTransition.setAutoReverse(true);
        }
    }

    public void changeStatus(boolean status){
        if(status){ // play
            if(isRectangle()){
                rotateTransition.play();
            }else if(isCircle()){
                scaleTransition.play();
            }
        }else{ // stop
            if(isRectangle()){
                rotateTransition.pause();
            }else if(isCircle()){
                scaleTransition.pause();
            }
        }
    }
}
