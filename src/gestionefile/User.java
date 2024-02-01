/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionefile;

/**
 *
 * @author lorir
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    public int username;
    public String password;
    public ArrayList<User> users = new ArrayList<>();

    public User(int username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void scriviOggetto(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("Oggetto scritto con successo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leggiOggetto(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            User user = (User) ois.readObject();
            this.username = user.username;
            this.password = user.password;
            this.users = user.users;
            System.out.println("Oggetto letto con successo");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
