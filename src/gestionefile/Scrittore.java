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
        try (BufferedWriter br = new BufferedWriter(new FileWriter(this.nomeFile))) {//1) apro il file
            //2) scrivo nel buffer
            br.write("File in output");
            br.newLine(); 
            br.write("<username: " + this.username + "><password: " + this.password + ">");
            //3) svuoto il buffer e salvo nel file i dati
            br.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void copia() {
        //apro il file in lettura
        try (BufferedReader brCopia = new BufferedReader(new FileReader("user.json"));
            //apro il file in scrittura 
            BufferedWriter bwCopia = new BufferedWriter(new FileWriter("copia.csv", true))) {
            
            //copio riga per riga
            String line;
            while ((line = brCopia.readLine()) != null) {
                bwCopia.write(line);
                bwCopia.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    //punto #2 e #3 insieme
    public void scrivi_DataOutputStream(){
        try(DataOutputStream dataOut = new DataOutputStream(new FileOutputStream("user.csv"))){
            //scrittura in output.csv con dataOutputStream
            dataOut.writeInt(1);
            dataOut.writeUTF("Alessandro");
            dataOut.writeDouble(4.25);
        }catch(IOException ex){
            System.out.println("eccezione catturata durante la scrittura: " + ex);
        }
        try(DataInputStream dataIn = new DataInputStream(new FileInputStream("user.csv"))){
            //lettura users.json
            int id = dataIn.readInt();
            String nome = dataIn.readUTF();
            double voto = dataIn.readDouble();
            
            //stampa dei dati letti
            System.out.println("i dati letti sono: " + id + "; " + nome + "; " + voto);
        } catch (IOException ex) {
            System.out.println("eccezione catturata durante la lettura: " + ex);
        }
    }
}
