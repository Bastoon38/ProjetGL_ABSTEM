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
    private int id;
    private int cuisson;
    private boolean flag;
    private int tpsCuisson;
    private int tailleFournee;
    private  String jour;
    private String heure;
    private int seuil_ini;
    private int seuil;
    private int fournee_ini;
    private int fournee;

    public Produit(String obj_name,  float credit, int quant, Date date) {

        this.nom = obj_name;
        this.prix= credit;
        this.quantite =quant;
        this.date=date;
        //  this.time=time;
    }

    public Produit(String obj_name, int quant, int cuisson, int ID) {

        this.nom = obj_name;
        this.cuisson= cuisson;
        this.quantite =quant;
        this.id=id;
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

    public Produit(String obj_name, int quant, boolean flag) {

        this.nom = obj_name;
        this.quantite = quant;
        this.flag = flag;
    }

    public Produit(String obj_name, float prix, int tpsCuisson, int tailleFournee) {

        this.nom = obj_name;
        this.prix = prix;
        this.tpsCuisson = tpsCuisson;
        this.tailleFournee = tailleFournee;
    }

    public Produit(String obj_name, String jour,String heure, int seuil_ini, int seuil, int fournee_ini, int fournee) {

        this.nom = obj_name;
        this.jour = jour;
        this.heure = heure;
        this.seuil_ini = seuil_ini;
        this.seuil=seuil;
        this.fournee_ini = fournee_ini;
        this.fournee = fournee;
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
    public boolean getFlag() {return this.flag;}
    public int getTpsCuisson()
    {
        return this.tpsCuisson;
    }
    public int getTailleFournee() {return this.tailleFournee;}
    public String getJour() {return this.jour;}
    public String getHeure() {return this.heure;}
    public int getSeuilIni() {return this.seuil_ini;}
    public int getSeuil() {return this.seuil;}
    public int getFourneeIni() {return this.fournee_ini;}
    public int getFournee() {return this.fournee;}

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
    public void setFlag(boolean flag)
    {
        this.flag=flag;
    }
    public void setTpsCuisson(int tpsCuisson)
    {
        this.tpsCuisson=tpsCuisson;
    }
    public void setTailleFournee(int tailleFournee)
    {
        this.tailleFournee=tailleFournee;
    }
    public void setSeuil(int seuil)
    {
        this.seuil=seuil;
    }
    public void setFournee(int fournee)
    {
        this.fournee=fournee;
    }

}
