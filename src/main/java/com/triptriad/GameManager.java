package com.triptriad;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class GameManager {

    public static void playGame() {
        
        Random R = new Random();
        Scanner sc = new Scanner(System.in);
        boolean isPlayerOneTurn;
        boolean fullBoard = false;
        boolean freeSlot = false;
        Card[][] board = new Card[3][3];
        Player playerOne = new Player("Player 1");
        Player playerTwo = new Player("Player 2");
        int gridRow = 0;
        int gridCol = 0;
        int playerOneScore = 0;
        int playerTwoScore = 0;

        
        //Call in the cards from the json file into an Array of object Cards
        ArrayList<Card> deck = new ArrayList<>();
        try {
            CardRepository.loadCards("cards.json");
            deck = CardRepository.getCards();    
        }
        catch (Exception e) 
        {
            System.out.println("Error loading cards");
            e.printStackTrace();
        }
     
        //Set each player with a random hand of cards. Plans to include a hand builder feature later
        playerOne.setRandomHand(deck);
        playerTwo.setRandomHand(deck);
        
        //Randomise the first turn for player 1 or 2
        int firstTurn = R.nextInt(1,3);

        if(firstTurn == 1)
            {System.out.println(playerOne.getPlayerName() + " is going first!");
            isPlayerOneTurn = true; }
        else
            { System.out.println(playerTwo.getPlayerName() + " is going first!"); 
            isPlayerOneTurn = false; }

            printBoard(board);
            //Start the Game
            while(!fullBoard) {

                    Player currentPlayer = (isPlayerOneTurn) ? playerOne : playerTwo;  

                    freeSlot = false;

                    System.out.println("This is your hand:");
                    currentPlayer.printHandCards();
                    currentPlayer.printHand();

                    System.out.println(currentPlayer.getPlayerName() + ", your turn!\nUse card index to pick a card.");
                    int cardPlayed = sc.nextInt();
                    
                    Card playedCard = currentPlayer.getCard(cardPlayed);
                    if(currentPlayer == playerOne){
                    playedCard.setPossession(true);
                    }

                
                    //A boolean check to make sure that players can't overwrite cards on the grid.
                    while(!freeSlot){
                    System.out.println("Pick a slot on the grid.\nYou should enter the row position then the column position.");
                    gridRow= sc.nextInt();
                    gridCol= sc.nextInt();

                    if(board[gridRow][gridCol] == null){
                        freeSlot = true;
                    }
                    else{
                        System.out.println("Sorry there's already a card there, please pick again");
                    }
                }
                    board[gridRow][gridCol] = playedCard;
                    fight(board, gridRow, gridCol);

                    printBoard(board);
                    currentPlayer.removeCard(cardPlayed);

                    //Check to see if the board is full so the game can proceed to tally the scores.
                    if(currentPlayer.getHand().size() == 0){
                        fullBoard = true;
                    }
                    isPlayerOneTurn = !isPlayerOneTurn;
                }

                /*Score checks runs through the grid to see how many cards belong to player one, gets the complimentary number for player two
                and adds the remaining number of cards in the players hands to determine who the winner is or if it is a draw. */
                for(int i = 0; i < board[0].length; i++){
                    for(int j = 0; j < board.length; j ++){
                        if(board[i][j].getPossesion() == true){
                            playerOneScore++;
                        }
                    }
                }
                playerTwoScore = (9-playerOneScore) + playerTwo.getHand().size();
                playerOneScore += playerOne.getHand().size();

                if(playerOneScore == playerTwoScore){
                    System.out.println("IT'S A DRAW!");
                }
                else if(playerOneScore>playerTwoScore){
                    System.out.println("PLAYER ONE WINS!");
                }
                else{
                    System.out.println("PLAYER TWO WINS!");
                }


                

            
            sc.close();
    }

        /*Method used for "combat" logic, this method checks the location of the recently played card and manages it so that it 
        so that the card does not try to search for another card out of the boards boundaries.
        */
    public static void fight(Card[][] board, int gridRow, int gridCol){
        if(gridRow > 0){            
            if(board[gridRow-1][gridCol] != null && board[gridRow-1][gridCol].getPossesion() != board[gridRow][gridCol].getPossesion()){
                if(board[gridRow-1][gridCol].getStats().getBottom() < board[gridRow][gridCol].getStats().getTop()){
                    board[gridRow-1][gridCol].setPossession(!board[gridRow-1][gridCol].getPossesion());
                }
            }
        }
        if(gridRow < board.length - 1){
            if(board[gridRow+1][gridCol] != null && board[gridRow+1][gridCol].getPossesion() != board[gridRow][gridCol].getPossesion()){
                if(board[gridRow+1][gridCol].getStats().getTop() < board[gridRow][gridCol].getStats().getBottom()){
                    board[gridRow+1][gridCol].setPossession(!board[gridRow+1][gridCol].getPossesion());
                }
            }

        }
        if(gridCol > 0){
            if(board[gridRow][gridCol-1] != null && board[gridRow][gridCol-1].getPossesion() != board[gridRow][gridCol].getPossesion()){
                if(board[gridRow][gridCol-1].getStats().getRight() < board[gridRow][gridCol].getStats().getLeft()){
                    board[gridRow][gridCol-1].setPossession(!board[gridRow][gridCol-1].getPossesion());
                }
            }

        }
        if(gridCol < board[gridRow].length-1){
             if(board[gridRow][gridCol+1] != null && board[gridRow][gridCol+1].getPossesion() != board[gridRow][gridCol].getPossesion()){
                if(board[gridRow][gridCol+1].getStats().getLeft() < board[gridRow][gridCol].getStats().getRight()){
                    board[gridRow][gridCol+1].setPossession(!board[gridRow][gridCol+1].getPossesion());
                }
            } 
        }


    }

        //Method used to print board with current cards place and a blank card filling unused spaces.
        public static void printBoard(Card[][] board){
        String[][] boardDisplay = new String[12][9];
        String[] blankCard = new String[12];
        Arrays.fill(blankCard, "|               |" );
        blankCard[0] = " --------------- ";
        blankCard[11] = " --------------- ";
        int row = 0;
        int col = 0;


        for(int i = 0; i < boardDisplay[11].length; i++){
        row = i/3;
        col = i%3;
            for(int j = 0; j < boardDisplay.length; j++){   
                if(board[row][col] == null){
                boardDisplay[j][i] = blankCard[j];
                }
                else{
                String[] card = Player.cardLineArray(board[row][col]);

                if(board[row][col].getPossesion()== true){ card[7] = "|      P1       |"; }
                else{ card[7] = "|      P2       |"; }
                boardDisplay[j][i] = card[j];
                }
            }
        }

        for(int i = 0; i < boardDisplay.length; i ++){
            for(int j = 0; j < 3; j++){
                System.out.print(boardDisplay[i][j] + "  ");
            }
            System.out.println();
        }
        for(int i = 0; i < boardDisplay.length; i ++){
            for(int j = 3; j < 6; j++){
                System.out.print(boardDisplay[i][j] + "  ");
            }
            System.out.println();
        }
        for(int i = 0; i < boardDisplay.length; i ++){
            for(int j = 6; j < 9; j++){
                System.out.print(boardDisplay[i][j] + "  ");
            }
            System.out.println();
        }
        
    }

}
