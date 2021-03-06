package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.util.ObjectHelper;
import edu.upc.eetac.dsa.util.QueryHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity) {

        String insertQuery = QueryHelper.createQueryINSERT(entity);

        // INSERT INTO Employee (ID, name, surname, salary) VALUES (?, ?, ?, ?)

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            pstm.setObject(1,0);
            int i = 2;

            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void register(Object entity) {

        String insertQuery = QueryHelper.createQueryRegister(entity);

        // INSERT INTO Employee (ID, name, surname, salary) VALUES (?, ?, ?, ?)

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            pstm.setObject(1,0);
            int i = 2;

            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getFieldName(int i, ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        String name = rsmd.getColumnName(i);

        return name;
    }

    public Object get(Class theClass, int ID) {
        String selectQuery = QueryHelper.createQuerySELECT2(theClass);

        Object entity = null;
        try {
            entity = theClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1,ID);
            rs = pstm.executeQuery();


            while(rs.next()){
                Field[] fields = theClass.getDeclaredFields();
                rs.getString(1);
                for (int i = 0; i<fields.length; i++){


                    String fieldName = this.getFieldName(i+2, rs);

                    ObjectHelper.setter(entity, fieldName, rs.getObject(i + 2));
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return entity;
    }
    public Object login(Class theClass, int ID) {
        String selectQuery = QueryHelper.createQueryPlayer(theClass);

        Object entity = null;
        try {
            entity = theClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1,ID);
            rs = pstm.executeQuery();


            while(rs.next()){
                Field[] fields = theClass.getDeclaredFields();
                rs.getString(1);
                for (int i = 0; i<fields.length; i++){


                    String fieldName = this.getFieldName(i+2, rs);

                    ObjectHelper.setter(entity, fieldName, rs.getObject(i + 2));
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public int getID(Class theClass, String username, String password) {
        String selectQuery = QueryHelper.createQuerySELECTIDUSER(theClass);

        ResultSet rs;
        PreparedStatement pstm;

        int id = 0;

        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, username);
            pstm.setObject(2, password);
            rs = pstm.executeQuery();

            rs.next();

            id = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            //throw new UserNotFoundException();
        }

        return id;
    }

    @Override
    public String getLevel(Class theClass, int level) {
        String selectQuery = QueryHelper.createQuerySELECTLEVEL(theClass);

        ResultSet rs;
        PreparedStatement pstm;

        String data = null;

        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, level);
            rs = pstm.executeQuery();

            rs.next();

            data = rs.getString(3);

        } catch (SQLException e) {
            e.printStackTrace();
            //throw new UserNotFoundException();
        }

        return data;
    }

    @Override
    public int getID2(Class theClass, String username) {
        String selectQuery = QueryHelper.createQuerySELECTID(theClass);

        ResultSet rs;
        PreparedStatement pstm;

        int id = 0;

        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, username);
            rs = pstm.executeQuery();

            rs.next();

            id = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            //throw new UserNotFoundException();
        }

        return id;
    }

    public void update(Object object, int ID) {
        String updateQuery = QueryHelper.createQueryUPDATE(object);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(updateQuery);
            int i = 1;


            for(String field: ObjectHelper.getFields(object)){
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }

            pstm.setObject(i,ID);
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void delete(Object object, int ID) {
        String deleteQuery = QueryHelper.createQueryDELETE(object);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(deleteQuery);
            pstm.setObject(1, ID);

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Object> findAll(Class theClass) {
        String findAllQuery = QueryHelper.findAllQuery(theClass);

        Object entity = null;
        List<Object> listOfObjects = new ArrayList<Object>();

        try {
            entity = theClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(findAllQuery);
            rs = pstm.executeQuery();

            while(rs.next()){
                Field[] fields = theClass.getDeclaredFields();
                rs.getString(1);
                for (int i = 0; i<fields.length; i++){
                    ObjectHelper.setter(entity, fields[i].getName(), rs.getObject(i + 2));
                }

                listOfObjects.add(entity);

                entity = theClass.newInstance();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return listOfObjects;
    }
    public List<Object> findAllObjects(Class theClass, String username) {
        String findAllQuery = QueryHelper.createQuerySELECTID(theClass);


        Object entity = null;
        List<Object> listOfObjects = new ArrayList<Object>();

        try {
            entity = theClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(findAllQuery);
            pstm.setObject(1, username);
            rs = pstm.executeQuery();

            while(rs.next()){
                Field[] fields = theClass.getDeclaredFields();
                rs.getString(1);
                for (int i = 0; i<fields.length; i++){
                    ObjectHelper.setter(entity, fields[i].getName(), rs.getObject(i + 2));
                }

                listOfObjects.add(entity);

                entity = theClass.newInstance();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return listOfObjects;
    }

    /*
     *          HashMap params = new HashMap();
     *          params.put("edat", new Condition("=", 15);
     *          params.put("salary", new Condition(100000, ">=");
     *          session.findAll(Employee.class, params);
     *
     *          SELECT * FROM Employee WHERE ( edat = 15 AND salary>=10000)
     */
    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

    /*
     *          SELECT * FROM Employee, Deparment  WHERE ( edat = 15 AND salary>=10000)
     */
    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}
