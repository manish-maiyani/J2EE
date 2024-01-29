/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Manish Maiyani
 */
public class Assignment2 extends Application 
{
    @Override
    public void start(Stage stage) throws Exception 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        FXMLDocumentController controller = loader.getController(); //for getting the controller class
        
        Scene scene = new Scene(root);
    
        World map = new World();
        controller.startGame(map); //loading the map and star it with current room = 1
        
        System.out.println("Data in Map:");
        map.displayMapData(); //display all the map data in standard output are
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
