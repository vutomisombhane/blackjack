/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Vutomi
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void shuffle(String[] nums){
        /**for(int i = 0; i < nums.length; i++){
                nums[i] = i;
        }**/
        Random random = new Random();
        for(int i = nums.length-1; i >= 1; i--){
            int j = random.nextInt(i);
            String tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
    public static int getValue(String card){
        
        String strNum = card.substring(0, card.length()-1);
        System.out.println(strNum);
        switch(strNum){
            case "A":
                return 1;
            case "J":
                return 10;
            case "Q":
                return 10;
            case "K":
                return 10;
            default:
                return Integer.parseInt(strNum);
        }
    }
    
    
    private ArrayList<String> deck;
    private Player human, cpu;
    private boolean run, humanStand, cpuStand;
    private int account;

    public Player getHuman() {
        return human;
    }

    public void setHuman(Player human) {
        this.human = human;
    }

    public ArrayList<String> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<String> deck) {
        this.deck = deck;
    }

    public Player getCpu() {
        return cpu;
    }

    public void setCpu(Player cpu) {
        this.cpu = cpu;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public boolean isHumanStand() {
        return humanStand;
    }

    public void setHumanStand(boolean humanStand) {
        this.humanStand = humanStand;
    }

    public boolean isCpuStand() {
        return cpuStand;
    }

    public void setCpuStand(boolean cpuStand) {
        this.cpuStand = cpuStand;
    }
    public Game(){
        String[] arrayDeck = new String[]{
            "AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS",
            "AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC",
            "AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD",
            "AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH",
        };
        //Shuffle the deck of cards
        shuffle(arrayDeck);
        
        // create an ArrayList using the shuffled cards
        deck = new ArrayList(Arrays.asList(arrayDeck));
        
        //Initialize the players
        human = new Player("You"); cpu = new Player("Dealer");
        
        // The game will keep on running as long as this is true
        run = true;
        humanStand = false; cpuStand = false;
        account = 600;
    }
    public int hit(Player player){
        
        player.addCard(deck.get(0));
        deck.remove(0);
        
        if ( player.getSum() == 21 ){
            // Player wins
            return 1;
        }else if( player.getSum() > 21){
            return -1; // PLayer loses
        }
        return 0; // the game continues
    }
    public void endGame(Player player, int res){
        this.run = false;
        if ( res == 1){
            // player won with res = 1
            System.out.println(player.getName() + " won!");
        }
        if ( res == -1){
            // player won with res = -1
            System.out.println(player.getName() + " lost!");
        }
        if ( res == 0 ){
            // One of the players took a stand
            if ( human.getSum() == cpu.getSum()){
                // it's a ties
                System.out.println("It's a tie");
            }else if ( human.getSum() > cpu.getSum()){
                // human wins
                System.out.println(human.getName() + " won!");
            }else{
                //cpu wins
                System.out.println(cpu.getName() + " won!");
            }
        }
        //Print the lists of cards
        System.out.println(this.cpu.getDetails());
        System.out.println(this.human.getDetails());
    }
    public void humanPlay(){
        int res  = this.hit(human);
        if (res == 1){
            // End the game after winning
            this.humanStand = true;
            this.cpuStand = true;
            account += 100;
            endGame(human, res);
        }
        if ( res == -1){
            //End the game after losing
            this.humanStand = true;
            this.cpuStand = true;
            account -= 100;
            endGame(human, res);
        }
    }
    public void cpuPlay(){
        int res = this.hit(cpu);
        if (res == 1){
            //cpu wins
            this.cpuStand = true;
            account -= 100;
            endGame(cpu, res);
        }
        if ( res == -1){
            //cpu loses
            this.cpuStand = true;
            account += 100;
            endGame(cpu, res);
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        
        Game game = new Game();
        
        while( game.run ){
            
            //initialCards
            game.hit(game.human);
            game.hit(game.cpu);
            
            while ( !game.humanStand ){
                System.out.println(game.human.getDetails());
                System.out.println("hit or Stand? h-hit, s-Stand :");
                if( in.next().equals("h") ){
                    game.humanPlay();
                }else{
                    game.humanStand = true;
                }
            }
            while ( !game.cpuStand ){
                if ( game.cpu.getSum() < 15 ){
                    game.cpuPlay();
                }else{
                    game.cpuStand = true;
                    game.endGame(game.cpu, 0);
                }
            }
        }
    }
}
