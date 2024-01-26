package gestionefile;

/**
 *
 * @author MC
 * @version 12/01/23
 */
import java.util.Scanner;
public class GestioneFile {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //1)LETTURA
        Lettore lettore = new Lettore("user.json");
        lettore.start();
        //2)ELABORAZIONE
        //dichiarazione oggetto scanner
        Scanner scanner = new Scanner(System.in);
        //INPUT credenziali utente
        System.out.println("Inserisci il nome utente: ");
        String username = scanner.nextLine();
        System.out.println("Inserisci la password: ");
        String password = scanner.nextLine();
        scanner.close();
        //3)SCRITTURA
        Scrittore scrittore = new Scrittore("output.csv", username, password);
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
        
    }
}
