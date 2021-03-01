package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class GameRankSample {

  static int TOTAL_SIZE = 20;

  public static void main(String[] args) {
    //Connection information. This information can be obtained from the console
    String host = "r-gs50a75e19681ff4.redis.singapore.rds.aliyuncs.com";

    int port = 6379;
    Jedis jedis = new Jedis(host, port);
    try {
      //Instance password
      String authString = jedis.auth("N1cetest"); //password
      if (!authString.equals("OK")) {
        System.err.println("AUTH Failed: " + authString);
        return;
      }
      //Key
      String key = "Game name: Keep Running, Alibaba Cloud!";
      //Clears any existing data
      jedis.del(key);
      //Generates several simulated players
      List<String> playerList = new ArrayList<String>();
      for (int i = 0; i < TOTAL_SIZE; ++i) {
        //Randomly generates an ID for each player
        playerList.add(UUID.randomUUID().toString());
      }
      System.out.println("Inputs all players ");
      //Records the score for each player
      for (int i = 0; i < playerList.size(); i++) {
        //Randomly generates numbers as the scores of each simulated player
        int score = (int) (Math.random() * 5000);
        String member = playerList.get(i);
        System.out.println("Player ID:" + member + ", Player Score: " + score);
        //Adds the player ID and score to SortedSet of the corresponding key
        jedis.zadd(key, score, member);
      }
      //Prints out the ranking list of all players
      System.out.println();
      System.out.println("       " + key);
      System.out.println(" Ranking list of all players");
      //Obtains the sorted list of players from SortedSet of the corresponding key
      Set<Tuple> scoreList = jedis.zrevrangeWithScores(key, 0, -1);
      for (Tuple item : scoreList) {
        System.out.println(
          "Player ID:" +
          item.getElement() +
          ", Player Score:" +
          Double.valueOf(item.getScore()).intValue()
        );
      }
      //Prints out the top five players
      System.out.println();
      System.out.println("       " + key);
      System.out.println("       Top players");
      scoreList = jedis.zrevrangeWithScores(key, 0, 4);
      for (Tuple item : scoreList) {
        System.out.println(
          "Player ID:" +
          item.getElement() +
          ", Player Score:" +
          Double.valueOf(item.getScore()).intValue()
        );
      }
      //Prints out a list of specific players
      System.out.println();
      System.out.println("         " + key);
      System.out.println(" Players with scores from 1,000 to 2,000");
      //Obtains a list of players with scores from 1,000 to 2,000 from SortedSet of the corresponding key
      scoreList = jedis.zrangeByScoreWithScores(key, 1000, 2000);
      for (Tuple item : scoreList) {
        System.out.println(
          "Player ID:" +
          item.getElement() +
          ", Player Score:" +
          Double.valueOf(item.getScore()).intValue()
        );
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jedis.quit();
      jedis.close();
    }
  }
}
