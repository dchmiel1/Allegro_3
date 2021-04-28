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

/**
 * Class provides downloading and processing repositories' data
 * @author Daniel Chmielewiec
 *
 */
public class ReposInfoService {

	private final static String api_addr_1 = "https://api.github.com/users/";
	private final static String api_addr_2 = "/repos?per_page=100&page=";
	private final static String stars_in_json = "stargazers_count";
	private final static String name_in_json = "name";
	

	/**
	 * Method builds String from given reader's data
	 * @param reader buffered reader from which we would like to get data
	 * @return read data as String
	 * @throws IOException when reading fails
	 */
	private static String readData(Reader reader) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		int read;
		while ((read = reader.read()) != -1) {
			stringBuilder.append((char) read);
		}
		return stringBuilder.toString();
	}

	/**
	 * Method gets from URL json array and returning it
	 * @param url URL to json array
	 * @return read json array
	 */
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
			return null;
		}
	}

	/**
	 * Method gets username and provides his repositories information
	 * @param username which repositories we want to list
	 * @return user's repositories list
	 */
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
					stars += jsonArray.getJSONObject(i).getInt(stars_in_json);
					repositories.add(new Repository(jsonArray.getJSONObject(i).getString(name_in_json), jsonArray.getJSONObject(i).getInt(stars_in_json)));
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
