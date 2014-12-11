/**
 * Created by Angèle on 09/12/2014.
 */
/*import java.util.Date;
//import java.lang.Object;
//import java.util.Timer;
import java.util.TimerTask;
//import java.util.*;

public class Timer {

    public static void main (int annee, int mois, int jour, int heure, int minutes) {

        if ((mois == 0) && (jour == 0)) {
            chrono (heure, minutes);
        }
        else {
            peremption (annee, mois, jour, heure, minutes);
        }
    }

    public static boolean chrono (int heure, int minutes) {
        Timer timer1 = new Timer();   // crée timer
        //TimerTask task = new TimerTask();
        timer1.schedule(new TimerTask() {
            public void run()
            {
                // System.out.printf ("tache lancee\n");
            }
        }, (long)0, (long)((heure/60)+minutes)/60000);
        // 0 est le délai avant de commencer, le deuxième est la durée en millisecondes

        // Produit devenu périmé

        return true;
    }

    public static void peremption (int annee, int mois, int jour, int heure, int minutes) {
        @SuppressWarnings("deprecation")
        Date dateFin = new Date(annee, mois, jour, heure, minutes);
        Timer timer = new Timer();// crée timer

        timer.schedule(new TimerTask() {
            public void run()
            {
                // System.out.printf ("tache lancee\n");
            }
        }, dateFin);

        // Produit devenu périmé
        int idProdPerime = getId();
        if (idProdPerime.lieu == "Stock"){		// id.lieu ou id.getLieu ??
            GestionBDD.supprimerStock (idProdPerime);
        }
        if (idProdPerime.lieu == "Cuisson"){
            String produit;
            GestionBDD.supprimerCuisson(produit);
        }
        if (idProdPerime.lieu == "Stock"){
            Vendeur.supprimerProduit (idProdPerime);
        }
    }

}
*/
