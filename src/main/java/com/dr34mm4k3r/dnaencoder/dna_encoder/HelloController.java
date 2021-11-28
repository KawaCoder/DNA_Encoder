/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * original creator: DR34M-M4K3R
 */

package com.dr34mm4k3r.dnaencoder.dna_encoder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.*;
import java.math.BigInteger;
import java.util.Objects;
import java.util.StringJoiner;


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
    public static void choixFichier(String indicator) throws IOException {
        FileChooser f = new FileChooser();
        File selectedFile = f.showOpenDialog(null);
        String filepath = selectedFile.getAbsolutePath();
        System.out.println(selectedFile.getAbsolutePath());




        if (Objects.equals(indicator, "encode")) {

            // from: https://stackoverflow.com/a/65344795:

            // Read all the bytes from the input file

            InputStream inputData = new FileInputStream(filepath);
            ByteArrayOutputStream fileData = new ByteArrayOutputStream();
            inputData.transferTo(fileData);

            // StringJoiner to store binary code(2) encoded

            StringJoiner binaryData = new StringJoiner(" ");

            // Convert every byte into binaryString

            for(Byte data : fileData.toByteArray()) {
                binaryData.add(Integer.toBinaryString(data));
            }

            // (File)OutputStream for writing binary code(2)

            OutputStream outputData = new FileOutputStream("taemp.txt");
            outputData.write(binaryData.toString().getBytes());

            // [IMPORTANT] Close all the streams

            fileData.close();
            outputData.close();
            inputData.close();



            try
            {
                String ENDL = System.getProperty("line.separator");

                StringBuilder sb = new StringBuilder();

                BufferedReader br = new BufferedReader(new FileReader("temp.txt"));
                String ln;
                while((ln = br.readLine()) != null)
                {
                    sb.append(ln
                            .replace(" 1", "01")/*
                            .replace("00", "at")
                            .replace("01", "ta")
                            .replace("10", "cg")
                            .replace("11", "gc")*/
                    ).append(ENDL);
                }
                br.close();

                BufferedWriter bw = new BufferedWriter(new FileWriter(String.valueOf("fini.txt")));
                bw.write(sb.toString());
                bw.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }





        }else{
            // DECODE

            // -->>Just reverse the process

            // Read all the bytes from the input (binary code(2)) file to string

            InputStream inputData = new FileInputStream(filepath);
            ByteArrayOutputStream fileData = new ByteArrayOutputStream();
            inputData.transferTo(fileData);

            // ByteArrayOutputStream to store bytes decoded

            ByteArrayOutputStream originalBytes = new ByteArrayOutputStream();

            // Convert every binary code(2) to original byte(s)

            for(String data : new String(fileData.toByteArray()).split(" ")) {
                originalBytes.write(new BigInteger(data, 2).toByteArray());
            }

            // (File)OutputStream for writing decoded bytes

            OutputStream outputData = new FileOutputStream("Decodedfile.txt");
            outputData.write(originalBytes.toByteArray());

            // [IMPORTANT] Close all the streams

            inputData.close();
            fileData.close();
            originalBytes.close();
            outputData.close();
        }




    }



    @FXML
    private Button encodeButton;

    @FXML
    private Button decodeButton;

    @FXML
    private Button aboutButton;



    @FXML
    protected void encodeAction() throws IOException {
        System.out.println("encode");
        choixFichier("encode");

    }
    @FXML
    protected void decodeAction() throws IOException {
        System.out.println("decode");
        choixFichier("decode");
    }
    @FXML
    protected void aboutAction() {
        System.out.println("about");
    }



}