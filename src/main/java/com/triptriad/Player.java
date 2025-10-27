package com.triptriad;

import java.util.Random;
import java.util.ArrayList;

public class Player {
    private int playerNumber;
    private int id = 1;
    private String playerName;
    private ArrayList<Card> hand = new ArrayList<>();

    public Player(String playerName){
        playerNumber = id;
        id++;
        this.playerName = playerName;
    }

    public int getPlayerNumber(){ return this.playerNumber; }
    
    public String getPlayerName(){ return this.playerName; }
    public void setPlayerName(String playerName ){ this.playerName = playerName; }


    /*Sets random hands using a random number and the index of the cards, cards can be duplicated in a hand */
    public void setRandomHand(ArrayList<Card> deck){
        Random R = new Random();

        for(int i = 0; i < 5; i++){
            hand.add(deck.get(R.nextInt(111)));
        }
    }

    public ArrayList<Card> getHand() { return this.hand; }
    

    public void printHand(){
        for(int i = 0; i < this.hand.size(); i++){
            System.out.println("Index: " + i + ": " + hand.get(i));
        }

    }

    public Card getCard(int i){
        return hand.get(i);
    }

    public void removeCard(int i){
        this.hand.remove(i);
    }

        /*Uses the cardLineArray method to create String arrays based off of input cards and then adds these arrays to a 2D array so it can
        print the cards side by side going line by line*/
    public void printHandCards(){
        String[][] handPrint = new String[12][this.hand.size()];

        for(int i = 0; i < this.hand.size(); i++){
            String[] card = cardLineArray(hand.get(i));
            for(int j = 0; j < 12; j++){
                handPrint[j][i] = card[j];
            }
        }

        for(int i = 0; i < 12; i ++){
            for(int j = 0; j < hand.size(); j++){
                System.out.print(handPrint[i][j] + "  ");
            }
            System.out.println();
        }
    }


        /*This method is used to create String arrays based on Card objects, this way the cards can be printed out side
          by side using a 2D array like for the printHandCards and printBoard methods*/
    public static String[] cardLineArray(Card card)
    {
        String[] cardView = new String[12];

        String mid = "|               |";
        String edge = " --------------- ";

        cardView[0] = edge;
        cardView[11] = edge;

        if(card.getStats().getTop()==10){cardView[1] = "|       A       |"; }
        else{ cardView[1] = "|       "+ card.getStats().getTop() +"       |"; }

        if(card.getStats().getBottom()==10){cardView[10] = "|       A       |"; }
        else{ cardView[10] = "|       "+ card.getStats().getBottom() +"       |"; }

        for(int i = 2; i<10; i++)
        {
                if(i == 5)
                {
                    if(card.getStats().getLeft() == 10 && card.getStats().getRight() ==10 ){
                        cardView[i] = ("|A             A|");
                    }
                    else if(card.getStats().getLeft() == 10){
                        cardView[i] = ("|A             "+ card.getStats().getRight()+ "|");
                    }
                    else if(card.getStats().getRight() == 10){
                        cardView[i] = ("|"+ card.getStats().getLeft()+"             A|");
                    }
                    else{
                    cardView[i] = ("|"+ card.getStats().getLeft()+"             "+ card.getStats().getRight()+ "|");
                    }
                }
                else if(i == 3)
                {   
                    int space = (16 - card.getName().length())/2;   //The cards are 17 characters long so 15 characters excluding the "|" on either side.

                    cardView[i] = "|"; 

                    for(int j = 0; j < space; j ++) { cardView[i] += " ";}
                    cardView[i] += (card.getName());

                    if(card.getName().length() % 2 == 0){ space--; }
                    
                    for(int j = 0; j < space; j ++) { cardView[i] += " ";}
                    cardView[i] += "|";

                }
                else{
             cardView[i] = mid;
                }
        }

        return cardView;
    }






}
