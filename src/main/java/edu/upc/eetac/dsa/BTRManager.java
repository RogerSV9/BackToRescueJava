package edu.upc.eetac.dsa;

import java.util.List;

public interface BTRManager {


    /**Authentication*/

    void UserRegistration(String username, String password);

    Character UserLogin(String username, String password) throws UserNotFoundException;

    void UserDelete(String username, String password) throws UserNotFoundException;

    void LogOut(String username, Character character);

    /**States*/

    Character GetStats(String username);

    void UpdateStats(String username, Character character);

    /**Objects*/

    List<Objeto> GetObjects(String username) throws UserNotFoundException;

    void AddObject(String usernme, String name, String image);

}
