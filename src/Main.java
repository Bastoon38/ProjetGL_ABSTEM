import java.util.Hashtable;
import java.util.Timer;

/**
 * Created by Bastien on 29/11/2014.
 */
public class Main {

    public static void main(String[] args) {
        // IL FAUT COMMENTER LES IHM QUE VOUS NE VOULEZ PAS AFFICHER ET TESTER
 
       //Interface_manager ihm = new Interface_manager();
        //ihm.setVisible(true);
        //Interface_vendeur frame = new Interface_vendeur();
        //frame.setVisible(true);

        GestionBDD bdd = new GestionBDD();
        bdd.resetBilan();
        //Elio Test Vendeur
        // ihm_billet billet = new ihm_billet();
        //billet.setVisible(true);
        //Interface_vendeur frame = new Interface_vendeur();
        //Vendeur vendeur= new Vendeur(frame);

        /*Connexion frame1 = new Connexion();
        frame1.setVisible(true);

        Timer timer = new Timer();
        timer.schedule(new TimerMajCuisson(), 0, 1800000);*/
    }
}
