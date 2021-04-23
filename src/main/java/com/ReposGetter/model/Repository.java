package com.ReposGetter.model;

public class Repository {

	private String name;
	private int stars;

	public Repository(String name, int stars) {
		this.name = name;
		this.stars = stars;
	}

	public String getName() {
		return name;
	}

	public int getStars() {
		return stars;
	}
}