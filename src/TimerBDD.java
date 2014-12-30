import java.util.*;
import java.util.TimerTask;


public class TimerBDD {
    private String lieu = new String();
    private int annee, mois, jour, heure, minutes;

    public TimerBDD (String lieu1, int annee1, int mois1, int jour1, int heure1, int minutes1) {
        lieu = lieu1;
        annee = annee1;
        mois = mois1;
        jour = jour1;
        heure = heure1;
        minutes = minutes1;

        if ((annee == 0) && (mois == 0) && (jour == 0)) {   // Cas de durée à chronométrer (cuisson)
            final Timer timer1 = new Timer();   // crée timer
            System.out.println("Lancement d'une cuisson de " + heure + "h et " + minutes +"min");
            Date DateChrono = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateChrono);
            cal.add(Calendar.HOUR, heure);  // Ajoute à la date courante prélevée, le nombre d'heures à chronométrer
            cal.add(Calendar.MINUTE, minutes);  // Ajoute à la date courante prélevée, le nombre de minutes à chronométrer
            System.out.println("Date courante: " + DateChrono + "\nDate de fin du chrono: " + cal.getTime());
            timer1.schedule(new TimerTask() {
                public void run() {
                    System.out.println("Tâche chrono finie -> Produit cuit");
                    timer1.cancel();    // Stoppe la tâche
                    timer1.purge();     // Supprime la tâche
                    System.out.println("Tâche chrono supprimée");
                    GestionBDD bdd = new GestionBDD();
                    bdd.lancerTimerPerime();
                    // supprimeProduit(Cuisson,...);
                    // ajouteProduit(Vitrine, ...);
                }
            }, cal.getTime());
        }

        else {  // Vérification des dates de péremption
            Timer timer2 = new Timer();   // crée timer
            System.out.println("Lancement de la vérification péremption toutes les 1h pour le stock et toutes les 5min pour la vitrine");

            timer2.schedule(new TimerTask() {
                public void run() {
                    Date maDate = new Date();
                    System.out.println("Tâche vérification péremption stock à lancer le " + maDate.toString());
                }
            }, (long) 0, (long) ((1 * 60) * 60000));
            // 0 est le délai avant de commencer, le deuxième est la durée en millisecondes (ici 1h)

            timer2.schedule(new TimerTask() {
                public void run() {
                    Date maDate = new Date();
                    System.out.println("Tâche vérification péremption vitrine à lancer le " + maDate.toString());

                    if (lieu == "Vitrine") {
                        // verifPeremptVitrine();  OU  verifPerempt(lieu); sans les if
                        System.out.println("Vérification Vitrine");
                    }
                    if (lieu == "Stock") {
                        System.out.println("Vérification Stock");
                    }
                }
            }, (long) 0, (long) (5 * 60000) );
            // 0 est le délai avant de commencer, le deuxième est la durée en millisecondes (ici 5min)
        }
    }
}
