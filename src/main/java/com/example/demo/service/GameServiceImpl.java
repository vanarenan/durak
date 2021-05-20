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
        this.getGame().setDeck(getShuffleDeck(data.getDeck()));
        Card firstCard = this.getGame().getDeck().get(0);
        Suit suit = firstCard.getSuit();
        this.getGame().setTrump(suit);
        Player player = new Player("Player", null);
        Player computer = new Player("Computer", null);
        player.setCardsOnHand(giveCards(this.getGame().getDeck(), 6));
        this.getGame().setFirstPlayer(player);
        System.out.println("Dergai");
        return game;
    }
    public List<Card> giveCards(List<Card> cards, int num){
        List<Card> cardsToGive = this.getGame().getDeck().subList(0, num);
        for (int i = 0; i < num; i++) {
            this.getGame().getDeck().remove(i);
        }
        return cardsToGive;
    }
    public List<Card> getShuffleDeck(List<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }
}
