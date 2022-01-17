package com.tour.entity;

public class Match {
	private Player p1;
	private Player p2;	
	private Player winner;	
	private Player loser;
	
	public Match(Player p1, Player p2) {	
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Player setResults(int p) {	
		if (p1.getTag().equals("bye"))		
			p = 0;					
		else if (p2.getTag().equals("bye"))
			p = 1;				
		
		if (p > 0) {		
			winner = p1;
			p1.addWin();
			loser = p2;
			p2.addLoss();
		} else {		
			winner = p2;
			p2.addWin();
			loser = p1;
			p1.addLoss();
		}
		return winner;		
	}
	
	public Player getWinner() {	
		return winner;
	}
	
	public Player getLoser() {	
		return loser;
	}
	
	public String toString() {
		return p1 + "(" + p1.getSeed() + ")" + " vs. " + p2 + "(" + p2.getSeed() + ")";	//ex. Fox McCloud(1) vs. Kirby(26)
	}
}