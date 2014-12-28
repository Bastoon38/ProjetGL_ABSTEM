/**
 * Created by Elio on 11/12/2014.
 */
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.text.DecimalFormat;
import  java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;

public class Produit {


    private String nom;
    private float prix=0;
    private int quantite=0; //utile pour le panier
    private Date date;
    private Time time;
    private int perime;
    private int vendu;
    private int jete;
    private boolean cuisson;

    public Produit(String obj_name,  float credit, int quant, Date date) {

        this.nom = obj_name;
        this.prix= credit;
        this.quantite =quant;
        this.date=date;
        //  this.time=time;
    }
    public Produit(String obj_name,  float credit, int quant, Date date,Time time, int perime) {

        this.nom = obj_name;
        this.prix= credit;
        this.quantite =quant;
        this.date=date;
        this.time=time;
        this.perime=perime;
    }
    public Produit(String obj_name, int vendu, int jete) {

        this.nom = obj_name;
        this.vendu = vendu;
        this.jete = jete;
    }

    public Produit(String obj_name, int quant, boolean cuisson) {

        this.nom = obj_name;
        this.quantite = quant;
        this.cuisson = cuisson;
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
    public Date getDate()
    {
        return this.date;
    }
    public Time getTime()
    {
        return this.time;
    }
    public int getPerime()
    {
        return this.perime;
    }
    public int getVendu()
    {
        return this.vendu;
    }
    public int getJete()
    {
        return this.jete;
    }
    public boolean getCuisson()
    {
        return this.cuisson;
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
    public void setDate(Date date)
    {
        this.date=date;
    }
    public void setTime(Time time)
    {
        this.time=time;;
    }
    public void setPerime(int perime)
    {
        this.perime=perime;
    }
    public void setVendu(int vendu)
    {
        this.vendu=vendu;
    }
    public void setJete(int jete)
    {
        this.jete=jete;
    }
    public void setCuisson(boolean cuisson)
    {
        this.cuisson=cuisson;
    }
}
