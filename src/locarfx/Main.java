package locarfx;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application{   

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/locarfx/View/frLogin.fxml"));
        
        Scene scene = new Scene(root, 800,600 );
        
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();        
    }
    
    public static void main(String[] args){
        launch();
    }
}
