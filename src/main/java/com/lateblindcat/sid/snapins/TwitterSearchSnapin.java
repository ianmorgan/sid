package com.lateblindcat.sid.snapins;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import com.lateblindcat.sid.framework.Route;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;

public class TwitterSearchSnapin implements Snapin {

	@Override
	public String getName() {
		return "Twitter Search";
	}

	@Override
	public Route getRoute() {
		return new Route("GET:/twittersearch");
	}

	@Override
	public PageResponse process() {

		// The factory instance is re-useable and thread safe.
		Twitter twitter = new TwitterFactory().getInstance();
		Query query = new Query("dartford");
		query.setPage(1);
		
		QueryResult result = null;
		StringBuilder sb = new StringBuilder();

		try {
			result = twitter.search(query);

			sb.append("<pre>results");
			sb.append(("page " + result.getPage()));
			for (Tweet tweet : result.getTweets()) {
				sb.append("\n");
				sb.append(tweet.getCreatedAt() + " - " + tweet.getFromUser() + ":" + tweet.getText() + ":" + tweet.getLocation());
			}
			sb.append("</pre>");

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			sb.append(e.getMessage());
		}

		return PageResponseFactory.text(sb.toString());
	}

}
