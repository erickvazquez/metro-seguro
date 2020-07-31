/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrosegurofx;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author EHef_
 */
public class loginController implements Initializable {
    
    @FXML
            
    private JFXButton entrar;
    
    @FXML
    private void entrar () throws IOException{
        Stage stage;
        Parent root = FXMLLoader.load(getClass().getResource("Reproductor.fxml"));
        stage = (Stage) entrar.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
