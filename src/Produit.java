/**
 * Created by Elio on 11/12/2014.
 */
import java.text.DecimalFormat;
import  java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;

public class Produit {


    public String nom;
    public float prix=0;
    public int quantite=0; //utile pour le panier
    public String date;
    public int perime;

    public Produit(String obj_name,  float credit, int quant, String date) {

        this.nom = obj_name;
        this.prix= credit;
        this.quantite =quant;
        this.date=date;
        //  this.time=time;
    }
    public Produit(String obj_name,  float credit, int quant, String date, int perime) {

        this.nom = obj_name;
        this.prix= credit;
        this.quantite =quant;
        this.date=date;
        this.perime=perime;
    }
    //get Methods
    public String getNom()
    {
        return this.nom;
    }
    public float getPrix()
    {
        return this.prix;
    }
    public int getQuantite()
    {
        return this.quantite;
    }
    public String getDate()
    {
        return this.date;
    }
    public int getPerime()
    {
        return this.perime;
    }
    // set Methods
    public void setNom(String nom)
    {
        this.nom=nom;
    }
    public void setPrix(float prix)
    {
        this.prix=prix;
    }
    public void setQuantite(int quant)
    {
        this.quantite=quant;
    }
    public void setDate(String date)
    {
        this.date=date;
    }
    public void setPerime(int perime)
    {
        this.perime=perime;
    }

}
