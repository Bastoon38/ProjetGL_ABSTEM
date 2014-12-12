/**
 * Created by Elio on 11/12/2014.
 */
import java.text.DecimalFormat;
import  java.sql.Date;

public class Produit {


    private String nom;
    private float prix;
    private int quantite=0; //utile pour le panier
    private int perime=0;
    private Date dateperempt = getDate();

    public Produit (String nom) {
        this.nom=nom;
    }

    public Produit(String obj_name,  float credit, int quant, int perime, Date dateperempt) {

        this.nom = obj_name;
        this.prix= credit;
        this.quantite = quant;
        this.perime=perime;
        this.dateperempt = dateperempt;
    }

    public float getPrix()
    {
        return this.prix;
    }
    public int getQuantite()
    {
        return this.quantite;
    }
    
    public String getNom() {
        return this.nom;
    }

    public int getPerime() {
        return this.perime;
    }

    public Date getDate() {
        return this.dateperempt;
    }


    public void setPrix(int prix)
    {
        this.prix=prix;
    }
    public void setQuantite(int quantite)
    {
        this.quantite=quantite;
    }

    public void setNom(String nom) {
        this.nom=nom;
    }

    public void setPerime(int perime) {
        this.perime=perime;
    }

    public void setDate(Date date) {
        this.dateperempt=date;
    }


}
