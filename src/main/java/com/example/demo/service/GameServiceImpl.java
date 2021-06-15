package com.example.demo.service;

import com.example.demo.data.DataDeck;
import com.example.demo.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    Card empty = Card.builder().img("/img/fulldeck/back.png").build();
    List<Nominal> nominals = new ArrayList<>();
    Boolean myTurn = true;
    Boolean myGame = true;


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
        if (myMove.size() != 0 || compMove.size() != 0){
            return refill.stream().sorted(Comparator.comparing(Card::getValue))
                    .collect(Collectors.toList());
        }
        int cardsAmount = 6 - refill.size();
        for (int i = 0; i < cardsAmount; i++) {
                Card card = this.giveCard();
                refill.add(card);
            }
            return refill.stream().sorted(Comparator.comparing(Card::getValue))
                    .collect(Collectors.toList());

    }
    public List<Card> giveCompCards(){
        if (myMove.size() != 0 || compMove.size() != 0){
            return refillComp.stream().sorted(Comparator.comparing(Card::getValue))
                    .collect(Collectors.toList());
        }
        int cardsAmount = 6 - refillComp.size();
        for (int i = 0; i < cardsAmount; i++) {
            Card card = this.giveCard();
            refillComp.add(card);
        }
        return refillComp.stream().sorted(Comparator.comparing(Card::getValue))
                .collect(Collectors.toList());

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
            Card myPick = refill.stream()
                    .filter(card -> card.getNominal().equals(nominal))
                    .filter(card -> card.getSuit().equals(suit))
                    .findAny()
                    .orElse(empty);
        if (nominals.isEmpty() || nominals.contains(myPick.getNominal())) {
            refill.remove(myPick);
            myMove.add(myPick);
            nominals.add(myPick.getNominal());
        }

        if (nominals.isEmpty() || nominals.contains(myPick.getNominal())){
        }
        this.getCompMoveMethod();
        if (compMove.contains(empty)) {
        this.throwTrash();
        }
        return myPick;
    }

    public void getCompMoveMethod() {
        if (myMove.size() == compMove.size()){
            return;
        }
        Card move = myMove.get(myMove.size()-1);
        Card moveBack = fightBack(move, refillComp);
        System.out.println(moveBack);
        refillComp.remove(moveBack);
        compMove.add(moveBack);
        nominals.add(moveBack.getNominal());
        System.out.println(compMove);

    }

    private Card fightBack(Card myMove, List<Card> compCards) {
       Suit suit = myMove.getSuit();
       List<Card> suits = refillComp.stream()
               .filter(el -> el.getSuit().equals(suit))
               .sorted(Comparator.comparing(Card::getValue))
               .collect(Collectors.toList());
       Card compMove = suits.stream().filter(el -> el.getValue() > myMove.getValue())
               .findFirst().orElse(empty);
       return compMove;
    }

    public void throwTrash() {
        nominals.clear();
        if (compMove.contains(empty)){
            for (Card card:compMove) {
                if (!card.equals(empty))
                refillComp.add(card);
            }
            for (Card card:myMove)
                refillComp.add(card);
        }

        compMove.clear();
        myMove.clear();
        giveMeCards();
    }
    public void compPick(){
        Card card = refillComp.stream().sorted(Comparator.comparing(Card::getValue))
                .findFirst().get();
        if (myMove.size() != compMove.size()){
            return;
        }
        myTurn = false;
        refillComp.remove(card);
        compMove.add(card);
    }
    public void flipTurn() {
        myTurn = !myTurn;
    }
}
