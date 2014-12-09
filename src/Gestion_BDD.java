
import java.sql.*;

public class Gestion_BDD {

	public Gestion_BDD(){

	}

	//verifie la péremption des produits en vitrine
	public void verifVitrine(String nom_prod) {
			System.out.println("test");
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
}
