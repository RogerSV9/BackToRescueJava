package edu.upc.eetac.dsa;

import java.util.List;

public interface BTRManager {


    /**Authentication*/

    Boolean UserRegistration(User user);

    Player UserLogin(User user) throws UserNotFoundException;

    void UserDelete(String username, String password) throws UserNotFoundException;

    void LogOut(Player player);


    /**States*/

    Player GetStats(String username) throws UserNotFoundException;

    void UpdateStats(Player player) throws UserNotFoundException;

    /**Scoreboard*/

    List<Player> GetScoreboard();

    /**Objects*/

    List<Objeto> GetObjects(String username) throws UserNotFoundException;

    void AddObject(String usernme, String name, String image);

    /**Map*/

    String GetLevelData(int level);

}
