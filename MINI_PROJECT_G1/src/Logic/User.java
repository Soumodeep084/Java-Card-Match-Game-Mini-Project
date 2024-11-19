package Logic;

import java.util.ArrayList;

public class User {
    private final String name;
    private final int score;
    private final int level;
    
    private static ArrayList<User> userData;
    
    public User(String name , int score , int level){
        this.name = name;
        this.score = score;
        this.level = level;
    }
    
    public static void initializeUserData(){
        userData = new ArrayList<>();
    }
    
    public static void setData(User u){
        userData.add(u);
    }
    
    public static ArrayList<User> getUserDetails(){
        return userData;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public int getLevel(){
        return this.level;
    }
    
    @Override
    public String toString(){
        return "[ name = " + name + " score = " + score + " level = " + level + "]";
    }
}
