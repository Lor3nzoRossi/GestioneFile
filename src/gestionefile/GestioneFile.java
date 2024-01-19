package gestionefile;

/**
 *
 * @author MC
 * @version 12/01/23
 */
import java.util.Scanner;
import jdk.nashorn.internal.codegen.CompilerConstants;
public class GestioneFile {
    public static String nomeUtente;
    public static String password;
    public static void elaborazione(){
        //dichiarazione oggetto scanner
        Scanner scanner = new Scanner(System.in);
        //INPUT UTENTE
        System.out.println("Inserisci il nome utente: ");
        nomeUtente = scanner.nextLine();
        System.out.println("Inserisci la password: ");
        password = scanner.nextLine();
        
        scanner.close();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //1)LETTURA
        Lettore lettore = new Lettore("user.json");
        lettore.start();
        //2)ELABORAZIONE
        elaborazione();
        //3) SCRITTURA
        Scrittore scrittore = new Scrittore("output.csv");
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
    }
}
