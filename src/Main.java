import java.util.Hashtable;

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
        //Connexion frame1 = new Connexion();
        //frame1.setVisible(true);
        GestionBDD bdd = new GestionBDD();
        System.out.println(bdd.supprimerStock("Baguette",500));
    }
}
