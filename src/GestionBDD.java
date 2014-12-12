
import java.sql.*;
import java.util.Date;
import java.util.*;

public class GestionBDD {

	//connexion à la base de données
	private Connection connexion() {
		try {
			String url = "jdbc:mysql://localhost/albm_dev";
			String login = "root";
			String password = "root";
			Connection con = DriverManager.getConnection(url,login,password);
			return con;
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
			return null;
		}
	}

	//verif les produits périmés en vitrine
	public void verifVitrine(String nom_prod) {

	}

	public void recupStockVitrine() {


		Connection con = connexion();

		String[][] tab = new String[][]{};


		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `vitrine`");

			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println("querying SELECT * FROM XXX");
			int columnsNumber = rsmd.getColumnCount();
			int j = 0;
			while (rs.next()) {
				for (int i = 2; i <= columnsNumber; i++) {
					String columnValue = rs.getString(i);
					hs.put("ok","ok"); //rsmd.getColumnName(i));
				}
				System.out.println("");
			}

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}
	}

	public void ajouterStock (String nom, int quantité, Date datePeremption) {	// Ajoute tjrs à la fin de la BDD

	}

	//mise en cuisson du produit, suppression du stock
	public String supprimerStock(String nom, int quantite) {

		String ret = "Suppression du stock finie";

		try{

			//Class.forName("com.mysql.jdbc.Driver");

			Connection con = connexion();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `QUANTITE` FROM Stock WHERE `PRODUIT`='" + nom + "'");

			rs.next();
			int qteStock = rs.getInt("QUANTITE");

			if (qteStock > quantite) {

				int val = qteStock - quantite;
				int res = stmt.executeUpdate("UPDATE `Stock` SET `QUANTITE`=" + String.valueOf(val) +" WHERE `PRODUIT`='" + nom + "'");

			}
			else if (qteStock == quantite) {
				int res = stmt.executeUpdate("DELETE FROM `Stock` WHERE `PRODUIT`='" + nom + "'");
			}
			else { // qteStock < quantite
				Statement stmt2 = con.createStatement();
				int res = stmt2.executeUpdate("DELETE FROM `Stock` WHERE `PRODUIT`='" + nom + "' AND `QUANTITE`=" + qteStock);
				int qte1 = quantite-qteStock; //on récupère la différence, en positif
				if (rs.next()) {
					int val = rs.getInt("QUANTITE") - qte1;
					res = stmt.executeUpdate("UPDATE `Stock` SET `QUANTITE`=" + String.valueOf(val) +" WHERE `PRODUIT`='" + nom + "'");
				} else {
					ret = "Il manque " + qte1 + " " + nom + " au stock";
				}

			}

			stmt.close();
			con.close();



		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			return sqle.getMessage();
		}

		return ret;
	}



	public void ajouterVitrine (String nom, int quantite) {

	}

	public void supprimerVitrine (String nom, int quantite) {
		boolean fini = false;
		do{
		//	fini = Vendeur.supprimerProduit (nom, quantite);           // enlever le commentaire quand vendeur sera ajouté
		} while (fini != true);
	}

	public void ajouterBilan (String nom, int quantite) {	// Ajouter un produit vendu dans le bilan du manager

	}

	public void majPrix (String nom, float nouveauPrix) {

	}

	public void majProductionDefaut () {	// Valeurs des fournées remplacées par celles par défaut

	}

	public void majProduction () {	// Valeurs des fournées modifiées

	}

	public void ajouterCommandeFournisseur (String nom) {
		int quantite = getQuantiteParametree(nom);
		// Ajouter nom et quantite à la commande fournisseur
	}

	private int getQuantiteParametree(String nom) {
		int quantite = 0;
		return quantite;
	}

	public void supprimerCuisson (String nom) {

	}

	public String ajouterCuisson (String nom, int quantite) {
	String ret = "Ajout de la cuissie complétée";

		try{

			//Class.forName("com.mysql.jdbc.Driver");

			Connection con = connexion();
			Statement stmt = con.createStatement();

			int res = stmt.executeUpdate("INSERT INTO `four`(`PRODUIT`,`QUANTITE`,`CUISSON`) VALUES ('" + nom +"'," + String.valueOf(quantite) + ",0)");



			stmt.close();
			con.close();



		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			return sqle.getMessage();
		}

		return ret;
	}


}