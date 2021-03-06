package edu.upc.eetac.dsa;

import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity);
    void register(Object entity);
    void close();
    Object get(Class theClass, int ID);
    Object login(Class theClass, int ID);
    int getID(Class theClass, String username, String password);
    int getID2(Class theClass, String username);
    void update(Object object, int ID);
    void delete(Object object, int ID);
    String getLevel(Class theClass, int level);
    List<Object> findAll(Class theClass);
    List<Object> findAllObjects(Class theClass, String username);
    List<Object> findAll(Class theClass, HashMap params);
    List<Object> query(String query, Class theClass, HashMap params);
}
