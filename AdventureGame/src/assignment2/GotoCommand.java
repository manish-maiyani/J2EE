/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

/**
 *
 * @author Manish Maiyani
 */
public class GotoCommand extends Command
{

    public GotoCommand(String args) 
    {
        super(args);
    }
    
    @Override
    public String perform(World world) 
    {
        if(world.exists(argument)) // if room exists in database or array tha it gets true
        {
            return world.describe(); //teleperted to that room
        }
        return "cannot enter "+argument+" room does not exist";
    }
    
}
