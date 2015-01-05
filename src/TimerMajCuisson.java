import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Bastien on 03/01/2015.
 */
class TimerMajCuisson extends TimerTask {

    public void run() {

        GestionBDD baseDonnee = new GestionBDD();

        DateTimeFormatter jourformatter = DateTimeFormatter.ofPattern("EEEE");
        DateTimeFormatter heureformatter = DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter minuteformatter = DateTimeFormatter.ofPattern("mm");

        LocalDateTime today = LocalDateTime.now();
        String jour = jourformatter.format(today).toUpperCase();
        String heure = heureformatter.format(today);
        String minute = minuteformatter.format(today);

        int int_heure = Integer.parseInt(heure);
        int int_minute = Integer.parseInt(minute);

        System.out.println("jour = " + jour);
        System.out.println("heure = " + int_heure + ":" + int_minute);

        if (int_minute> 0 && int_minute <30)
            minute = "00";
        else if (int_minute>30 && int_minute<=59)
            minute = "30";
        else if (int_minute == 0)
            minute = "00";
        else if (int_minute == 30)
            minute = "30";

        String heureFinal = heure+"."+minute;
        System.out.println("heureFinal = " +heureFinal);

        Produit[] tabSeuil = null;
        Produit[] tabVitrine = null;

        tabSeuil = baseDonnee.getSeuil(jour,heureFinal);
        tabVitrine = baseDonnee.getVitrine();

        LinkedList tab;
        tab = setAfficheTab(tabVitrine,tabVitrine.length);

        for (int i=0 ; i<tabSeuil.length;i++) {
            System.out.println("dans la boucle, nom = " + tabSeuil[i].getNom());
            int seuil = tabSeuil[i].getSeuil();
            System.out.println("seuil = " + seuil);
            int vitrine = (Integer) tab.get(i);
            System.out.println("vitrine = " + vitrine);
            if (vitrine < seuil)
            {
                baseDonnee.ajouterCuisson(tabSeuil[i].getNom(), tabSeuil[i].getFournee());
                baseDonnee.supprimerStock(tabSeuil[i].getNom(), tabSeuil[i].getFournee());
            }
        }

        if ( int_heure == 0 && int_minute == 0)
            baseDonnee.resetBilan();
    }



    private LinkedList setAfficheTab(Produit[] tab, int taille) {
        LinkedList<Integer> tabAffich = new LinkedList<Integer>();

        System.out.println(taille);

        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);

        for (int i = 0; i < taille; i++) {
            if (tab[i].getNom().equals("BAGUETTE"))
            {
                System.out.println("dans baguette");
                tabAffich.set(0, tabAffich.get(0) + tab[i].getQuantite());
            }

            else if (tab[i].getNom().equals("FLUTE"))
                tabAffich.set(5, tabAffich.get(5) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("CROISSANT"))
                tabAffich.set(3, tabAffich.get(3) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("PAIN AU CHOCOLAT"))
                tabAffich.set(10, tabAffich.get(10) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("BRIOCHE SUCRE"))
                tabAffich.set(1, tabAffich.get(1) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("PAIN AU LAIT"))
                tabAffich.set(11, tabAffich.get(11) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("TARTE CITRON"))
                tabAffich.set(13, tabAffich.get(13) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("TARTE PRALINE"))
                tabAffich.set(14, tabAffich.get(14) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("COCA COLA"))
                tabAffich.set(2, tabAffich.get(2) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("FANTA"))
                tabAffich.set(4, tabAffich.get(4) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("SPRITE"))
                tabAffich.set(12, tabAffich.get(12) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("OASIS"))
                tabAffich.set(9, tabAffich.get(9) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("ORANGE"))
                tabAffich.set(6, tabAffich.get(6) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("POMME"))
                tabAffich.set(7, tabAffich.get(7) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("RAISIN"))
                tabAffich.set(8, tabAffich.get(8) + tab[i].getQuantite());
        }
        System.out.print(tabAffich);
        return tabAffich;
    }
}



