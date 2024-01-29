/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.util.ArrayList;

/**
 *
 * @author Manish Maiyani
 */
public class Room
{
    private String roomID;
    private String description;
    private String searchDescription; 
    private ArrayList <Door> doors;

    public Room(String roomID, String description, String searchDescription) 
    {
        this.roomID = roomID;
        this.description = description;
        this.searchDescription = searchDescription;
        doors = new ArrayList<>();
    }
    
    public void addDoor(String to)
    {
        Door door = new Door(roomID, to);
        doors.add(door);    //adding edges to door array list
    }
    
    public boolean hasDoor(String door)
    {
        for(Door d : doors)
        {
            if(d.getTo().equals(door)) //checking if door is accessible to perticuler room or not
            {
                return true;
            }
        }
        return false;
    }
    
    public String getSearchDescription()
    {
        return searchDescription;
    }
    
    public String roomDescription()
    {
        return description;
    }

    @Override
    public String toString() 
    { 
        //display current room's data 
        StringBuffer buffer = new StringBuffer();
        buffer.append("you are in room ").append(roomID);
        buffer.append("\n\n");
        buffer.append(roomDescription());
        buffer.append("\n");
        buffer.append("\nYou can see the following doors:");
        
        for (Door d : doors)
        {
            buffer.append("\n").append(d.toString());
            
        }
        return buffer.toString();
    }
    
}
