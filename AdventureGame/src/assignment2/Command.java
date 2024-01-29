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
public abstract class Command 
{
    protected String argument;

    public Command() 
    {
    
    }

    public Command(String argument) 
    {
        this.argument = argument;
    }
    //abstract perform method
    public abstract String perform(World world);
    
}
