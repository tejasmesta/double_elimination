package com.tour.entity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Scheduler {
	  public static void main(String[] args) {
	    Scanner input = new Scanner(System.in);
	    LinkedList<Player> players = new LinkedList<Player>();
	    
	    System.out.println("How many players will participate?");
	    int entrants = valInt(input, 1, Integer.MAX_VALUE);
	    
	    System.out.println("\nFor each player, type their name and seed separated by a space, and press enter.");
	    String name;
	    int seed;
	    for (int i = 0; i < entrants; i++) {
	      name = input.next();
	      seed = valInt(input, 1, Integer.MAX_VALUE);
	      players.add(new Player(name, seed));
	    }
	    
	    Collections.sort(players);				
	    
	    doubleElim(players);	    
	  }
	  
	  public static int valInt(Scanner input, int min, int max) {
	    boolean check = true; 
	    int num = -1;         
	    do {
	      try {
	        num = input.nextInt();
	        if (num < min || num > max)   
	          throw new NumberFormatException("Input is out of range.");
	        else              
	          check = false;  
	      }
	      catch (Exception e) {
	        System.out.println("Please choose a valid option.");
	        System.out.print("");
	        input.nextLine();
	      }
	    } while (check);
	    
	    return num;
	  }
	  
	  public static void doubleElim(LinkedList<Player> winners) {
		  	LinkedList<Player> toPlace = new LinkedList<Player>();      
		  	LinkedList<Player> results = new LinkedList<Player>();			
		  	LinkedList<Player> losers = new LinkedList<Player>();		
		  	int entrants = winners.size();			
		  	
		  	Scanner input = new Scanner(System.in);						
		  	
		  	if ((entrants & (entrants - 1)) != 0) {		
		  		int i = 2;											      
		  		while(true) {
		  			if (Math.pow(2, i) > entrants)				
		  				break;
		  			else											
		  				i++;
		  		}
		  		int add = (int)Math.pow(2, i) - entrants;			
		  		
		  		for (int j = 0; j < add; j++) {
		  			winners.add(new Player("bye", (int)Math.pow(2, i) ));	
		  		}
		  	}
		  	
		  	LinkedList<Match> round = new LinkedList<Match>();  
		  	
		  	boolean winRnd = false;           
		  	
		  	int w = 0;      
		  	
		  	while(winners.size() + losers.size() > 2) {       
		  	  int winMtc = 0;    
		  	  if (winRnd || w < 1 ) {                         
		  	    while(!winners.isEmpty()) {                   
		  	      round.addLast(new Match(winners.pollFirst(), winners.pollLast()));  
		  	      winMtc++;
		  	    }
		  	  }
		  	  
		  	  while(!losers.isEmpty()) {                
		  	    round.addLast(new Match(losers.pollFirst(), losers.pollLast()));  
		  	  }
		  	  System.out.println(round);
		  	  
		  	  for (int i = 0; i < winMtc; i++) {  
		  	    Match m = round.pollFirst();      
		  	    winners.addLast(m.setResults(input.nextInt()));   
		  	    losers.addLast(m.getLoser());    
		  	  }
		  	  while(!round.isEmpty()) {         
		  	    Match m = round.pollFirst();    
		  	    losers.addLast(m.setResults(input.nextInt()));   
		  	    toPlace.addLast(m.getLoser());   
		  	  }
		  	  while(!toPlace.isEmpty()) {
		  	    Player p = toPlace.pollFirst();
		  	    p.setPlace(winners.size() + losers.size() + 1);   
		  	    results.addFirst(p);    
		  	  }
		  	  
		  	  winRnd = !winRnd;       
		  	  w++;
		  	}
		  	
		  	//Grand Finals
		  	boolean reset = false;      
		  	int r = 0;                  
		  	do {                       
		  	  round.addLast(new Match(winners.peekFirst(), losers.peekFirst()));
		  	  System.out.println(round);
		  	  Match m = round.pollFirst();
		  	  int win = input.nextInt();   
		  	  m.setResults(win);            
		  	  if (win > 0) {              
		  	    m.getLoser().setPlace(2);
		  	    results.addFirst(m.getLoser()); 
		  	    m.getWinner().setPlace(1);
		  	    results.addFirst(m.getWinner());
		  	    reset = false;        
		  	  }
		  	  else {          
		  	    reset = true;
		  	    r++;    
		  	    if (r > 1) {  
		  	      m.getLoser().setPlace(2);
		  	      results.addFirst(m.getLoser()); 
		  	      m.getWinner().setPlace(1);
		  	      results.addFirst(m.getWinner());
		  	    }
		  	  }
		  	} while (reset && r < 2); 
		  	
		  	displayElimResults(results);		
		  }
	  
	  public static void displayElimResults(LinkedList<Player> results) {
		  	for (Player player : results)
		  		System.out.println(player.getPlace() + ". " + player.getTag());
		  }
	  
	  
}	  