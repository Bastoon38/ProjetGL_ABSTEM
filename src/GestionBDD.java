
import java.sql.*;
import java.util.Date;

public class GestionBDD {

	public void verifVitrine(String nom_prod) {

	}

	public void ajouterStock (String nom, int quantité, Date datePeremption) {	// Ajoute tjrs à la fin de la BDD

	}

	public void supprimerStock(String nom, int quantite) {	// Prend toujours ceux au début de la BDD (les + vieux)
		String url = "localhost";
		String login = "root";
		String password = "root";
		try{

			//Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(url,login,password);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `QUANTITE` FROM Stocks WHERE `PRODUIT`='" + nom + "'");

			if (rs.getInt("QUANTITE") > quantite) {
				rs.updateInt("QUANTITE",rs.getInt("QUANTITE")-quantite);
			}
			else if (rs.getInt("QUANTITE") == quantite) {
				rs.deleteRow();
			}
			if (rs.getInt("QUANTITE") < quantite) {

				rs.updateInt("QUANTITE",rs.getInt("QUANTITE")-quantite);
			}
			con.commit();

			rs.close();
			stmt.close();
			con.close();

		}
		catch(SQLException sqle){
			System.out.print(sqle.getMessage());
		}
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


}
