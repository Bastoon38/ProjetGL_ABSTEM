import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.SampleModel;

import java.net.URL;

/**
 * Created by Bastien on 29/11/2014.
 */
public class Interface_manager extends JFrame {
    private JTabbedPane tbp_prinCat;
    private JPanel test;
    private JPanel test2;
    private JTabbedPane tbp_vueSynthetique;
    private JPanel pan_vueSynthetique;
    private JPanel pan_commande;
    private JPanel pan_parametres;
    private JPanel pan_bilanJournalier;
    private JPanel pan_cuisson;
    private JPanel pan_vitrine;
    private JPanel pan_stock;
    private JTabbedPane tbp_parametre;
    private JPanel pan_parametreSeuil;
    private JPanel pan_paramMdp;
    private JPanel pan_paramPrix;
    private JButton btn_chgMdp;


    private String motDePasse = "Manager";


    public  Interface_manager()
    {
        class JPasswordField2 implements ActionListener {

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
                int comparaison = String.copyValueOf(passwordField1.getPassword()).compareToIgnoreCase(motDePasse);    // String.valueOf(this.motDePasse)
                if(comparaison == 0) {	// Si le mot de passe est le bon

                }
                // System.out.println("texte saisie = " + String.copyValueOf(passwordField1.getPassword()));
            }

        }





        add(tbp_prinCat);
        setVisible(true);

        ClassLoader classLoader = getClass().getClassLoader();
        URL file = classLoader.getResource("images/param64.png");
        Icon icn_param = new ImageIcon(file);
        tbp_prinCat.setIconAt(3,icn_param);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/cmd64.png");
        Icon icn_cmd = new ImageIcon(file);
        tbp_prinCat.setIconAt(2,icn_cmd);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/bilan64.png");
        Icon icn_bilan = new ImageIcon(file);
        tbp_prinCat.setIconAt(1,icn_bilan);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/vue64.png");
        Icon icn_vue = new ImageIcon(file);
        tbp_prinCat.setIconAt(0,icn_vue);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/cuisson48.png");
        Icon icn_cuisson = new ImageIcon(file);
        tbp_vueSynthetique.setIconAt(0,icn_cuisson);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/vitrine48.png");
        Icon icn_vitrine = new ImageIcon(file);
        tbp_vueSynthetique.setIconAt(1,icn_vitrine);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/stock48.png");
        Icon icn_stock = new ImageIcon(file);
        tbp_vueSynthetique.setIconAt(2,icn_stock);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/graph48.png");
        Icon icn_seuil = new ImageIcon(file);
        tbp_parametre.setIconAt(0,icn_seuil);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/mdp48.png");
        Icon icn_mdp = new ImageIcon(file);
        tbp_parametre.setIconAt(1,icn_mdp);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/price48.png");
        Icon icn_prix = new ImageIcon(file);
        tbp_parametre.setIconAt(2,icn_prix);

        btn_chgMdp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeMdp();
            }
        });

        tbp_prinCat.setSelectedIndex(0);
        tbp_prinCat.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (tbp_prinCat.getSelectedIndex()){
                    case 0: vueSynthSelect();
                        break;
                    case 1: bilanJournSelect();
                        break;
                    case 2: commandeSelect();
                        break;
                    case 3: parametreSelect();
                        break;
                }
            }
        });

        tbp_vueSynthetique.setSelectedIndex(0);
        tbp_vueSynthetique.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (tbp_vueSynthetique.getSelectedIndex()){
                    case 0: cuissonSelect();
                        break;
                    case 1: vitrineSelect();
                        break;
                    case 2: stockSelect();
                        break;
                }
            }
        });

        tbp_parametre.setSelectedIndex(0);
        tbp_parametre.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (tbp_parametre.getSelectedIndex()){
                    case 0: paramSeuilSelect();
                        break;
                    case 1: paramMdpSelect();
                        break;
                    case 2: paramPrixSelect();
                        break;
                }
            }
        });

        //private void createUIComponents() {
        // TODO: place custom component creation code here
    //}
    }


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
        }



    public void changeMdp(){
        // TODO: mettre les fonctions de liaison avec la BDD
        // TODO: mettre le test pour savoir si ca c'est bien passé
        JOptionPane.showMessageDialog(null, "Nouveau mot de passe enregistré", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void vueSynthSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        JOptionPane.showMessageDialog(null, "Vue synthetique selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void bilanJournSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        JOptionPane.showMessageDialog(null, "Bilan journalier selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void commandeSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        JOptionPane.showMessageDialog(null, "Commandes selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void parametreSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        JOptionPane.showMessageDialog(null, "Parametres selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void cuissonSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        JOptionPane.showMessageDialog(null, "Cuisson selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void vitrineSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Vitrine selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        //Les données du tableau
        Object[][] data = {
                {"BAGUETTE", "28"},
                {"FLUTE", "28"},
                {"CROISSANT", "24"},
                {"PAIN AU CHOCOLAT", "32"}
        };

        //Les titres des colonnes
        String  title[] = {"PRODUIT", "NOMBRE EN VITRINE"};
        JTable tableau = new JTable(data, title);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tableContainer = new JScrollPane(tableau);
        pan_vitrine.add(tableContainer, BorderLayout.CENTER);
    }

    public void stockSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        JOptionPane.showMessageDialog(null, "Stock selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void paramSeuilSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        JOptionPane.showMessageDialog(null, "Parametre seuil selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void paramMdpSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        JOptionPane.showMessageDialog(null, "Parametre MDP selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void paramPrixSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        JOptionPane.showMessageDialog(null, "Parametre prix selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }




}