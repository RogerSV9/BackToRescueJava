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
        levels[0] = "patata";
    }

    @Override
    public void UserRegistration(User user) {
        User user2 = users.get(user.username);
        if(user2 == null) {
            users.put(user.username,new User(user.username,user.password));
            AddCharacter(user.username,100,100,50,50,2,999);
        }
        else{
            log.error("User is already in the system");
        }
    }

    @Override
    public Character UserLogin(User user) throws UserNotFoundException {
        User user2 = users.get(user.username);
        Character c = null;
        if(user2 != null) {
            if (user2.getPassword().equals(user.password)) {
                for(int i = 0; i< this.characters.size(); i++) {
                    if(user.username.equals(this.characters.get(i).getUsername())){
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
        if(user != null) {
            if (user.getPassword().equals(password)) {
                this.users.remove(username);
                for(int i = 0; i< this.characters.size(); i++) {
                    if(username.equals(this.characters.get(i).getUsername())){
                        this.characters.remove(i);
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
    }

    @Override
    public void LogOut(Character character) {
        UpdateStats(character);
    }

    @Override
    public void AddCharacter(String username, int health, int mana, int damage, int defense, int level, double money) {
        characters.add(new Character(username, health, mana, damage, defense, level, money));
    }

    @Override
    public Character GetStats(String username) throws UserNotFoundException{
        User user = this.users.get(username);
        Character c = null;
        if(user != null){
            for(int i = 0; i< this.characters.size(); i++) {
                if(username.equals(this.characters.get(i).getUsername())){
                    c = this.characters.get(i);
                }
            }
        }
        else{
            throw new UserNotFoundException();
        }
        return c;
    }

    @Override
    public void UpdateStats(Character character) {
        for(int i = 0; i< this.characters.size(); i++) {
            if(character.getUsername().equals(this.characters.get(i).getUsername())){
                this.characters.remove(i);
                this.characters.add(new Character(character.getUsername(),character.getHealth(),character.getMana(),character.getLevel(),character.getDamage(),character.getDefense(),character.getMoney()));
            }
        }
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
