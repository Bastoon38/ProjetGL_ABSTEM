import java.util.*;
import java.util.TimerTask;


public class TimerBDD {
    private int heure, minutes;


    public void TimerStock () {
        // Vérification des dates de péremption
        Timer timer2 = new Timer();   // crée timer
        System.out.println("Lancement de la vérification péremption du stock toutes les 1h");

        timer2.schedule(new TimerTask() {   // Lance une tâche répétitive
            public void run() {     // Lance une tâche répétitive
                Date maDate = new Date();
                System.out.println("Tâche vérification péremption stock lancée le " + maDate.toString());
                Interface_cuisson intCuisson = new Interface_cuisson();
                intCuisson.timer_refresh_produits_jeter();  // Vérifie la péremption de la BDD stock
            }
        }, (long) 0, (long) ((1 * 60) * 60000)); // 0 est le délai avant de commencer, le deuxième est la durée en millisecondes (ici 1h)
    }


    public void TimerVitrine () {
        // Vérification des dates de péremption
        Timer timer2 = new Timer();   // crée timer
        System.out.println("Lancement de la vérification péremption de la vitrine toutes les 2 min");

        timer2.schedule(new TimerTask() {
            public void run() {
                Date maDate = new Date();
                System.out.println("Tâche vérification péremption vitrine lancée le " + maDate.toString());
                Interface_vendeur intVendeur = new Interface_vendeur();
                intVendeur.timer_refresh_produits_jeter();  // Vérifie la péremption de la BDD vitrine
            }
        }, (long) 0, (long) (2 * 60000) ); // 0 est le délai avant de commencer, le deuxième est la durée en millisecondes (ici 2 min)
    }
}