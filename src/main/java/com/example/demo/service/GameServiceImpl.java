package com.example.demo.service;

import com.example.demo.data.DataDeck;
import com.example.demo.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
@NoArgsConstructor
@Service
public class GameServiceImpl implements  IGameService{

    private Game game;
    private Deque<Card> deck;
    List<Card> refill = new ArrayList<>();
    List<Card> refillComp = new ArrayList<>();
    List<Card> myMove = new ArrayList<>();
    List<Card> compMove = new ArrayList<>();



    @Autowired
    DataDeck data;


    @Override
    public void init() {
        deck = new LinkedList<>(data.getDeck());
        refill.clear();
        refillComp.clear();
        myMove.clear();
        compMove.clear();
        refillComp.clear();

    }
    public Card giveCard(){
        return deck.poll();
    }
    public void shuffleDeck(){
        List<Card> list = new ArrayList<>(deck);
        Collections.shuffle(list);
        deck = new LinkedList<>(list);
    }
    public void removeCard(Card card){
        deck.removeFirstOccurrence(card);
    }
    public int countDeck(){
        if (deck==null){
            return 0;
        }
        return deck.size();
    }
    public List<Card> giveMeCards(){
        int cardsAmount = 6 - refill.size();
        for (int i = 0; i < cardsAmount; i++) {
                Card card = this.giveCard();
                refill.add(card);
            }
            return refill;

    }
    public List<Card> giveCompCards(){
        int cardsAmount = 6 - refillComp.size();
        for (int i = 0; i < cardsAmount; i++) {
            Card card = this.giveCard();
            refillComp.add(card);
        }
        return refillComp;

    }
    public List<Card> addCard(){
        if (refill.size()<6) {
            Card card = this.giveCard();
            refill.add(card);
        }
        return refill;
    }

    public Card getByImg(String img) {
        Card myPick = refill.stream().filter(card -> card.getImg().equals(img))
                .findFirst().orElse(null);
        refill.remove(myPick);
        myMove.add(myPick);
        return myPick;
    }
    public Card getCard(Suit suit, Nominal nominal) {
     /*   System.out.println(suit + ":" + nominal);*/
        Card myPick = refill.stream()
                .filter(card -> card.getNominal().equals(nominal))
                .filter(card -> card.getSuit().equals(suit))
                .findAny()
                .orElse(Card.builder().img("/img/fulldeck/back.png").build());
        refill.remove(myPick);
        myMove.add(myPick);
        System.out.println(myMove);
        return myPick;
    }
}
