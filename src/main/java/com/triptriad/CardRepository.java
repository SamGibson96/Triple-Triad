package com.triptriad;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class CardRepository {

    /*This class is responsible for gatering the card data from the .json file and inputting it into an ArrayList that the applications 
    can access. It uses the object mapper from the Jackson Library to appropriately manage the data in the json file and convert it to 
    Card objects.
     */

    private static ArrayList<Card> cards = new ArrayList<>();

    public static void loadCards(String resourceName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = CardRepository.class.getClassLoader().getResourceAsStream(resourceName)) {

            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found");
            }

            Card[] cardArray = mapper.readValue(inputStream, Card[].class);
            cards = new ArrayList<>(Arrays.asList(cardArray));

    
        }
    }

    
    public static ArrayList<Card> getCards() {
        return cards;
    }
}
