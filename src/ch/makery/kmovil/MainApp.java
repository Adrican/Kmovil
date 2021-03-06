package ch.makery.kmovil;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


import ch.makery.kmovil.model.Mobile;
import ch.makery.kmovil.model.MobileListWrapper;
import ch.makery.kmovil.view.MobileEditDialogController;
import ch.makery.kmovil.view.MobileOverviewController;
import ch.makery.kmovil.view.RootLayoutController;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<Mobile> mobileData = FXCollections
			.observableArrayList();

	/**
	 * Constructor
	 */
	public MainApp() {
		// Add some sample data
		mobileData.add(new Mobile("Xiaomi mi5", "300�"));
	}

	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Kmovil");

		// Set the application icon.
		this.primaryStage.getIcons().add(
				new Image("file:resources/images/faviconfinal.png"));
		

		initRootLayout();

		showPersonOverview();
		RootLayoutController rl = new RootLayoutController();
		rl.sendStage();
	}

	/**
	 * Initializes the root layout and tries to load the last opened
	 * person file.
	 */
	public void initRootLayout() {
		try {
			
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.hide();
			esconderSegs();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Try to load last opened person file.
		File file = getMobileFilePath();
		if (file != null) {
			loadMobileDataFromFile(file);
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showPersonOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MobileOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			// Give the controller access to the main app.
			MobileOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param person
	 *            the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showMobileEditDialog(Mobile mobile) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/MobileEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Mobile");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.getIcons().add(
					new Image("file:resources/images/faviconfinal.png"));
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			MobileEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMobile(mobile);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 * 
	 * @return
	 */
	public File getMobileFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 * 
	 * @param file
	 *            the file or null to remove the path
	 */
	public void setMobileFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update the stage title.
			primaryStage.setTitle("Kmovil - " + file.getName());
		} else {
			prefs.remove("filePath");

			// Update the stage title.
			primaryStage.setTitle("Kmovil");
		}
	}

	/**
	 * Loads person data from the specified file. The current person data will
	 * be replaced.
	 * 
	 * @param file
	 */
	public void loadMobileDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext
					.newInstance(MobileListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			// Reading XML from the file and unmarshalling.
			MobileListWrapper wrapper = (MobileListWrapper) um.unmarshal(file);

			mobileData.clear();
			mobileData.addAll(wrapper.getPersons());

			// Save the file path to the registry.
			setMobileFilePath(file);

		} catch (Exception e) { // catches ANY exception
//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setTitle("Error");
//			alert.setHeaderText("Could not load data to file:\n" + file.getPath());
//			alert.setContentText(e.getMessage());
//
//			alert.showAndWait();
		}
	}

	/**
	 * Saves the current person data to the specified file.
	 * 
	 * @param file
	 */
	public void saveMobileDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext
					.newInstance(MobileListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			MobileListWrapper wrapper = new MobileListWrapper();
			wrapper.setPersons(mobileData);

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);
			
			// Save the file path to the registry.
			setMobileFilePath(file);
			
		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data to file:\n" + file.getPath());
			alert.setContentText(e.getMessage());

			alert.showAndWait();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Returns the data as an observable list of Persons.
	 * 
	 * @return
	 */
	public ObservableList<Mobile> getMobileData() {
		return mobileData;
	}
	
	/**
	 * Esconde el primarystage 6 segundos para hacer el efecto de splashscreen
	 * 
	 * @author Adrian Canas Ramos
	 */
	public void esconderSegs(){
		PauseTransition delay = new PauseTransition(Duration.seconds(5));
		delay.setOnFinished( event -> primaryStage.show() );
		delay.play();
	}

	public static void main(String[] args) {
		launch(args);
	}
}