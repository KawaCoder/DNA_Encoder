module com.dr34mm4k3r.dnaencoder.dna_encoder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.dr34mm4k3r.dnaencoder.dna_encoder to javafx.fxml;
    exports com.dr34mm4k3r.dnaencoder.dna_encoder;
}