package ch.makery.kmovil.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;





import ch.makery.kmovil.model.Mobile;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class MobileEditDialogController {

	@FXML
	private TextField modeloField;
	@FXML
	private TextField precioField;
	@FXML
	private TextField procesadorField;
	@FXML
	private TextField ramField;
	@FXML
	private TextField romField;
	@FXML
	private TextField pantallaField;
	@FXML
	private TextField bateriaField;

	private Stage dialogStage;
	private Mobile mobile;
	private boolean okClicked = false;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the person to be edited in the dialog.
	 * 
	 * @param person
	 */
	public void setMobile(Mobile mobile) {
		this.mobile = mobile;

		modeloField.setText(mobile.getModelo());
		precioField.setText(mobile.getPrecio());
		procesadorField.setText(mobile.getProcesador());
		ramField.setText(mobile.getRAM());
		romField.setText(mobile.getROM());
		pantallaField.setText(mobile.getPantalla());
		bateriaField.setText(mobile.getBateria());

	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			mobile.setModelo(modeloField.getText());
			mobile.setPrecio(precioField.getText());
			mobile.setProcesador(procesadorField.getText());
			mobile.setRAM(ramField.getText());
			mobile.setROM(romField.getText());
			mobile.setPantalla(pantallaField.getText());
			mobile.setBateria(bateriaField.getText());

			okClicked = true;
			dialogStage.close();
		}
	}

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
	private boolean isInputValid() {
		String errorMessage = "";

		if (modeloField.getText() == null || modeloField.getText().length() == 0) {
			errorMessage += "Modelo no valido";
		}
		if (precioField.getText() == null || precioField.getText().length() == 0) {
			errorMessage += "Precio no valido";
		}
		if (procesadorField.getText() == null || procesadorField.getText().length() == 0) {
			errorMessage += "Procesador no valido";
		}

		if (ramField.getText() == null || ramField.getText().length() == 0) {
			errorMessage += "RAM no valida";
		}

		if (romField.getText() == null || romField.getText().length() == 0) {
			errorMessage += "ROM no valida";
		}

		if (pantallaField.getText() == null || pantallaField.getText().length() == 0) {
			errorMessage += "Pantalla no valida";
		}

		if (bateriaField.getText() == null || bateriaField.getText().length() == 0) {
			errorMessage += "Bateria no valida";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();
			return false;
		}
	}
}