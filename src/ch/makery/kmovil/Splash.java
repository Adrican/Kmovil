package ch.makery.kmovil;

import java.io.IOException;

import ch.makery.kmovil.view.RootLayoutController;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class Splash extends Application{

		  private static Stage primaryStage;
	    private BorderPane rootLayout;


	    @Override
	    public void start(Stage primaryStage) {
	        this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("Kmovil");
	        this.primaryStage.getIcons().add(
					new Image("file:resources/images/faviconfinal.png"));
	        initRootLayout();
	    }

	    /**
	     * Initializes the root
	     * @author Adri
	     * @version 1
	     */
	    public void initRootLayout() {
	        try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Splash.class.getResource("view/SplashScreen.fxml"));
	            rootLayout = (BorderPane) loader.load();
	            

	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout,525,525);				

//				
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
	            primaryStage.show();
	            PauseTransition delay = new PauseTransition(Duration.seconds(5));
				delay.setOnFinished( event -> primaryStage.close() );
				delay.play();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }


		
		public static void main(String[] args) {
			launch(args);
			
		}
	}
