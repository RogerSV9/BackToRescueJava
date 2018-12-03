package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;

import java.util.*;


public class BTRManagerImpl implements BTRManager {
    final static Logger log = Logger.getLogger(BTRManagerImpl.class.getName());
    private static BTRManager instance;

    private HashMap<String,User> users;
    private List<Objeto> objects;
    private List<Character> characters;
    private String[] levels;

    public static BTRManager getInstance(){
        if(instance == null){
            instance = new BTRManagerImpl();
        }
        return instance;
    }

    private BTRManagerImpl(){
        users = new HashMap<>();
        objects = new ArrayList<>();
        characters = new ArrayList<>();
        levels = new String[10];
    }

    @Override
    public void UserRegistration(String username, String password) {
        User user = users.get(username);
        if(user == null){
            User newuser = new User(username, password);
            users.put(username,newuser);
        }
        else{
            log.error("User is already in the system");
        }
    }

    @Override
    public Character UserLogin(String username, String password) throws UserNotFoundException {
        User user = users.get(username);
        Character c = null;
        if(user != null) {
            if (user.getPassword().equals(password)) {
                for(int i = 0; i< this.characters.size(); i++) {
                    if(username.equals(this.characters.get(i).getUsername())){
                        c = this.characters.get(i);
                    }
                }
            } else {
                log.error("Incorrect password");
            }
        }
        else{
            log.error("User not found");
            throw new UserNotFoundException();
        }

        return c;
    }

    @Override
    public void UserDelete(String username, String password) throws UserNotFoundException{
        User user = users.get(username);
        Character c = null;
        if(user != null) {
            if (user.getPassword().equals(password)) {
                users.remove(username);
            } else {
                log.error("Incorrect password");
            }
        }
        else{
            log.error("User not found");
            throw new UserNotFoundException();
        }
    }

    @Override
    public void LogOut(String username, Character character) {
        UpdateStats(username, character);
    }

    @Override
    public Character GetStats(String username) {
        return null;
    }

    @Override
    public void UpdateStats(String username, Character character) {

    }

    @Override
    public List<Objeto> GetObjects(String username) throws UserNotFoundException{
        List<Objeto> objs = new ArrayList<>();
        User user = this.users.get(username);
        Objeto o = null;
        if(user != null){
            for(int i = 0; i< this.objects.size(); i++) {
                if(username.equals(this.objects.get(i).getUsername())){
                    o = this.objects.get(i);
                    objs.add(o);
                }
            }
        }
        else{
            throw new UserNotFoundException();
        }
        return objs;
    }

    @Override
    public void AddObject(String username, String name, String image) {
        objects.add(new Objeto(username, name, image));
    }

    @Override
    public String getLevelData(int level) {
        return levels[level-1];
    }

}
