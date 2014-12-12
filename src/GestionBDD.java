
import java.sql.*;
import java.sql.Date;
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

	public Produit[] recupStockVitrine() {


		Connection con = connexion();

		Produit[] vitrine = null;
		int rows = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `vitrine`");
			while (rs.next()) {
				if (rs.last()) {
					rows = rs.getRow();
					// Move to beginning
					rs.beforeFirst();
					break;
				}
			}
			System.out.println(rows);
			vitrine = new Produit[rows];
			int j = 0;
			float prix = 0;
			while (rs.next()) {
				String nom = rs.getString("PRODUIT");
				int quantite = rs.getInt("QUANTITE");
				Date date = rs.getDate("DATE_PEREMPTION");
				int perime = rs.getInt("PERIME");

				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("SELECT `PRIX` FROM `produit`");
				if (rs2.next()) {
					prix = rs2.getFloat("PRIX");
				}

				vitrine[j] = new Produit(nom,prix,quantite,perime,date);
				j++;
			}

		}
		catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		}

		return vitrine;
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

	public static void supprimerPerime (String lieu) {	// Le timer a détecté au moins un produit périmé, donc vérifie peremption de tous les produits de la BDD concernée
												// lieu = "Stock" ou "Vitrine"  Cuisson n'est pas concerné par la péremption
	}

	// Rajouter les méthodes pour gérer l'affichage du bilan du manager

}
