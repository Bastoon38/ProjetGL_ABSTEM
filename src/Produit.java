/**
 * Created by Elio on 11/12/2014.
 */
import java.text.DecimalFormat;

public class Produit {


    public String nom;
    public int prix=0;
    public int quantite=0; //utile pour le panier

    public Produit(String obj_name,  int credit, int quant) {

        this.nom = obj_name;
        this.prix= credit;
        this.quantite = 0;
    }

    public float getPrix()
    {
        return this.prix;
    }

}
