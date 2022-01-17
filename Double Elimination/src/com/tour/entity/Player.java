package com.tour.entity;

import java.util.ArrayList;

public class Player implements Comparable<Player> {
	private String tag;		
	private int win = 0;	
	private int loss = 0;	
	private int seed;		
	private int place = 1;	
	public ArrayList<Player> history = new ArrayList<Player>();	
	
	public Player(String tag) {		
		this.tag = tag;
	}
	
	public Player(String tag, int seed) {	
		this.tag = tag;
		this.seed = seed;
	}
	
	public String getTag() {		
		return tag;
	}
	
	public int getSeed() {		
		return seed;
	}
	
	public void setSeed(int seed) {		
		this.seed = seed;
	}
	
	public int getWin() {			
		return win;
	}
	
	public void addWin() {			
		win++;
	}
	
	public int getLoss() {		
		return loss;
	}
	
	public void addLoss() {			
		loss++;
	}
	
	public int getPlace() {			
		return place;
	}
	
	public void setPlace(int place) {	
		this.place = place;
	}
	
	@Override
	public int compareTo(Player p) {	
		if (this.seed > p.seed)
			return 1;
		else if (this.seed == p.seed)
			return 0;
		else
			return -1;
	}
	
	public String toString() {		
		return tag;
	}
}