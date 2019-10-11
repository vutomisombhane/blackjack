/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Vutomi
 */
class CardComparator implements Comparator<String>{
    public static int getValue(String card){
        String strNum = card.substring(0, card.length()-1);
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
    @Override
    public int compare(String str1, String str2){
        if ( getValue(str1) == getValue(str2)){
            return 0;
        }else if( getValue(str1) > getValue(str2) ){
            return -1;
        }
        return 1;
    }
}
public class Player {
    
    private ArrayList<String>  listCards;
    private ArrayList<String> sortedCards;
    private int sum;
    private String name;
    
    public Player(String name){
        this.name = name;
        listCards = new ArrayList();
    }
    public String getName(){
        return this.name;
    }

    public ArrayList<String> getListCards() {
        return listCards;
    }
    public String getLastCard(){
        return listCards.get(listCards.size()-1);
    }
    public void setListCards(ArrayList<String> listCards) {
        this.listCards = listCards;
    }
    
    public int getSum(){
        return sum;
    };
    public String getDetails(){
        return name + " cards: " + Arrays.toString(listCards.toArray()) + ", sum: " + getSum();
    }
    private void calcSum(){
        sortedCards = new ArrayList(listCards);
        Collections.sort(sortedCards, new CardComparator());
        
        int s = 0;
        for (int i = 0; i < sortedCards.size(); i++){
            int c = CardComparator.getValue(sortedCards.get(i));
            
            if ( c == 1 && i == sortedCards.size()-1){
                if ( (s + 11) <= 21 ){
                    s += 11;
                }else{
                    s++;
                }
            }else{
                s += c;
            }
        }
        this.sum = s;
    }
    
    public void addCard(String card){
        listCards.add(card);
        calcSum();
    }
}
