/**
 * Created by Angèle on 09/12/2014.
 */

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;
//import java.util.Comparator;



public class Manager {

    public String motDePasse = "Manager";

    public class JPasswordField2 implements ActionListener {

        JPasswordField passwordField1 = null;

        public void Manager () {
            JPasswordField2 jpf2 = new JPasswordField2();
            jpf2.init();
        }

        public void init() {
            JFrame f = new JFrame("ma fenetre");
            f.setSize(300, 100);
            JPanel pannel = new JPanel();

            passwordField1 = new JPasswordField("");
            passwordField1.setPreferredSize(new Dimension(100, 20));
            pannel.add(passwordField1);

            JButton bouton1 = new JButton("Afficher");
            bouton1.addActionListener(this);

            pannel.add(bouton1);
            f.getContentPane().add(pannel);
            f.setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            int comparaison = String.copyValueOf(passwordField1.getPassword()).compareToIgnoreCase(String.valueOf(motDePasse));
            if(comparaison == 0) {	// Si le mot de passe est le bon

            }
            // System.out.println("texte saisie = " + String.copyValueOf(passwordField1.getPassword()));
            else{

            }
        }

    }

}

