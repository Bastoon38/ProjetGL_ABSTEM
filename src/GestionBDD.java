
import java.sql.*;
import java.util.Date;

public class GestionBDD {

	public void verifVitrine(String nom_prod) {

	}

	public void ajouterStock (String nom, int quantité, Date datePeremption) {	// Ajoute tjrs à la fin de la BDD

	}

	//mise en cuisson du produit, suppression du stock
	public String supprimerStock(String nom, int quantite) {
		String url = "jdbc:mysql://localhost/albm_dev";
		String login = "root";
		String password = "root";
		String ret = "Suppression du stock finie";

		try{

			//Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(url,login,password);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `QUANTITE` FROM Stock WHERE `PRODUIT`='" + nom + "'");


			rs.next();
			int qteStock = rs.getInt("QUANTITE");

			if (qteStock > quantite) {

				int val = qteStock - quantite;
				Statement stmt2 = con.createStatement();
				int res = stmt.executeUpdate("UPDATE `Stock` SET `QUANTITE`=" + String.valueOf(val) +" WHERE `PRODUIT`='" + nom + "'");
			}
			else if (qteStock == quantite) {
				rs.deleteRow();
			}
			if (qteStock < quantite) {

				int qte1 = quantite-qteStock; //on récupère la différence, en positif
				rs.deleteRow();
				if (rs.next()) {
					int val = rs.getInt("QUANTITE") - qte1;
					Statement stmt2 = con.createStatement();
					int res = stmt.executeUpdate("UPDATE `Stock` SET `QUANTITE`=" + String.valueOf(val) +" WHERE `PRODUIT`='" + nom + "'");
				} else {
					ret = "Il manque " + qte1 + " " + nom + " au stock";
				}

			}

			rs.close();
			stmt.close();
			con.close();



		}
		catch(SQLException sqle){
			return sqle.getMessage();
		}

		return ret;
	}

	public void ajouterStock(String nom, int quantite) {

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

	public void ajouterCuisson (String nom) {

	}

	public void supprimerCuisson (String nom) {

	}

	public void supprimerPerime (String lieu) {	// Le timer a détecté au moins un produit périmé, donc vérifie peremption de tous les produits de la BDD concernée
												// lieu = "Stock" ou "Vitrine"
	}

}
