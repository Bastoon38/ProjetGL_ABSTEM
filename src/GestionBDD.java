
import com.mysql.jdbc.CommunicationsException;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.net.ConnectException;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.Vector;
import java.util.*;



public class GestionBDD {


	//connexion à la base de données
	private Connection connexion() {
		try {
			String url = "jdbc:mysql://localhost/albm_dev";
			String login = "root";
			String password = "";
			Connection con = DriverManager.getConnection(url,login,password);
			return con;
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
			return null;
		}
	}




	//GETTER ****************************************
	//GETTER ****************************************
	public Vector<Produit> getVitrine(Vector<Produit> vitrine) {


		Connection con = connexion();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM vitrine,produit WHERE vitrine.produit = produit.produit");

			while (rs.next()) {
				String nom = rs.getString("PRODUIT");
				int quantite = rs.getInt("QUANTITE");
				Date date = rs.getDate("DATE_PEREMPTION");
				Time time =rs.getTime("DATE_PEREMPTION");
				int perime = rs.getInt("PERIME");
				float prix = rs.getFloat("PRIX");
				int id = rs.getInt("ID");

				Produit aux=new Produit(id,nom,prix,quantite,date,time,perime);
				vitrine.add(aux);

			}





			rs.close();
			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}

		return vitrine;
	}

	public Produit[] getVitrine() {
		Connection con = connexion();

		Produit[] vitrine = null;
		int rows = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM vitrine,produit WHERE vitrine.produit = produit.produit ");
			while (rs.next()) {
				if (rs.last()) {
					rows = rs.getRow();
					// Move to beginning
					rs.beforeFirst();
					break;
				}
			}
			vitrine = new Produit[rows];
			int j = 0;

			while (rs.next()) {
				String nom = rs.getString("PRODUIT");
				int quantite = rs.getInt("QUANTITE");
				//Timestamp timestamp = rs.getTimestamp("DATE_PEREMPTION");
				Date date = rs.getDate("DATE_PEREMPTION");
				Time time =rs.getTime("DATE_PEREMPTION");
				int perime = rs.getInt("PERIME");
				float prix = rs.getFloat("PRIX");

				//String date = String.format("%1$TD %1$TT", timestamp);

				vitrine[j] = new Produit(nom,prix,quantite,date,time,perime);
				j++;
			}

			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}
		return vitrine;
	}

	public Produit[] getStock() {


		Connection con = connexion();

		Produit[] stock = null;
		int rows = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `stock`");
			while (rs.next()) {
				if (rs.last()) {
					rows = rs.getRow();
					// Move to beginning
					rs.beforeFirst();
					break;
				}
			}
			stock = new Produit[rows];
			int j = 0;
			float prix = 0;
			while (rs.next()) {
				String nom = rs.getString("PRODUIT");
				int quantite = rs.getInt("QUANTITE");
				Timestamp timestamp = rs.getTimestamp("DATE_PEREMPTION");
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("SELECT `PRIX` FROM `produit`");
				if (rs2.next()) {
					prix = rs2.getFloat("PRIX");
				}

				stock[j] = new Produit(nom,prix,quantite,timestamp);
				j++;
				rs2.close();
				stmt2.close();
			}

			rs.close();
			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}

		return stock;
	}

	public String getDatePerimeBoisson(String nom) {
		Connection con = connexion();

		String timestamp = null;
		int rows = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `DATE_PEREMPTION` FROM `stock` WHERE `PRODUIT` = '"+ nom +"' ORDER BY `DATE_PEREMPTION` ASC LIMIT 1");
			while (rs.next()) {
				if (rs.last()) {
					rows = rs.getRow();
					// Move to beginning
					rs.beforeFirst();
					break;
				}
			}
			while (rs.next()) {
				timestamp = rs.getString("DATE_PEREMPTION");
				Statement stmt2 = con.createStatement();
				stmt2.close();
			}

			rs.close();
			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}

		return timestamp;
	}

	public Produit[] getBilan() {

		Connection con = connexion();

		Produit[] bilan = null;
		int rows = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `bilan`");
			while (rs.next()) {
				if (rs.last()) {
					rows = rs.getRow();
					// Move to beginning
					rs.beforeFirst();
					break;
				}
			}
			bilan = new Produit[rows];
			int j = 0;
			while (rs.next()) {
				String nom = rs.getString("PRODUIT");
				int vendu = rs.getInt("VENDU");
				int jete = rs.getInt("JETE");
				bilan[j] = new Produit(nom,vendu,jete);
				j++;
			}

			rs.close();
			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}

		return bilan;
	}
	//GETTER ****************************************

	//récupère le temps de cuisson pour un tel produit + le temps de cuisson restant si une cuisson est déjà lancée
	public int getTime_cuisson(String nom, int id) {

		int temps=0;
		int tps_cuisson=0;
		Time time = null;
		Connection con = connexion();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `TEMPS CUISSON` from `produit` where `PRODUIT`= '"+ nom +"'");

			while (rs.next()) {
				tps_cuisson = rs.getInt("TEMPS CUISSON");
			}


			rs = stmt.executeQuery("SELECT * from `four` where `PRODUIT`= '"+ nom +"' AND CUISSON=1 AND ID=" + id + " LIMIT 1");
			if (rs.next()) {
				time = rs.getTime("TIME_LANCEMENT");
			} else {
				return tps_cuisson;
			}


			java.util.Date d = new java.util.Date();
			long now = d.getTime();
			Time t2 = new Time(now - time.getTime());
			temps=tps_cuisson-t2.getMinutes();
			temps=temps*60-t2.getSeconds();//retourne les secs

			rs.close();
			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}

		return temps;
	}

	public Vector<Produit> getFour(Vector<Produit>four) {

		Connection con = connexion();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `four`");


			while (rs.next()) {
				String nom = rs.getString("PRODUIT");
				int quant = rs.getInt("QUANTITE");
				int cuisson = rs.getInt("CUISSON");
				int id = rs.getInt("ID");
				four.add( new Produit(nom,quant,cuisson,id));

			}

			rs.close();
			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}

		return four;
	}
	public Produit[] getFour() {

		Connection con = connexion();

		Produit[] four = null;
		int rows = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `four`");
			while (rs.next()) {
				if (rs.last()) {
					rows = rs.getRow();
					// Move to beginning
					rs.beforeFirst();
					break;
				}
			}
			four = new Produit[rows];
			int j = 0;
			while (rs.next()) {
				String nom = rs.getString("PRODUIT");
				int quant = rs.getInt("QUANTITE");
				boolean cuisson = rs.getBoolean("CUISSON");
				four[j] = new Produit(nom,quant,cuisson);
				j++;
			}

			rs.close();
			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}

		return four;
	}

	public Produit[] getCommande() {

		Connection con = connexion();

		Produit[] commande = null;
		int rows = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `commande`");
			while (rs.next()) {
				if (rs.last()) {
					rows = rs.getRow();
					// Move to beginning
					rs.beforeFirst();
					break;
				}
			}
			commande = new Produit[rows];
			int j = 0;
			while (rs.next()) {
				String nom = rs.getString("PRODUIT");
				int quant = rs.getInt("QUANTITE");
				boolean recu = rs.getBoolean("RECU");
				commande[j] = new Produit(nom,quant,recu);
				j++;
			}

			rs.close();
			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}
		return commande;
	}

	public Produit[] getProduit() {

		Connection con = connexion();

		Produit[] produit = null;
		int rows = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `produit`");
			while (rs.next()) {
				if (rs.last()) {
					rows = rs.getRow();
					// Move to beginning
					rs.beforeFirst();
					break;
				}
			}
			produit = new Produit[rows];
			int j = 0;
			while (rs.next()) {
				String nom = rs.getString("PRODUIT");
				float prix = rs.getFloat("PRIX");
				int tpsCuisson = rs.getInt("TEMPS CUISSON");
				int tailleCmd = rs.getInt("TAILLE COMMANDE");
				produit[j] = new Produit(nom,prix,tpsCuisson,tailleCmd);
				j++;
			}

			rs.close();
			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}
		return produit;
	}


	public float getRecette() {

		Connection con = connexion();

		float total = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `bilan`");

			while (rs.next()) {
				String nom = rs.getString("PRODUIT");
				float vendu = rs.getInt("VENDU");
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("SELECT PRIX FROM `produit` WHERE PRODUIT='" + nom + "'");
				float prix=0;
				while (rs2.next()) {
					prix = rs2.getFloat("PRIX");
				}
				total = total + (vendu*prix);
			}



			rs.close();
			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}
		return total;
	}


	public String getMdp() {

		Connection con = connexion();

		String mdp = null;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `mot_de_passe`");

			while (rs.next()) {
				mdp = rs.getString("Valeur");
			}
			rs.close();
			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return mdp;
	}

	public String setMdp(String nom) {

		try{
			Connection con = connexion();
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE `mot_de_passe` SET `Valeur`='"+nom+"' WHERE 1");
			stmt.close();
			con.close();
			//JOptionPane.showMessageDialog(null, "Mot de passe modifié avec succès !", "SAISIE REUSSIE", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(SQLException sqle){
			return sqle.getMessage();
		}
		return "Mot de passe modifié avec succès !";
	}

	public Produit[] getSeuil(String jour, String heure) {

		Connection con = connexion();

		Produit[] seuil = null;
		int rows = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `param_seuil` WHERE`JOUR`='"+jour+"' AND `HEURE`='"+heure+"'");
			while (rs.next()) {
				if (rs.last()) {
					rows = rs.getRow();
					// Move to beginning
					rs.beforeFirst();
					break;
				}
			}
			seuil = new Produit[rows];
			int j = 0;
			while (rs.next()) {
				String name = rs.getString("PRODUIT");
				String jour1 = rs.getString("JOUR");
				String heure1 = rs.getString("HEURE");
				int seuil_ini = rs.getInt("SEUIL_INI");
				int seuil1 = rs.getInt("SEUIL");
				int fournee_ini = rs.getInt("FOURNEE_INI");
				int fournee = rs.getInt("FOURNEE");
				seuil[j] = new Produit(name,jour1,heure1,seuil_ini,seuil1,fournee_ini,fournee);
				j++;
			}

			rs.close();
			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}
		return seuil;
	}

	public void majSeuil(Object nom, String jour, String heure ,Object seuil, Object fournee) {

		try{
			Connection con = connexion();
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE `param_seuil` SET `SEUIL`='"+seuil+"' WHERE `PRODUIT`='" + nom + "' AND `JOUR`='" + jour + "' AND `HEURE`=" + heure + "");
			stmt.executeUpdate("UPDATE `param_seuil` SET `FOURNEE`='"+fournee+"' WHERE `PRODUIT`='" + nom + "' AND `JOUR`='" + jour + "' AND `HEURE`=" + heure + "");
			stmt.close();
			con.close();
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}

	public void supprimerCommande(Object nom, Object qte) {

		Connection con = connexion();

		int id = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `ID` FROM `commande` WHERE`PRODUIT`='"+nom+"' AND `QUANTITE`='"+qte+"'");

			while (rs.next()) {
				id = rs.getInt("ID");
			}

			rs.close();
			stmt.close();
			//con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}


		try{
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM `commande` WHERE `ID`='" + id + "' AND `PRODUIT`='" + nom + "' AND `QUANTITE`='" + qte + "'");

			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	//AJOUT & SUPPR ****************************************
	//STOCK ****************************************
	public String ajouterStock(String nom, int quantite, String datePeremption) {	// Ajoute tjrs à la fin de la BDD
		String ret = "Ajout du stock finie";

		try{

			Connection con = connexion();
			Statement stmt = con.createStatement();

			stmt.executeUpdate("INSERT INTO `stock`(`PRODUIT`,`QUANTITE`,`DATE_PEREMPTION`) VALUES ('" + nom + "'," + quantite + ",'" + datePeremption + "')");

			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			return sqle.getMessage();
		}

		return ret;
	}

	//mise en cuisson du produit, suppression du stock
	public String supprimerStock(String nom, int quantite) {

		String ret = "Suppression du stock finie";

		try{

			Connection con = connexion();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `QUANTITE` FROM `stock` WHERE `PRODUIT`='" + nom + "' ORDER BY `DATE_PEREMPTION`");

			if (rs.next()) {
				int qteStock = rs.getInt("QUANTITE");

				if (qteStock > quantite) {

					int val = qteStock - quantite;
					stmt.executeUpdate("UPDATE `stock` SET `QUANTITE`=" + val + " WHERE `PRODUIT`='" + nom + "' ORDER BY `DATE_PEREMPTION` LIMIT 1");

				} else if (qteStock == quantite) {
					stmt.executeUpdate("DELETE FROM `stock` WHERE `PRODUIT`='" + nom + "' ORDER BY `DATE_PEREMPTION` LIMIT 1");
				} else { // qteStock < quantite
					Statement stmt2 = con.createStatement();
					stmt2.executeUpdate("DELETE FROM `stock` WHERE `PRODUIT`='" + nom + "' AND `QUANTITE`=" + qteStock + " ORDER BY `DATE_PEREMPTION` LIMIT 1");
					int qteDiff = quantite - qteStock; //on récupère la différence, en positif
					/* Ancienne version avant le récursif, à garder au cas ou
					if (rs.next()) {
						int val = rs.getInt("QUANTITE") - qte1;
						stmt.executeUpdate("UPDATE `stock` SET `QUANTITE`=" + String.valueOf(val) + " WHERE `PRODUIT`='" + nom + "' ORDER BY `DATE_PEREMPTION` LIMIT 1");
					} else {
						ret = "Il manque " + qte1 + " " + nom + " au stock";
					}
					*/
					stmt2.close();
					ret = supprimerStock(nom,qteDiff);
				}
			} else {

				ret = "Il manque " + quantite + " " + nom + " au stock";
			}

			rs.close();
			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			return sqle.getMessage();
		}

		return ret;
	}

	//AJOUT & SUPPR & MAJ CUISSON ****************************************
	public String ajouterCuisson(String nom, int quantite) {
		String ret = "Ajout de la cuisson complété";

		try{

			Connection con = connexion();
			Statement stmt = con.createStatement();

			stmt.executeUpdate("INSERT INTO `four`(`PRODUIT`,`QUANTITE`,`CUISSON`,`TIME_LANCEMENT`) VALUES ('" + nom +"'," + String.valueOf(quantite) + ",0,NULL)");

			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			return sqle.getMessage();
		}

		return ret;
	}

	//pour le psg en vitrine
	public void supprimerCuisson(int id) {
		try{

			Connection con = connexion();
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM `four` WHERE `ID`='" + id + "' LIMIT 1");

			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}

	//mise à jour d'un produit en "attente de cuisson" à "en cuisson"
	public void majCuisson(String nom, String qte, int id) {

		try{

			Connection con = connexion();
			Statement stmt = con.createStatement();

			System.out.println(nom);System.out.println(qte);
			java.util.Date date = new java.util.Date();
			Time time = new Time(date.getTime());
			stmt.executeUpdate("UPDATE `four` SET `CUISSON`=1,TIME_LANCEMENT='" + time + "' WHERE `PRODUIT`='" + nom + "' AND `QUANTITE`=" + qte + " AND ID=" + id + " LIMIT 1");


			stmt.close();
			con.close();
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}

	//AJOUT & SUPPR VITRINE ****************************************
	//Ajout d'un produit cuisson finie en vitrine
	public void ajouterVitrine (String nom, int quantite, String date) {

		try{
			Connection con = connexion();
			Statement stmt = con.createStatement();

			stmt.executeUpdate("INSERT INTO `vitrine`(`PRODUIT`,`QUANTITE`,`DATE_PEREMPTION`,`PERIME`) VALUES ('" + nom + "','" + quantite + "','" + date + "','" + 0 + "')");

			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}

	public String supprimerVitrine(String nom, int quantite) {
		String ret = "Suppression vitrine finie";

		try{

			//Class.forName("com.mysql.jdbc.Driver");

			Connection con = connexion();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `QUANTITE` FROM `vitrine` WHERE `PRODUIT`='" + nom + "' AND `PERIME`=0 ORDER BY `DATE_PEREMPTION` LIMIT 1");

			if (rs.next()) {
				int qteVit = rs.getInt("QUANTITE");

				if (qteVit > quantite) {

					int val = qteVit - quantite;
					stmt.executeUpdate("UPDATE `vitrine` SET `QUANTITE`=" + val + " WHERE `PRODUIT`='" + nom + "' AND `PERIME`=0 ORDER BY `DATE_PEREMPTION` LIMIT 1");

				} else if (qteVit == quantite) {
					stmt.executeUpdate("DELETE FROM `vitrine` WHERE `PRODUIT`='" + nom + "' AND `PERIME`=0 ORDER BY `DATE_PEREMPTION` LIMIT 1");
				} else { // qteStock < quantite
					Statement stmt2 = con.createStatement();
					stmt2.executeUpdate("DELETE FROM `vitrine` WHERE `PRODUIT`='" + nom + "' AND `QUANTITE`=" + qteVit + " AND `PERIME`=0 ORDER BY `DATE_PEREMPTION` LIMIT 1");
					int qteDiff = quantite - qteVit; //on récupère la différence, en positif, du reste à enlever
					stmt2.close();
					ret=supprimerVitrine(nom, qteDiff);
				}
			} else {

				ret = "Il manque " + quantite + " " + nom + " dans la vitrine";
			}

			rs.close();
			stmt.close();
			con.close();


		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			return sqle.getMessage();
		}
		return ret;
	}


	public void majPerime(int id) {
		Connection con = connexion();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE `vitrine` SET `PERIME`=1 WHERE `ID`=" + id);

			stmt.close();
			con.close();
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void supprimerPerime(String nom, int quantite) {

		try{

			//Class.forName("com.mysql.jdbc.Driver");

			Connection con = connexion();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `QUANTITE` FROM `vitrine` WHERE `PRODUIT`='" + nom + "' AND `PERIME`=1 ORDER BY `DATE_PEREMPTION` LIMIT 1");

			if (rs.next()) {
				int qteVit = rs.getInt("QUANTITE");

				if (qteVit > quantite) {

					int val = qteVit - quantite;
					stmt.executeUpdate("UPDATE `vitrine` SET `QUANTITE`=" + val + " WHERE `PRODUIT`='" + nom + "' AND `PERIME`=1 ORDER BY `DATE_PEREMPTION` LIMIT 1");

				} else if (qteVit == quantite) {
					stmt.executeUpdate("DELETE FROM `vitrine` WHERE `PRODUIT`='" + nom + "' AND `PERIME`=1 ORDER BY `DATE_PEREMPTION` LIMIT 1");
				} else { // qteStock < quantite
					Statement stmt2 = con.createStatement();
					stmt2.executeUpdate("DELETE FROM `vitrine` WHERE `PRODUIT`='" + nom + "' AND `QUANTITE`=" + qteVit + " AND `PERIME`=1 ORDER BY `DATE_PEREMPTION` LIMIT 1");
					int qteDiff = quantite - qteVit; //on récupère la différence, en positif, du reste à enlever
					stmt2.close();
					supprimerVitrine(nom, qteDiff);
				}
			}

			rs.close();
			stmt.close();
			con.close();


		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}


	// Ajouter un produit vendu dans le bilan du manager
	public void ajouterBilan(String nom, int quantite) {
		Connection con = connexion();
		int qte=0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `VENDU` FROM `bilan` WHERE `PRODUIT`='" + nom + "'");
			if (rs.next()) {
				qte=rs.getInt("VENDU");
			}
			Statement stmt2 = con.createStatement();
			int qteFinale=qte+quantite;
			stmt2.executeUpdate("UPDATE `bilan` SET `VENDU`=" + qteFinale + " WHERE `PRODUIT`='" + nom + "'");
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}

	}
	// Ajouter un produit perime dans le bilan du manager
	public void ajouterPerime(String nom, int quantite) {
		Connection con = connexion();
		int qte=0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `JETE` FROM `bilan` WHERE `PRODUIT`='" + nom + "'");
			if (rs.next()) {
				qte=rs.getInt("JETE");
			}
			Statement stmt2 = con.createStatement();
			int qteFinale=qte+quantite;
			stmt2.executeUpdate("UPDATE `bilan` SET `JETE`=" + qteFinale + " WHERE `PRODUIT`='" + nom + "'");
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	public void majPrix(Object nom, Object nouveauPrix) {
		Connection con = connexion();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE `produit` SET `PRIX`=" + nouveauPrix + " WHERE `PRODUIT`='" + nom + "'");
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	// Valeurs des fournées remplacées par celles par défaut /!\ ATTENTION CECI EST POUR TOUTES LES VALEURS
	public void majProductionsDefaut () {
		Connection con = connexion();
		try {
			Statement stmt = con.createStatement();
			Statement stmt2 = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `param_seuil`");
			while (rs.next()) {
				String prod = rs.getString(1);
				String jour = rs.getString(2);
				String heure = rs.getString(3);
				int fournee = rs.getInt("FOURNEE_INI");
				stmt2.executeUpdate("UPDATE `param_seuil` SET `FOURNEE`=" + fournee + " WHERE `PRODUIT`='" + prod + "' AND `JOUR`='" + jour + "' AND `HEURE`='" + heure + "'");
			}

		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void majProductionDefaut (String prod, String jour, String heure) {
		Connection con = connexion();
		try {
			Statement stmt = con.createStatement();
			Statement stmt2 = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT FOURNEE_INI FROM `param_seuil` WHERE `PRODUIT`='" + prod + "' AND `JOUR`='" + jour + "' AND `HEURE`='" + heure + "'");
			while (rs.next()) {
				int fournee = rs.getInt(1);
				stmt2.executeUpdate("UPDATE `param_seuil` SET `FOURNEE`=" + fournee + " WHERE `PRODUIT`='" + prod + "' AND `JOUR`='" + jour + "' AND `HEURE`='" + heure + "'");
			}

		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}


	public void ajouterCommandeFournisseur(Object nom, Object quantite) {
		//int quantite = getQuantiteParametree(nom);

		try{

			Connection con = connexion();
			Statement stmt = con.createStatement();

			stmt.executeUpdate("INSERT INTO `commande`(`PRODUIT`,`QUANTITE`,`RECU`) VALUES ('" + nom +"'," + String.valueOf(quantite) + ",0)");

			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}

	public void majCommande(String nom, int qte, int flag) {

		try{
			Connection con = connexion();
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE `commande` SET `RECU`='" +flag+"' WHERE `PRODUIT`='" + nom + "' AND `QUANTITE`=" + qte + " LIMIT 1");

			stmt.close();
			con.close();
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}

	public void resetBilan() {
		Connection con = connexion();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE `bilan` SET `VENDU`=0, `JETE`=0");

			stmt.close();
			con.close();
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}

}