import java.util.Scanner;

import java.util.*;
//import java.util.ArrayList;
//import java.util.List;

class Main {
    public static void main(String[] args) {        
        Game g = new Game();
        int opponents = 1;
        int depth = 100000;
        float winPercentage = 0;

        List<Card> visibleCards = new ArrayList<Card>();

        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter card");

        String card1 = myObj.nextLine();
        visibleCards.add(g.convertString(card1));
        System.out.println("Enter card");

        String card2 = myObj.nextLine();
        visibleCards.add(g.convertString(card2));
        
        //EV = (%w*$w)-(%l*$l)

        winPercentage = g.generateWinPercentDeck(visibleCards, depth, opponents);
        System.out.println("win percentage preflop: ");
        System.out.println(winPercentage);

        System.out.println("Start flop \nEnter card");
        String card3 = myObj.nextLine();
        visibleCards.add(g.convertString(card3));
        System.out.println("Enter card");

        String card4 = myObj.nextLine();
        visibleCards.add(g.convertString(card4));
        System.out.println("Enter card");

        String card5 = myObj.nextLine();
        visibleCards.add(g.convertString(card5));
        
        System.out.println("win percentage postflop: \n visible hands"); 
        winPercentage = g.generateWinPercentDeck(visibleCards, depth, opponents);
        System.out.println(winPercentage);

        System.out.println("Turn \nEnter card");

        String turn = myObj.nextLine();
        visibleCards.add(g.convertString(turn));

        System.out.println("win percentage post turn: ");
        winPercentage = g.generateWinPercentDeck(visibleCards, depth, opponents);
        System.out.println(winPercentage);
        

        System.out.println("River \nEnter card");
        String river = myObj.nextLine();
        visibleCards.add(g.convertString(river));

        System.out.println("win percentage post river: ");
        winPercentage = g.generateWinPercentDeck(visibleCards, depth, opponents);
        System.out.println(winPercentage);
        myObj.close();
    }
}