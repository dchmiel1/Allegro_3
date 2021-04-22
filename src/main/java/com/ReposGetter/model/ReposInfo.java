package com.ReposGetter.model;

import java.util.ArrayList;

public class ReposInfo {

	private int stars;
	private ArrayList<String> reposNames;

	public ReposInfo(int stars, ArrayList<String> reposNames) {
		this.stars = stars;
		this.reposNames = reposNames;
	}
	
	public int getStars() {
		return stars;
	}
	
	public ArrayList<String> getReposNames(){
		return reposNames;
	}

}
