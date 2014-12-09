
import java.sql.*;

public class GestionBDD {

	public void verifVitrine(String nom_prod) {

	}

	public void supprimerStock(String nom, int quantite) {
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
}
