package com.ReposGetter.model;

import java.util.ArrayList;

public class ReposInfo {

	private int allStars;
	private ArrayList<Repository> repositories;

	public ReposInfo(int allStars, ArrayList<Repository> repositories) {
		this.allStars = allStars;
		this.repositories = repositories;
	}

	public int getAllStars() {
		return allStars;
	}

	public ArrayList<Repository> getRepositories() {
		return repositories;
	}

}
