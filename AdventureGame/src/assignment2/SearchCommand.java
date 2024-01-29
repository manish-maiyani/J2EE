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
public class SearchCommand extends Command
{

    public SearchCommand() 
    {
        
    }

    @Override
    public String perform(World world) 
    {
        return world.getSearchDescription();
    }
    
}
