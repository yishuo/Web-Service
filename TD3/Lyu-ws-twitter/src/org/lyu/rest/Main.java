package org.lyu.rest;

import java.net.URLEncoder;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import twitter4j.IDs;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Twitter services and twitter4j
 * @author Yishuo LYU
 */
public class Main {
	
	static OAuthService service = new ServiceBuilder().provider(TwitterApi.SSL.class)
			.apiKey("pdGoV9K87AOgHn2Kr5Ns3B6O2")
			.apiSecret("UAENio0Kf3JnMIREXX3GtdWrZ2Jhcru5HhZvVtT9abMlEjwh1Z").build();
    Token requestToken = service.getRequestToken();
	static Token accessToken= new Token("2861401239-FCUemBZQW2JHg6Pl4fijDHByOuosCDpGiLjmrYC", "8vOrYrHgY0ziafOgJwvLF5bTK1iAgmhqhbYbmctrjZi16");
	
	//This is the question 1. 
	public static void parseJSON(){
		OAuthRequest request =
				new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json");
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println(response.getBody());
		JSONParser parser1 = new JSONParser();
		Object objetNormal1;
		try {
			objetNormal1 = parser1.parse(response.getBody());
			JSONObject jsonObject1 = (JSONObject)objetNormal1;
			System.out.println("id_str = " + jsonObject1.get("id_str"));
			System.out.println("name = " + jsonObject1.get("name"));
			System.out.println("screen_name = " + jsonObject1.get("screen_name"));
			System.out.println("followers_count = " + jsonObject1.get("followers_count"));
			System.out.println("friends_count = " + jsonObject1.get("friends_count"));
			System.out.println("created_at = " + jsonObject1.get("created_at"));
			System.out.println("time_zone = " + jsonObject1.get("time_ zone"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	//This is the question 2.
	public static void retrievingMyFollowers(){
		OAuthRequest requestFriends =
				new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/followers/list.json");
		service.signRequest(accessToken, requestFriends);
		Response responseFriends = requestFriends.send();
		System.out.println(responseFriends.getBody());
	
		JSONParser parser2 = new JSONParser();
		try {
			Object objetNormal2 = parser2.parse(responseFriends.getBody());
			JSONObject jsonObject2 = (JSONObject)objetNormal2;
//			System.out.println(objetNormal);
			JSONArray array2 = (JSONArray) jsonObject2.get("users");
			for (int i = 0; i < array2.size(); i++) {
				JSONObject arrayObject2 = (JSONObject)array2.get(i);
				System.out.println("id_str = " + arrayObject2.get("id_str"));
				System.out.println("name = " + arrayObject2.get("name"));
				System.out.println("screen_name = " + arrayObject2.get("screen_name"));
				System.out.println("followers_count = " + arrayObject2.get("followers_count"));
				System.out.println("friends_count = " + arrayObject2.get("friends_count"));
				System.out.println("created_at = " + arrayObject2.get("created_at"));
				System.out.println("time_zone = " + arrayObject2.get("time_ zone"));
				System.out.println("----------------------------------------------");
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//This is the question 3.
	public static void analyzingMyTwitterData(){
		OAuthRequest requestFriendsList =
				new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/friends/list.json");
		service.signRequest(accessToken, requestFriendsList);
		Response responseFriendsList = requestFriendsList.send();
		System.out.println(responseFriendsList.getBody());
	
		JSONParser parser3 = new JSONParser();
		try {
			Object objetNormal3 = parser3.parse(responseFriendsList.getBody());
			JSONObject jsonObject3 = (JSONObject)objetNormal3;
//			System.out.println(objetNormal);
			JSONArray array3 = (JSONArray) jsonObject3.get("users");
			int index = 0;
			int sumFollowers = 0;
			for (int i = 0; i < array3.size(); i++) {
				JSONObject arrayObject3 = (JSONObject)array3.get(i);
				sumFollowers += Integer.parseInt(arrayObject3.get("followers_count").toString());
				index += 1;
			}
			int avgFollowers = sumFollowers/index;
			System.out.println("The sum of followers of my friends: " + sumFollowers);
			System.out.println("The number of my friends: " + index);
			System.out.println("The average number of followers of my friends: " + avgFollowers);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//This is the question 4.
	public static void UpdateMyStatus(){
		String date = new Date().toString();
		String status = "This is my RESTFUL status Generated at " + date;
		String urlstatus = URLEncoder.encode(status);
		String url = "https://api.twitter.com/1.1/statuses/update.json?status="+urlstatus;		
		OAuthRequest requestUpdate = new OAuthRequest(Verb.POST, url);
		
		service.signRequest(accessToken, requestUpdate);
		Response responseUpdate = requestUpdate.send();
		System.out.println("Successfully posted");
	}
	
	//Go Further: The package Twitter4j

	//twitter4j OAuth.
    private static ConfigurationBuilder cb; 
    private static Twitter twitter;
    public static void MyConfigurationBuilder() {
      cb = new ConfigurationBuilder();
      cb.setDebugEnabled(true)
        .setOAuthConsumerKey("pdGoV9K87AOgHn2Kr5Ns3B6O2")
        .setOAuthConsumerSecret("UAENio0Kf3JnMIREXX3GtdWrZ2Jhcru5HhZvVtT9abMlEjwh1Z")
        .setOAuthAccessToken("2861401239-FCUemBZQW2JHg6Pl4fijDHByOuosCDpGiLjmrYC")
        .setOAuthAccessTokenSecret("8vOrYrHgY0ziafOgJwvLF5bTK1iAgmhqhbYbmctrjZi16");
      TwitterFactory tf = new TwitterFactory(cb.build());
      twitter = tf.getInstance();
    }


	//This is the question 1 by twitter4j.
	public static void parseJSON4j(){
		MyConfigurationBuilder();
		try {
            User user = twitter.verifyCredentials();
            System.out.println("Successfully verified credentials");
			System.out.println("id_str = " + user.getId());
			System.out.println("name = " + user.getName());
			System.out.println("screen_name = " + user.getScreenName());
			System.out.println("followers_count = " + user.getFollowersCount());
			System.out.println("friends_count = " + user.getFriendsCount());
			System.out.println("created_at = " + user.getCreatedAt());
			System.out.println("time_zone = " + user.getTimeZone());
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to verify credentials: " + te.getMessage());
            System.exit(-1);
        }
		
	}
	
	//This is the question 2 by twitter4j. 
	public static void retrievingMyFollowers4j(){
		MyConfigurationBuilder();		
		try {
          long cursor = -1;
          IDs ids;
          System.out.println("Listing followers's ids.");
		  System.out.println("----------------------------------------------");
          do {
              ids = twitter.getFollowersIDs(cursor);
              for (long id : ids.getIDs()) {
            	  User user = twitter.showUser(id);
      			  System.out.println("id_str = " + user.getId());
    			  System.out.println("name = " + user.getName());
    		      System.out.println("screen_name = " + user.getScreenName());
    			  System.out.println("followers_count = " + user.getFollowersCount());
    			  System.out.println("friends_count = " + user.getFriendsCount());
    			  System.out.println("created_at = " + user.getCreatedAt());
    			  System.out.println("time_zone = " + user.getTimeZone());
  				  System.out.println("----------------------------------------------");
              }
          } while ((cursor = ids.getNextCursor()) != 0);
          System.exit(0);
      } catch (TwitterException te) {
          te.printStackTrace();
          System.out.println("Failed to get followers' ids: " + te.getMessage());
          System.exit(-1);
      }
		
	}
	
	//This is the question 3 by twitter4j. 
	public static void analyzingMyTwitterData4j(){
		MyConfigurationBuilder();
		try {
            long cursor = -1;
            IDs ids;
            int index4j = 0;
            int sumFollowers4j = 0;
            System.out.println("Listing following ids.");
            do {
                ids = twitter.getFriendsIDs(cursor);
                for (long id : ids.getIDs()) {
                	User user = twitter.showUser(id);
                	sumFollowers4j += user.getFollowersCount();
                    index4j += 1;
                }

            }while ((cursor = ids.getNextCursor()) != 0);
            int avgFollowers4j = sumFollowers4j/index4j;
			System.out.println("The sum of followers of my friends (twitter4j) : " + sumFollowers4j);
			System.out.println("The number of my friends (twitter4j) : " + index4j);
			System.out.println("The average number of followers of my friends (twitter4j) : " + avgFollowers4j);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get friends' ids: " + te.getMessage());
            System.exit(-1);
        }		
	}
	
	//This is the question 4 by twitter4j. 
	public static void UpdateMyStatus4j(){
		String date = new Date().toString();
		String status = "This is my RESTFUL status Generated by twitter4j at " + date;
		MyConfigurationBuilder();
	    Status myStatus = null;
		try {
			myStatus = twitter.updateStatus(status);
			System.out.println("Successfully updated the status to [" + myStatus.getText() + "].");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//This is the question 1.
		parseJSON();
		//This is the question 2.
		retrievingMyFollowers();
		//This is the question 3.
		analyzingMyTwitterData();
		//This is the question 4.
		UpdateMyStatus();
		
		//This is the question 1 by twitter4j.
		parseJSON4j();
		//This is the question 2 by twitter4j.
		retrievingMyFollowers4j();
		//This is the question 3 by twitter4j.
		analyzingMyTwitterData4j();
		//This is the question 4 by twitter4j.
		UpdateMyStatus4j();

	}
}
