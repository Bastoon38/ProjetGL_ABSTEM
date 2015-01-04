import java.util.*;
import java.util.TimerTask;


public class TimerBDD {
    private int heure, minutes;


    public TimerBDD (int heure1, int minutes1) {
        heure = heure1;
        minutes = minutes1;

        final Timer timer1 = new Timer();   // crée timer
        System.out.println("Lancement d'une cuisson de " + heure + "h et " + minutes + "min");
        Date DateChrono = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateChrono);
        cal.add(Calendar.HOUR, heure);  // Ajoute à la date courante prélevée, le nombre d'heures à chronométrer
        cal.add(Calendar.MINUTE, minutes);  // Ajoute à la date courante prélevée, le nombre de minutes à chronométrer
        System.out.println("Date courante: " + DateChrono + "\nDate de fin du chrono: " + cal.getTime());
        timer1.schedule(new TimerTask() {       // Lance une tâche non répétitive
            public void run() {
                System.out.println("Tâche chrono finie -> Produit cuit");
                timer1.cancel();    // Stoppe la tâche
                timer1.purge();     // Supprime la tâche
                System.out.println("Tâche chrono supprimée");
                //GestionBDD bdd = new GestionBDD();
                // supprimeProduit(Cuisson,...);
                // ajouteProduit(Vitrine, ...);
            }
        }, cal.getTime());
    }


    public TimerBDD () {
        // Vérification des dates de péremption
        Timer timer2 = new Timer();   // crée timer
        System.out.println("Lancement de la vérification péremption toutes les 1h pour le stock et toutes les 2 min pour la vitrine");

        timer2.schedule(new TimerTask() {   // Lance une tâche répétitive
            public void run() {
                Date maDate = new Date();
                System.out.println("Tâche vérification péremption stock lancée le " + maDate.toString());
                GestionBDD bdd = new GestionBDD();
                bdd.verifPerime("Stock");
            }
        }, (long) 0, (long) ((1 * 60) * 60000));    // Lance une tâche répétitive
        // 0 est le délai avant de commencer, le deuxième est la durée en millisecondes (ici 1h)

        /*timer2.schedule(new TimerTask() {
            public void run() {
                Date maDate = new Date();
                System.out.println("Tâche vérification péremption vitrine lancée le " + maDate.toString());
                //GestionBDD bdd = new GestionBDD();
                //bdd.verifPerime(lieu);
            }
        }, (long) 0, (long) (2 * 60000) );*/
        // 0 est le délai avant de commencer, le deuxième est la durée en millisecondes (ici 2 min)
    }
}