package data.database.models;


public class Player {
 
    private static String Name;
    private static int Score ;
    



    public static void setName(String name) {
        Name = name;
    }

    public static void setScore(int score) {
        Score = score;
    }

    public static String getName() {
        return Name;
    }

    public static int getScore() {
        return Score;
    }

  
}
