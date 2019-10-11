/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Vutomi
 */
public class BoardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private ImageView playerCard;
    @FXML private ImageView cpuCard;
    @FXML private ImageView deck;
    
    @FXML private StackPane leftBox; 
    @FXML private StackPane rightBox;  
    @FXML private StackPane centerBox;
    @FXML private StackPane bottomPane;
    
    @FXML private Button hit, stand, deal;
    @FXML HBox buttonBox;
    
    @FXML private Label humanSum;
    @FXML private Label humanAccount;
    @FXML private Label humanWin;
    
    @FXML private Label cpuSum;
    @FXML private Label cpuWin;
    
    @FXML
    private void handleUserHit(ActionEvent event){
        userHit();
    }
    
    @FXML
    private void handleDeal(ActionEvent event){
        leftBox.getChildren().clear();
        rightBox.getChildren().clear();
        humanSum.setText("");
        humanWin.setText("");
        cpuSum.setText("");
        cpuWin.setText("");
        initializeGame();
        buttonBox.setDisable(false);
        deal.setDisable(true);
    }
    @FXML
    private void handleUserStand(ActionEvent event){
        game.setHumanStand(true);
        buttonBox.setDisable(true);
        while ( !game.isCpuStand() ){
            if ( game.getCpu().getSum() < 17 ){
                cpuHit();
            }else{
                game.setCpuStand(true);
                endGame(game.getCpu(), 0);
            }
        }
    }
    private Game game;
    
    public void endGame(Player player, int res){
        game.setRun(false);
        String humanRes = "";
        String cpuRes = "";
        if ( res == 1 ){
           if ( player.getName().equals("You")){
               humanRes = "WINNER";
               cpuRes = "LOSER";
           } else{
               cpuRes = "WINNER";
               humanRes = "LOSER";
           }
        }
        if ( res == -1){
            // player won with res = -1
            if ( player.getName().equals("Dealer")){
               humanRes = "WINNER";
               cpuRes = "LOSER";
           } else{
               cpuRes = "WINNER";
               humanRes = "LOSER";
           }
        }
        if ( res == 0 ){
            // One of the players took a stand
            if ( game.getHuman().getSum() == game.getCpu().getSum()){
                // it's a ties
                cpuRes = "IT'S A TIE";
                humanRes = "IT'S A TIE";
            }else if ( game.getHuman().getSum() > game.getCpu().getSum()){
                // human wins
                humanRes = "WINNER";
               cpuRes = "LOSER";
            }else{
                //cpu wins
                cpuRes = "WINNER";
                humanRes = "LOSER";
            }
        }
        Player cpu = game.getCpu();
        System.out.println(Arrays.toString(cpu.getListCards().toArray()));
        for(int i = 1; i < cpu.getListCards().size(); i++){
            System.out.println(i + ". " + cpu.getListCards().get(i));
            Image image = new Image("assets/cards/" + cpu.getListCards().get(i) + ".png");
            ImageView imageView = (ImageView) rightBox.getChildren().get(i);
            imageView.setImage(image);
        }
        
        
        humanWin.setText(humanRes);
        cpuWin.setText(cpuRes);
        deal.setDisable(false);
    }
    public void initializeGame(){
        game = new Game();
        //initialCards
        userHit();
        cpuHit();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    public void userHit(){
        int res  = game.hit(game.getHuman());
        if ( !(res == 0)){
            // End the game after winning
            game.setHumanStand(true);
            game.setCpuStand(true);
            buttonBox.setDisable(true);
            //account += 100;
            endGame(game.getHuman(), res);
        }
        addHumanCard(game.getHuman().getLastCard(), game.getHuman().getListCards().size()-1);
        humanSum.setText(String.valueOf(game.getHuman().getSum()));
    }
    public void cpuHit(){
        int res  = game.hit(game.getCpu());
        if ( !(res == 0)){
            // End the game after winning
            game.setHumanStand(true);
            game.setCpuStand(true);
            //account += 100;
            addCpuCard(game.getCpu().getLastCard(), game.getCpu().getListCards().size()-1);
            cpuSum.setText(String.valueOf(game.getCpu().getSum()));
            endGame(game.getCpu(), res);
        }
        addCpuCard(game.getCpu().getLastCard(), game.getCpu().getListCards().size()-1);
        cpuSum.setText(String.valueOf(game.getCpu().getSum()));
    }
    public void addCpuCard(String card, int shift){
        ImageView imageView;
        if ( shift == 0 ){
            imageView = getCardImage(card);
        }else{
            imageView = getCardImage(card);
        }
        rightBox.getChildren().add(imageView);
        rightBox.setMargin(imageView, new Insets(0, 0,0,shift*24));
    }
    public void addHumanCard(String card, int shift){
        ImageView imageView = getCardImage(card);
        leftBox.getChildren().add(imageView);
        leftBox.setMargin(imageView, new Insets(0, 0,0,shift*40));
    }
    protected ImageView getCardImage(String name){
        Image image = new Image("assets/cards/" + name + ".png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(180.0f);
        imageView.setFitWidth(120.0f);
        return imageView;
    }
}
