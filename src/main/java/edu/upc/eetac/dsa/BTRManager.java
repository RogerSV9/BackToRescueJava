package edu.upc.eetac.dsa;

import java.util.List;

public interface BTRManager {


    /**Authentication*/

    void UserRegistration(String username, String password);

    Character UserLogin(String username, String password) throws UserNotFoundException;

    void UserDelete(String username, String password) throws UserNotFoundException;

    void LogOut(Character character);

    void AddCharacter(String username, int health, int mana, int damage, int defense, int level, double money);

    /**States*/

    Character GetStats(String username) throws UserNotFoundException;

    void UpdateStats(Character character);

    /**Scoreboard*/

    List<Character> GetScoreboard();

    /**Objects*/

    List<Objeto> GetObjects(String username) throws UserNotFoundException;

    void AddObject(String usernme, String name, String image);

    /**Map*/

    String GetLevelData(int level);

}
