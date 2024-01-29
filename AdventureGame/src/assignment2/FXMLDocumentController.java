/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Manish Maiyani
 */
public class FXMLDocumentController
{
    @FXML
    private TextArea displayTxtArea;

    @FXML
    private TextField cmdTxt;

    World world;
    String CommandNum;
    
    @FXML
    void btnHelpClickAction(ActionEvent event) 
    {
        //if help button click this message will be displayed in text area
        displayTxtArea.setText("Valid commands are:\n\tserch\n\tdescribe\n\tenter n\n\tgoto n\n");
    }
    
    @FXML
    void btnExitClickAction(ActionEvent event) 
    {
        Platform.exit(); //if exit button clicks this will exit the applcation
    }
    
    @FXML
    void newCommandEnter(ActionEvent event) 
    {
        String enteredCommand = cmdTxt.getText();
        
        if (enteredCommand.matches(".*\\s+.*")) // if command has blank spoce or not
        {
            String[] cmd = enteredCommand.split("\\s"); // split command with blank space
            String CommandName = cmd[0]; // storing the command name
            try
            {
                CommandNum = cmd[1]; // storing the command number
                switch (CommandName) 
                {
                    case "enter": // it will display the command's output on text area
                        displayTxtArea.setText(createCommand(CommandName).perform(world));
                        break;
                    case "goto":// it will display the command's output on text area
                        displayTxtArea.setText(createCommand(CommandName).perform(world));
                        break;
                    default:// it will display the command's output on text area
                        displayTxtArea.setText(createCommand("Unknown").perform(world));
                        break;
                }
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                // if you enter "entner " like this than it will display unknown command
                displayTxtArea.setText(createCommand("Unknown").perform(world));
            }
            
        }
        else if(enteredCommand.equals("describe"))
        {
            displayTxtArea.setText(createCommand(enteredCommand).perform(world));//describe command
        }
        else if(enteredCommand.equals("search"))
        {
            displayTxtArea.setText(createCommand(enteredCommand).perform(world));//search command
        }
        else
        {
            displayTxtArea.setText(createCommand("Unknown").perform(world)); //unknown command
        }
        cmdTxt.selectAll();
    }
    
    public void startGame(World world)
    {
        this.world = world;
        world.start(); //world's start method to start the game with room 1
        displayTxtArea.setText("Welcome to your new Advanture\n\n");
        displayTxtArea.appendText(world.describe()); // displaying room 1 (current room)'s info
        displayTxtArea.appendText("\n\nValid commands are:\n\tserch\n\tdescribe\n\tenter n\n\tgoto n\n");
    }
    
    private Command createCommand(String Command)
    {
        switch (Command)
        {
            case "enter":
                return new EnterCommand(CommandNum); //enter command created with room number (commandNum)
            case "goto":
                return new GotoCommand(CommandNum); //goto command created with room number (commandNum)
            case "Unknown":
                return new UnknownCommand(); //unknown command created
            case "describe":
                return new DescribeCommand(); //describe  command created
            case "search":
                return new SearchCommand();//search describe command created
        }
        return new UnknownCommand();
    }
}
