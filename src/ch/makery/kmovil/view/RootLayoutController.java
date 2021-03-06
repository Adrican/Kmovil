package ch.makery.kmovil.view;

import java.io.File;
import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import ch.makery.kmovil.MainApp;
import ch.makery.kmovil.Splash;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author Marco Jakob, Adrian Canas and Marco Perez
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;
    public static Stage sendStage;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Este metodo mostrar� la splashscreen
     * 
     * @Author Ernesto Ramiro Cordoba, Modified by Adrian Canas Ramos
     */
	public void showSendProfile() {
    try {
    	
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Splash.class.getResource("view/SplashScreen.fxml"));
			BorderPane page = (BorderPane) loader.load();
			sendStage = new Stage();
			sendStage.setTitle("Kmovil");
			this.sendStage.getIcons().add(
					new Image("file:resources/images/faviconfinal.png"));
			Scene scene = new Scene(page);
			sendStage.setScene(scene);

			sendStage.show();
			mostrarSegs();
	        PauseTransition delay = new PauseTransition(Duration.seconds(5));
			delay.setOnFinished( event -> sendStage.close() );
			delay.play();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
}
	
	@FXML
	public void sendStage() {
		showSendProfile();
		
		
	}
    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        mainApp.getMobileData().clear();
        mainApp.setMobileFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadMobileDataFromFile(file);
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File personFile = mainApp.getMobileFilePath();
        if (personFile != null) {
            mainApp.saveMobileDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.saveMobileDataToFile(file);
		}
	}

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Kmovil");
		alert.setHeaderText("About");
		alert.setContentText("Designed and modified by Adrian Canas Ramos and Marco Perez Aranda \nBased on Marco Jakob Project \nWebsite: http://code.makery.ch ");

		alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
	/**
	 * Muestra el sendStage 5 segundos y lo cierra para hacer el efecto de splashscreen
	 * 
	 * @author Adrian Canas Ramos
	 */
	public void mostrarSegs(){
		PauseTransition delay = new PauseTransition(Duration.seconds(5));
		delay.setOnFinished( event -> sendStage.close() );
		delay.play();
	}
}