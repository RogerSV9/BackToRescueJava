package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;

import java.util.*;


public class BTRManagerImpl implements BTRManager {
    final static Logger log = Logger.getLogger(BTRManagerImpl.class.getName());
    private static BTRManager instance;

    private HashMap<String,User> users;
    private List<Objeto> objects;
    private List<Player> players;
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
        players = new ArrayList<>();
        levels = new String[10];
        levels[0] = "patata";
    }

    @Override
    public void UserRegistration(User user) {
        User user2 = users.get(user.username);
        if(user2 == null) {
            //users.put(user.username,new User(user.username,user.password));
            //AddCharacter(user.username,100,100,50,50,2,999);
            Session session = null;
            try {
                session = FactorySession.openSession();
                users.put(user.username,new User(user.username,user.password));
                Player player = new Player(user.username,100,100,50,50,2,999);
                session.register(user);
                session.register(player);
            }
            catch (Exception e) {
                // LOG
            }
            finally {
                session.close();
            }
        }
        else{
            log.error("User is already in the system");
        }
    }

    @Override
    public Player UserLogin(User user) throws UserNotFoundException {
        //User user2 = users.get(user.username);
        Player c = null;
        Session session = null;
        int userID;
        try {
            session = FactorySession.openSession();
            userID = (Integer) session.getID(User.class, user.getUsername(),user.getPassword());
            c = (Player) session.login(User.class, userID);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        /*if(user2 != null) {
            if (user2.getPassword().equals(user.password)) {
                for(int i = 0; i< this.players.size(); i++) {
                    if(user.username.equals(this.players.get(i).getUsername())){
                        c = this.players.get(i);
                    }
                }
            } else {
                log.error("Incorrect password");
            }
        }
        else{
            log.error("User not found");
            throw new UserNotFoundException();
        }*/

        return c;
    }

    @Override
    public void UserDelete(String username, String password) throws UserNotFoundException{
        User user = users.get(username);
        if(user != null) {
            if (user.getPassword().equals(password)) {
                this.users.remove(username);
                for(int i = 0; i< this.players.size(); i++) {
                    if(username.equals(this.players.get(i).getUsername())){
                        this.players.remove(i);
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
    public void LogOut(Player player) {
        UpdateStats(player);
    }

    @Override
    public Player GetStats(String username) throws UserNotFoundException{
        User user = this.users.get(username);
        Player c = null;
        if(user != null){
            for(int i = 0; i< this.players.size(); i++) {
                if(username.equals(this.players.get(i).getUsername())){
                    c = this.players.get(i);
                }
            }
        }
        else{
            throw new UserNotFoundException();
        }
        return c;
    }

    @Override
    public void UpdateStats(Player player) {
        for(int i = 0; i< this.players.size(); i++) {
            if(player.getUsername().equals(this.players.get(i).getUsername())){
                this.players.remove(i);
                this.players.add(new Player(player.getUsername(), player.getHealth(), player.getMana(), player.getLevel(), player.getDamage(), player.getDefense(), player.getMoney()));
            }
        }
    }

    @Override
    public List<Player> GetScoreboard() {
        Session session = null;
        List <Player> players = null;
        int userID;
        try {
            session = FactorySession.openSession();
            players = session.findAll(Player.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return players;
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
    public String GetLevelData(int level) {
        return levels[level-1];
    }



}
