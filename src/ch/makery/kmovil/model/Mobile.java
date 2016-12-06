package ch.makery.kmovil.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Mobile {

    private final StringProperty modelo;
    private final StringProperty precio;
    private final StringProperty procesador;
    private final StringProperty ram;
    private final StringProperty rom;
    private final StringProperty pantalla;
    private final StringProperty bateria;

    /**
     * Default constructor.
     */
    public Mobile() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Mobile(String modelo, String precio) {
        this.modelo = new SimpleStringProperty(modelo);
        this.precio = new SimpleStringProperty(precio);

        // Some initial dummy data, just for convenient testing.
        this.procesador = new SimpleStringProperty("proce");
        this.ram = new SimpleStringProperty("ram");
        this.rom = new SimpleStringProperty("rom");
        this.pantalla = new SimpleStringProperty("pantalla");
        this.bateria = new SimpleStringProperty("bateria");
    }

    public String getModelo() {
        return modelo.get();
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    public StringProperty modeloProperty() {
        return modelo;
    }

    public String getPrecio() {
        return precio.get();
    }

    public void setPrecio(String precio) {
        this.precio.set(precio);
    }

    public StringProperty precioProperty() {
        return precio;
    }

    public String getProcesador() {
        return procesador.get();
    }

    public void setProcesador(String procesador) {
        this.procesador.set(procesador);
    }

    public StringProperty procesadorProperty() {
        return procesador;
    }

    public String getRAM() {
        return ram.get();
    }

    public void setRAM(String ram) {
        this.ram.set(ram);
    }

    public StringProperty ramProperty() {
        return ram;
    }

    public String getBateria() {
        return bateria.get();
    }

    public void setBateria(String bateria) {
        this.bateria.set(bateria);
    }

    public StringProperty BateriaProperty() {
        return bateria;
    }

    public String getPantalla() {
        return pantalla.get();
    }

    public void setPantalla(String pantalla) {
        this.pantalla.set(pantalla);
    }

    public StringProperty streetPantalla() {
        return pantalla;
    }


    public String getROM() {
        return rom.get();
    }

    public void setROM(String rom) {
        this.rom.set(rom);
    }

    public StringProperty ROMProperty() {
        return rom;
    }
}