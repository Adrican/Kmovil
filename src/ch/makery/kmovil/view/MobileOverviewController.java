package ch.makery.kmovil.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;



import ch.makery.kmovil.MainApp;
import ch.makery.kmovil.model.Mobile;

public class MobileOverviewController {
    @FXML
    private TableView<Mobile> mobileTable;
    @FXML
    private TableColumn<Mobile, String> nameColumn;
    @FXML
    private TableColumn<Mobile, String> priceColumn;

	@FXML
	private Label modeloLabel;
	@FXML
	private Label modeloLabel1;
	@FXML
	private Label precioLabel;
	@FXML
	private Label procesadorLabel;
	@FXML
	private Label ramLabel;
	@FXML
	private Label romLabel;
	@FXML
	private Label pantallaLabel;
	@FXML
	private Label bateriaLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MobileOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Initialize the person table with the two columns.
        nameColumn.setCellValueFactory(
        		cellData -> cellData.getValue().modeloProperty());
        priceColumn.setCellValueFactory(
        		cellData -> cellData.getValue().precioProperty());
        
        // Clear person details.
        showMobileDetails(null);

        // Listen for selection changes and show the person details when changed.
		mobileTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showMobileDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        mobileTable.setItems(mainApp.getMobileData());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showMobileDetails(Mobile mobile) {
    	if (mobile != null) {
    		// Fill the labels with info from the person object.
    		modeloLabel.setText(mobile.getModelo());
    		modeloLabel1.setText(mobile.getModelo());
			precioLabel.setText(mobile.getPrecio());
			procesadorLabel.setText(mobile.getProcesador());
			ramLabel.setText(mobile.getRAM());
			romLabel.setText(mobile.getROM());
			pantallaLabel.setText(mobile.getPantalla());
			bateriaLabel.setText(mobile.getBateria());
    	} else {
    		// Person is null, remove all the text.
			modeloLabel.setText("");
			precioLabel.setText("");
			procesadorLabel.setText("");
			ramLabel.setText("");
			romLabel.setText("");
			pantallaLabel.setText("");
			bateriaLabel.setText("");
    	}
    }

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteMobile() {
		int selectedIndex = mobileTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			mobileTable.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No seleccionado");
			alert.setHeaderText("No has seleccionado ningun móvil");
			alert.setContentText("Porfavor selecciona un movil que eliminar");

			alert.showAndWait();
		}
	}
	
	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNewMobile() {
		Mobile tempMobile = new Mobile();
		boolean okClicked = mainApp.showMobileEditDialog(tempMobile);
		if (okClicked) {
			mainApp.getMobileData().add(tempMobile);
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEditMobile() {
		Mobile selectedMobile = mobileTable.getSelectionModel().getSelectedItem();
		if (selectedMobile != null) {
			boolean okClicked = mainApp.showMobileEditDialog(selectedMobile);
			if (okClicked) {
				showMobileDetails(selectedMobile);
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No seleccionado");
			alert.setHeaderText("No has seleccionado ningun móvil");
			alert.setContentText("Porfavor selecciona un movil en la tabla");

			alert.showAndWait();
		}
	}
}