/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * original creator: DR34M-M4K3R
 */

package com.dr34mm4k3r.dnaencoder.dna_encoder;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;


public class HelloController {

    static JFrame frame;
    /*    public static void choixFichier(String indicator){
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Titre");
            FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);

            //fd.setFile("*.dna");
            // for mac os (?) --> fd.setFilenameFilter((dir, name) -> name.endsWith(".dna"));
            fd.setVisible(true);
            frame.setVisible(true);
            String filename = fd.getFile();
            System.out.println(filename);
           // if (filename == null){
             //   return null;
          //  }else{
                //return(filename);

        }*/
    public static void choixFichier(String indicator){

        if (Objects.equals(indicator, "encode")) {
            FileChooser f = new FileChooser();
            File selectedFile = f.showOpenDialog(null);
            System.out.println(selectedFile.getAbsolutePath());
        }


    }



    @FXML
    private Button encodeButton;

    @FXML
    private Button decodeButton;

    @FXML
    private Button aboutButton;



    @FXML
    protected void encodeAction() {
        System.out.println("encode");
        choixFichier("encode");

    }
    @FXML
    protected void decodeAction() {
        System.out.println("decode");
        choixFichier("decode");
    }
    @FXML
    protected void aboutAction() {
        System.out.println("about");
    }



}