/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;


/**
 *
 * @author Manish Maiyani
 */
public class World 
{
    //database URL and Queries
    private final String DATABASE_URL = "jdbc:derby://localhost:1527/Assignment2";
    private final String SELECT_ROOM_QUERY = "SELECT * FROM ROOMS";
    private final String SELECT_EDGES_QUERY = "SELECT * FROM EDGES";
    private final String USER = "rahul";
    private final String PASSWORD = "123";

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    
    private HashMap<String, Room> map;   
    private Room currentRoom; 

    /*
    static String[] roomIDs = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
                               "12", "13", "14", "15", "16", "17", "18", "19", "20",};
    
    static String[] roomDescriptions = {"desc1", "desc2", "desc3", "desc4", "desc5",
                                        "desc6", "desc7", "desc8", "desc9", "desc10",
                                        "desc11","desc12", "desc13", "desc14", "desc15",
                                        "desc16","desc17", "desc18", "desc19", "desc20",};
    
    static String[] searchDescriptions = {"room1 detailed description of results of search", "room2 detailed description of results of search",
                                          "room3 detailed description of results of search", "room4 detailed description of results of search",
                                          "room5 detailed description of results of search", "room6 detailed description of results of search",
                                          "room7 detailed description of results of search", "room8 detailed description of results of search",
                                          "room9 detailed description of results of search", "room10 detailed description of results of search",
                                          "room11 detailed description of results of search","room12 detailed description of results of search",
                                          "room13 detailed description of results of search","room14 detailed description of results of search",
                                          "room15 detailed description of results of search","room16 detailed description of results of search",
                                          "room17 detailed description of results of search","room18 detailed description of results of search",
                                          "room19 detailed description of results of search","room20 detailed description of results of search",};
    
    static String[][] edges = {{"1", "11"}, {"1", "10"},{"1", "2"},
                               {"2", "12"}, {"2", "1"}, {"2", "3"},
                               {"3", "13"}, {"3", "2"}, {"3", "4"},
                               {"4", "14"}, {"4", "13"},{"4", "3"},{"4", "5"},
                               {"5", "15"}, {"5", "4"}, {"5", "6"},
                               {"6", "16"}, {"6", "5"}, {"6", "7"},
                               {"7", "17"}, {"7", "6"}, {"7", "8"},
                               {"8", "18"}, {"8", "7"}, {"8", "9"},
                               {"9", "19"}, {"9", "8"}, {"9", "10"},
                               {"10", "20"},{"10", "9"},{"10", "1"},
                               {"11", "1"}, {"11", "19"},{"11", "13"},
                               {"12", "2"}, {"12", "20"},{"12", "14"},
                               {"13", "4"}, {"13", "3"}, {"13", "11"},{"13", "15"},
                               {"14", "4"}, {"14", "12"},{"14", "16"},
                               {"15", "5"}, {"15", "13"},{"15", "17"},
                               {"16", "6"}, {"16", "14"},{"16", "18"},
                               {"17", "7"}, {"17", "15"},{"17", "19"},
                               {"18", "8"}, {"18", "16"},{"18", "20"},
                               {"19", "9"},{"19", "17"},{"19", "11"},
                               {"20", "10"},{"20", "18"},{"20", "12"}};
    */



    
    public World() 
    {
        //initialize the map and load tha hashMap
        map = new HashMap<>();
        loadMap();
    }

    public void loadMap()
    {
        //load the map from the array
        /*for(int i = 0; i<roomIDs.length; i++)
        {
            Room room = new Room( roomIDs[i], roomDescriptions[i], searchDescriptions[i]);
            map.put(roomIDs[i], room);
        }
        //adding the doors (edges) to related rooms
        for (String[] edge : edges) 
        {
            String from = edge[0];
            String to = edge[1];
            Room room = map.get(from);
            room.addDoor(to);
        }*/
        
        try
        {
            //making conection
            connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
            statement = connection.createStatement();//creating statement
            //executing the select statement and stare in resultSet
            resultSet = statement.executeQuery(SELECT_ROOM_QUERY);
            
            while(resultSet.next())
            {
                //loading the map from resultset
                Room room = new Room(resultSet.getString("ROOMID"), resultSet.getString("ROOMDESC"), resultSet.getString("SEARCHDESC"));
                map.put(resultSet.getString("ROOMID"), room);
            }
            
            ResultSet resultSet1 = statement.executeQuery(SELECT_EDGES_QUERY);
            
            while(resultSet1.next())
            {
                // adding the doors to room from database through database
                Room room = map.get(resultSet1.getString("ROOM0"));
                room.addDoor(resultSet1.getString("ROOM1"));
                room.addDoor(resultSet1.getString("ROOM2"));
                room.addDoor(resultSet1.getString("ROOM3"));
            }
            resultSet1.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try 
            {
                //closing statements
                connection.close();
                statement.close();
                resultSet.close();
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
        
    }
    
    public void displayMapData()
    {
        //displaying the data in standard output area with help of collection from map
        Collection<Room> values = map.values();
        for (Room r : values) 
        {
            System.out.print(r + "\n\n");
        }
    }
    
    public String getSearchDescription()
    {
        return currentRoom.getSearchDescription();
    }
    
    public String describe()
    {
        return currentRoom.toString();
    }
    
    public String enter(String room)
    {
        String enteredRoom = "Entered room: "+room+"\n";
        return enteredRoom;
    }
    
    public boolean exists(String room)
    {
        //checking through roomIDs ot check the room is exists or not
        /*for (String roomID : roomIDs) 
                {
                    if (roomID.equals(room)) 
                    {
                        //if yes then set as current room
                        currentRoom = map.get(room);
                        return true;
                    }
        }*/
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL,"rahul","123");
            statement = connection.createStatement();
            ResultSet resultSet3 = statement.executeQuery("SELECT ROOMID FROM ROOMS where ROOMID = '"+room+"'");
            
            while(resultSet3.next())
            {
                 //checking through ROOMS in ROOMS table to check the room is exists or not
                if(resultSet3.getString("ROOMID").equals(room))
                {
                    //if yes then set as current room
                    currentRoom = map.get(room);
                    return true;
                }
            }
            resultSet3.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean accessible(String room)
    {
        // checking the if current room has edges or not
        if(currentRoom.hasDoor(room))
        {
            //if yes than set as current room
            currentRoom = map.get(room);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void start()
    {
        //start the game with room 1
        currentRoom = map.get("1");
    }
}
