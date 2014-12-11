/**
 * Created by Angèle on 09/12/2014.
 */
import java.util.*;
import java.util.TimerTask;
//import java.util.*;

/*
public class TimerBDD {

    public static void main (String lieu, int annee, int mois, int jour, int heure, int minutes) {

        if ((mois == 0) && (jour == 0)) {
            chrono (heure, minutes);
        }
        else {
            peremption (lieu, annee, mois, jour, heure, minutes);
        }
    }

    public static boolean chrono (int heure, int minutes) {
        TimerBDD timer1 = new TimerBDD();   // crée timer
        //TimerTask task = new TimerTask();
        timer1.schedule(new TimerTask() {
            public void run()
            {
                System.out.printf ("Tâche chrono lancée\n");
            }
        }, (long)0, (long)((heure/60)+minutes)/60000);
        // 0 est le délai avant de commencer, le deuxième est la durée en millisecondes

        return true;    // Produit devenu périmé
    }

    public static void peremption (String lieu, int annee, int mois, int jour, int heure, int minutes) {
        //@SuppressWarnings("deprecation")
        Date dateFin = new Date(annee, mois, jour, heure, minutes);
        Timer timer = new Timer();// crée timer

        timer.schedule(new TimerTask() {
            public void run()
            {
                 System.out.printf ("Tâche peremption lancée\n");
            }
        }, dateFin);

        System.out.printf ("Tâche peremption finie\n");

        // Produit devenu périmé
        if (lieu == "Stock"){
            GestionBDD.supprimerPerime ("Stock");
        }

        if (lieu == "Vitrine"){
            GestionBDD.supprimerPerime ("Vitrine");
        }
    }
} */