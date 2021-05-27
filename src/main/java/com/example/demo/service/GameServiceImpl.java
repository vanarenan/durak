package com.example.demo.service;

import com.example.demo.data.DataDeck;
import com.example.demo.model.Card;
import com.example.demo.model.Game;
import com.example.demo.model.Player;
import com.example.demo.model.Suit;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Service
public class GameServiceImpl implements  IGameService{

    private Game game;
    private List<Card> deck;

    @Autowired
    DataDeck data;


    @Override
    public Game init() {
        deck = data.getDeck();
        System.out.println(deck.size());
        return null;
    }
    public Card giveCard(){
        return deck.get(0);
    }
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }
    public void removeCard(Card card){
        deck.remove(card);
    }
    public int countDeck(){
        if (deck==null){
            return 0;
        }
        return deck.size();
    }
    public List<Card> giveMeCards(int amount){
        List<Card> refill = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Card card = giveCard();
            refill.add(card);
            removeCard(card);
        }
        return refill;
    }
}
