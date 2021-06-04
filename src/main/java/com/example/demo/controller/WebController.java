package com.example.demo.controller;

import com.example.demo.model.Card;
import com.example.demo.model.Nominal;
import com.example.demo.model.Suit;
import com.example.demo.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// info
@Controller
@RequestMapping("/ui")
public class WebController {
    @Autowired
    GameServiceImpl service;

    @RequestMapping("/desk")
    String getDesk(Model model){
        model.addAttribute("count", service.countDeck());
    return "newgame";
}
    @RequestMapping("/init")
    public String initGame(Model model){
        service.init();
        service.shuffleDeck();
        model.addAttribute("count", service.countDeck());
        return "newgame";
    }
    @RequestMapping("/shuffle")
    public String shuffleDeck(Model model){
        service.shuffleDeck();
        model.addAttribute("count", service.countDeck());
        Card card = new Card();
        card.setImg("/img/fulldeck/back.png");
        model.addAttribute("card", card);
        return "gametable";
    }
    @RequestMapping("/refill/{amount}")
    public String refill(Model model, @PathVariable("amount") int amount){
        model.addAttribute("list", service.giveMeCards());
        model.addAttribute("count", service.countDeck());
        return "gametable";
    }
    @RequestMapping("/refill")
    public String refill(Model model){
        model.addAttribute("list", service.giveMeCards());
        model.addAttribute("listComp", service.giveCompCards());
        model.addAttribute("myMove", service.getMyMove());
        model.addAttribute("count", service.countDeck());
        return "desk";
    }
    @RequestMapping("/pick/{suit}/{nominal}")
    public String pick(Model model,
                       @PathVariable("suit") Suit suit,
                       @PathVariable("nominal") Nominal nominal){
        Card card = service.getCard(suit, nominal);
        model.addAttribute("list", service.getRefill());
        model.addAttribute("listComp", service.getRefillComp());
        model.addAttribute("count", service.countDeck());
        model.addAttribute("myMove", service.getMyMove());
        model.addAttribute("compMove", service.getCompMove());
        return "gametable";
    }


}
