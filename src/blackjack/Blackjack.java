/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Vutomi
 */
public class Blackjack extends Application {
    protected ImageView getCardImage(String name){
        Image image = new Image("assets/cards/" + name + ".png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(180.0f);
        imageView.setFitWidth(120.0f);
        return imageView;
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Board.fxml"));
        
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("assets/Login.css");
        
        stage.setTitle("Blackjack");
        
        /**Group root = new Group();
        
        Canvas canvas = new Canvas(500, 600);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.fillRoundRect(10, 10, 120, 180, 10, 10);
        gc.strokeRoundRect(150, 10, 120, 180, 10, 10);
        
        Image image = new Image("assets/cards/3D.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(180.0f);
        imageView.setFitWidth(120.0f);
        
        ImageView imageView2 = getCardImage("JD");
        imageView2.setX(400);
        imageView2.setY(10);
        imageView.setX(10);
        imageView.setY(10);
        Path path = new Path();
        path.getElements().add(new MoveTo(imageView.getX() +100, imageView.getY()+90));
        path.getElements().add(new HLineTo(imageView2.getX()+40));
        System.out.println(imageView.getX() + " - " + imageView.getY());
        System.out.println(imageView2.getX() + " - " + imageView2.getY());
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(1500));
        pathTransition.setNode(imageView);
        pathTransition.setPath(path);
        //pathTransition.setCycleCount(50);
        pathTransition.setAutoReverse(true);
        //pathTransition.play();
        System.out.println(imageView.getX() + " - " + imageView.getY());
        Button btn = new Button();
        btn.setText("Transition");
        btn.setLayoutX(200.0f);
        btn.setLayoutY(400.0f);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pathTransition.play();
            }
        });
        root.getChildren().add(imageView);
        root.getChildren().add(imageView2);
        root.getChildren().add(btn);**/
        stage.setScene( scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
