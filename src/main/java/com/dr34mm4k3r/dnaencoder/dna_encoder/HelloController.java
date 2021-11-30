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
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
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

            OutputStream outputData = new FileOutputStream("temp.txt");
            outputData.write(binaryData.toString().getBytes());

            // [IMPORTANT] Close all the streams

            fileData.close();
            outputData.close();
            inputData.close();


            FileWriter outputStream = null;
            FileWriter addZeroToTemp = null;

            try {



                ArrayList<String> words = new ArrayList<>();
                try (Scanner s = new Scanner(new File("temp.txt")).useDelimiter("\\s* \\s*")) {
                    // \\s* in regular expressions means "any number or whitespaces".
                    // We could've said simply useDelimiter("-") and Scanner would have
                    // included the whitespaces as part of the data it extracted.
                    while (s.hasNext()) {
                        words.add(s.next());
                    }
                }
                catch (FileNotFoundException e) {
                    // Handle the potential exception
                }


                addZeroToTemp = new FileWriter("temp.txt");

                for (int ii = 0;ii<words.size();ii++){
                    if (words.get(ii).length()==7){
                        words.set(ii, ("0" + words.get(ii)));

                    }
                    words.set(ii, (words.get(ii) + "  "));
                    addZeroToTemp.write(words.get(ii));
                }

                addZeroToTemp.close();


                //inputStream = new FileReader("temp.txt");
                outputStream = new FileWriter("EncodedFile.dna");


                int i = 1;
                StringBuilder tempchars = new StringBuilder();

                for (String word : words) {
                    for (int jj = 0; jj < word.length(); jj++) {
                        System.out.println(word + " index: "+jj);
                        tempchars.append(word.charAt(jj));

                       if (i%2==0)  {

                            if (Objects.equals(String.valueOf(tempchars), "00")) {
                                outputStream.write("at");
                                // System.out.println("00 --> at");

                            } else if (Objects.equals(String.valueOf(tempchars), "  ")) {
                                outputStream.write("ag");
                                //System.out.println("'  ' --> ag");

                                tempchars = new StringBuilder();
                            }else if (Objects.equals(String.valueOf(tempchars), "01")) {
                                outputStream.write("ta");
                                // System.out.println("01 --> ta");

                            } else if (Objects.equals(String.valueOf(tempchars), "10")) {
                                outputStream.write("cg");
                                // System.out.println("10 --> cg");

                            } else if (Objects.equals(String.valueOf(tempchars), "11")) {
                                outputStream.write("gc");
                                //  System.out.println("11 --> gc");

                            }
                            tempchars = new StringBuilder();

                        }

                        i++;

                    }


                }
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (addZeroToTemp != null) {
                    addZeroToTemp.close();
                }
            }


/*
                int c;
                int i = 1;
                StringBuilder tempchars = new StringBuilder();


                while ((c = inputStream.read()) != -1) {

                    tempchars.append((char) c);
                    //System.out.println(tempchars);

                    if (Objects.equals(String.valueOf(tempchars), " ")) {
                        outputStream.write("aaa");
                        //System.out.println("' ' --> aaa");
                        tempchars = new StringBuilder();
                    }else if (i%2==0)  {

                        if (Objects.equals(String.valueOf(tempchars), "00")) {
                            outputStream.write("at");
                           // System.out.println("00 --> at");


                        } else if (Objects.equals(String.valueOf(tempchars), "01")) {
                            outputStream.write("ta");
                           // System.out.println("01 --> ta");

                        } else if (Objects.equals(String.valueOf(tempchars), "10")) {
                            outputStream.write("cg");
                           // System.out.println("10 --> cg");

                        } else if (Objects.equals(String.valueOf(tempchars), "11")) {
                            outputStream.write("gc");
                          //  System.out.println("11 --> gc");

                        }
                        tempchars = new StringBuilder();

                    }

                    i++;
                }
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }*/




        }else{
            // DECODE





            FileReader inputStream = null;
            FileWriter outputStream = null;
            FileWriter removeZeroFromTemp = null;

            try {
                inputStream = new FileReader(filepath);
                outputStream = new FileWriter("temp.txt");



                int c;
                int i = 1;
                StringBuilder tempchars = new StringBuilder();


                while ((c = inputStream.read()) != -1) {

                    tempchars.append((char) c);

                    if (i%2==0)  {


                        if (Objects.equals(String.valueOf(tempchars), "at")) {
                            outputStream.write("00");
                            System.out.println("00 --> at");


                        } else if (Objects.equals(String.valueOf(tempchars), "ta")) {
                            outputStream.write("01");
                            System.out.println("01 --> ta");

                        } else if (Objects.equals(String.valueOf(tempchars), "ag")) {
                            outputStream.write(" ");
                            System.out.println("' ' --> ag");

                        } else if (Objects.equals(String.valueOf(tempchars), "cg")) {
                            outputStream.write("10");
                            System.out.println("10 --> cg");

                        } else if (Objects.equals(String.valueOf(tempchars), "gc")) {
                            outputStream.write("11");
                            System.out.println("11 --> gc");

                        }

                        tempchars = new StringBuilder("");
                    }

                    i++;
                }

                outputStream.close();

                ArrayList<String> words = new ArrayList<>();
                try (Scanner s = new Scanner(new File("temp.txt")).useDelimiter("\\s* \\s*")) {
                    // \\s* in regular expressions means "any number or whitespaces".
                    // We could've said simply useDelimiter("-") and Scanner would have
                    // included the whitespaces as part of the data it extracted.
                    while (s.hasNext()) {
                        words.add(s.next());
                    }
                }
                catch (FileNotFoundException e) {
                    // Handle the potential exception
                }


                removeZeroFromTemp= new FileWriter("temp.txt");

                for (int ii = 0;ii<words.size();ii++){
                    if (words.get(ii).length()==8){
                        words.set(ii, (words.get(ii).substring(1)));

                    }
                    words.set(ii, (words.get(ii)+" "));
                    removeZeroFromTemp.write(words.get(ii));
                }

                removeZeroFromTemp.close();



            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (removeZeroFromTemp != null) {
                    removeZeroFromTemp.close();
                }
            }






            // -->>Just reverse the process

            // Read all the bytes from the input (binary code(2)) file to string

            InputStream inputData = new FileInputStream("temp.txt");
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