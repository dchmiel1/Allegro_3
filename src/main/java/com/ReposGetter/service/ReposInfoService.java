package com.ReposGetter.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.json.JSONArray;

import com.ReposGetter.model.ReposInfo;
import com.ReposGetter.model.Repository;

public class ReposInfoService {

	private final static String api_addr_1 = "https://api.github.com/users/";
	private final static String api_addr_2 = "/repos?per_page=100&page=";

	private static String readData(Reader reader) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		int read;
		while ((read = reader.read()) != -1) {
			stringBuilder.append((char) read);
		}
		return stringBuilder.toString();
	}

	public static JSONArray getJSONArray(URL url) {
		InputStream inputStream;
		BufferedReader bufferedReader;
		String jsonText;
		try {
			inputStream = url.openStream();
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			jsonText = readData(bufferedReader);
			inputStream.close();
			return new JSONArray(jsonText);
		} catch (IOException e) {
			System.out.println("User doesn't exist or API rate limit exceeded");
			return null;
		}
	}

	public static ReposInfo getReposInfo(String username) {
		int page = 1;
		ArrayList<Repository> repositories = new ArrayList<Repository>();
		JSONArray jsonArray = null;
		URL url;
		int stars = 0;
		do {
			try {
				url = new URL(api_addr_1 + username + api_addr_2 + page);
				jsonArray = getJSONArray(url);
				if (jsonArray == null) {
					throw new NullPointerException();
				}
				for (int i = 0; i < jsonArray.length(); i++) {
					stars += jsonArray.getJSONObject(i).getInt("stargazers_count");
					repositories.add(new Repository(jsonArray.getJSONObject(i).getString("name"), jsonArray.getJSONObject(i).getInt("stargazers_count")));
				}
				++page;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			} catch (NullPointerException e) {
				return null;
			}
		} while (jsonArray.length() != 0 || page == 200);
		return new ReposInfo(stars, repositories);
	}
}
