package gestionefile;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author MC
 * @version 12/01/23
 */


public class Scrittore implements Runnable {
    String nomeFile;
    String username;
    String password;

    public Scrittore(String nomeFile, String username, String password) {
        this.nomeFile = nomeFile;
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        scrivi();
        copia();
        scrivi_DataOutputStream();
    }

    /**
     * Scrive un file di testo usando la classe BufferedWriter
     */
    public void scrivi() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(this.nomeFile))) {
            //1) apro il file
            //2) scrivo nel buffer
            br.write("File in output");
            br.newLine(); // Use newLine() instead of "\n\r"
            br.write("<username: " + this.username + "><password: " + this.password + ">");
            //3) svuoto il buffer e salvo nel file i dati
            br.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void copia() {
        try (BufferedReader brCopia = new BufferedReader(new FileReader("user.json"));
             BufferedWriter bwCopia = new BufferedWriter(new FileWriter("copia.csv", true))) {

            String line;
            while ((line = brCopia.readLine()) != null) {
                bwCopia.write(line);
                bwCopia.newLine(); // Add a new line after each line copied
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    //punto #2 e #3 insieme
    public void scrivi_DataOutputStream(){
        try(DataOutputStream dataOut = new DataOutputStream(new FileOutputStream("user.csv"));
            DataInputStream dataIn = new DataInputStream(new FileInputStream("user.json"))){
            //scrittura in output.csv con dataOutputStream
            dataOut.writeInt(1);
            dataOut.writeUTF("Alessandro");
            dataOut.writeDouble(4.25);
            //lettura users.json
            int id = dataIn.readInt();
            String nome = dataIn.readUTF();
            double voto = dataIn.readDouble();
        }catch(IOException ex){
            System.out.println("eccezione catturata: " + ex);
        }
        
    }
}
