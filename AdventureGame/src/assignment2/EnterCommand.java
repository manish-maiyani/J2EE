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
public class EnterCommand extends Command
{

    public EnterCommand(String args) 
    {
        super(args);
    }

    @Override
    public String perform(World world) 
    {
        // checking the current edge (doors) number is accessible from current room or not 
        if(world.accessible(argument)) 
        {
            //if yes then it will retun entered room <number> message with that room description
            return world.enter(argument) + world.describe();
        }
        //if not that this message will return with current room's description
       return "cannot enter "+argument+" from here.\n"+world.describe();
                                  
    }
    
}
