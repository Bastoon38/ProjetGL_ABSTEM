import javax.swing.*;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.*;

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
    private JLabel lab_recette;
    private JPanel pan_cmdGeneral1;
    private JButton COMMANDERButton;
    private JButton MISEAJOURDESButton;
    private JPanel pan_cmdGeneral2;
    private JPanel pan_produitACommander;
    private JPanel pan_boutonProduitCommander;
    private JPanel pan_produitAttendu;
    private JPanel pan_boutonRecpetionProduit;
    private JPanel pan_paramSeuil;
    private JComboBox cmb_jour;
    private JComboBox cmb_heure;
    private JComboBox cmb_minute;
    private JButton btn_paramOk;
    private JButton btn_paramDefaut;
    private JLabel lab_paramJour;
    private JLabel lab_paramHeure;
    private JLabel lab_paramMinute;
    private JPasswordField passwordField1;
    private JPasswordField psw_nouvMotDePasse;
    private JPasswordField psw_confirmeChgt;
    private JButton btn_okChangementMotDePasse;
    private JPasswordField psw_ancienMDP;


    private String motDePasse = "Manager";


    public  Interface_manager()
    {
        /*class JPasswordField2 implements ActionListener {

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

        }*/

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1200,900));

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

        btn_okChangementMotDePasse.addActionListener(new ActionListener() {
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


        btn_paramDefaut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "bouton param defaut cliqué", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // MISE EN PLACE DES PREMIERES PAGE POUR LE DEMARRAGE (SANS AVOIR A CLIQUER DESSUS)

        //Les données du tableau pour la page parametre seuil
        Object[][] data = {
                {"BAGUETTE", "28", "34"},
                {"FLUTE", "28", "12"},
                {"CROISSANT", "24", "72"},
                {"PAIN AU CHOCOLAT", "32", "21"}
        };

        //Les titres des colonnes
        String  title[] = {"PRODUITS", "SEUIL", "TAILLE FOURNEE"};
        JTable tab_paramSeuil = new JTable(data, title);
        tab_paramSeuil.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_paramSeuil.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_paramSeuil);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_paramSeuil = new JScrollPane(tab_paramSeuil);
        pan_paramSeuil.add(tbc_paramSeuil, BorderLayout.CENTER);
        lab_paramJour.setFont(new Font("Serif", Font.PLAIN, 30));
        lab_paramHeure.setFont(new Font("Serif", Font.PLAIN, 30));
        lab_paramMinute.setFont(new Font("Serif", Font.PLAIN, 30));
        String[] str_jour = {"LUNDI","MARDI","MERCREDI","JEUDI","VENDREDI","SAMEDI","DIMANCHE"};
        String[] str_heure = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
        String[] str_minute = {"00","30"};
        for (int i=0; i<7 ; i++)
            cmb_jour.addItem(str_jour[i]);
        for (int i=0; i<24 ; i++)
            cmb_heure.addItem(str_heure[i]);
        for (int i=0; i<2 ; i++)
            cmb_minute.addItem(str_minute[i]);
    }



    public void changeMdp(){
        // TODO: mettre les fonctions de liaison avec la BDD
        // TODO: mettre le test pour savoir si ca c'est bien passé
        JOptionPane.showMessageDialog(null, "Nouveau mot de passe enregistré", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void vueSynthSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Vue synthetique selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void bilanJournSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Bilan journalier selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        //Les données du tableau
        Object[][] data = {
                {"200 BAGUETTE", "28 BAGUETTE"},
                {"50 FLUTE", "28 FLUTE"},
                {"17 CROISSANT", "24 CROISSANT"},
                {"54 PAIN AU CHOCOLAT", "32 PAIN AU CHOCOLAT"}
        };

        //Les titres des colonnes
        String  title[] = {"VENTES", "JETES"};
        JTable tab_bilan = new JTable(data, title);
        tab_bilan.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_bilan.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_bilan);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_bilan = new JScrollPane(tab_bilan);
        pan_bilanJournalier.add(tbc_bilan, BorderLayout.CENTER);
        lab_recette.setText("RECETTE = 13976 €");
        lab_recette.setFont(new Font("Serif", Font.BOLD, 23));
        pan_bilanJournalier.add(lab_recette, BorderLayout.SOUTH);
    }


    public void commandeSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Commandes selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        //Les données du tableau
        Object[][] data = {
                {"200 BAGUETTE", "28 BAGUETTE"},
                {"50 FLUTE", "28 FLUTE"},
                {"17 CROISSANT", "24 CROISSANT"},
                {"54 PAIN AU CHOCOLAT", "32 PAIN AU CHOCOLAT"}
        };

        //Les titres des colonnes
        String  title[] = {"VENTES", "JETES"};
        JTable tab_bilan = new JTable(data, title);
        tab_bilan.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_bilan.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_bilan);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_bilan = new JScrollPane(tab_bilan);
        pan_produitACommander.add(tbc_bilan, BorderLayout.CENTER);


        //Les données du tableau
        Object[][] dataAttente = {
                {"200 BAGUETTE", "28 BAGUETTE"},
                {"50 FLUTE", "28 FLUTE"},
                {"17 CROISSANT", "24 CROISSANT"},
                {"54 PAIN AU CHOCOLAT", "32 PAIN AU CHOCOLAT"}
        };

        //Les titres des colonnes
        String  titleAttente[] = {"VENTES", "JETES"};
        JTable tab_bilanAttente = new JTable(data, titleAttente);
        tab_bilanAttente.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_bilanAttente.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_bilanAttente);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_bilanAttente = new JScrollPane(tab_bilanAttente);
        pan_produitAttendu.add(tbc_bilanAttente, BorderLayout.CENTER);
    }

    public void parametreSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Parametres selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void cuissonSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Cuisson selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
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
        JTable tab_vitrine = new JTable(data, title);
        tab_vitrine.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_vitrine.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_vitrine);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_vitrine = new JScrollPane(tab_vitrine);
        pan_vitrine.add(tbc_vitrine, BorderLayout.CENTER);
    }

    public void stockSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Stock selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        GestionBDD baseDonnee =  new GestionBDD();
        Produit[] tabStock = null;

        tabStock = baseDonnee.getStock();

        System.out.print(tabStock[0].getQuantite());

        //Les données du tableau
        Object[][] data = {
                {tabStock[0].getNom(), tabStock[0].getQuantite()},
                {"FLUTE", "28"},
                {"CROISSANT", "24"},
                {"PAIN AU CHOCOLAT", "32"}
        };

        //Les titres des colonnes
        String  title[] = {"PRODUIT", "STOCK"};
        JTable tab_stock = new JTable(data, title);
        tab_stock.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_stock.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_stock);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_stock = new JScrollPane(tab_stock);
        pan_stock.add(tbc_stock, BorderLayout.CENTER);
    }

    public void paramSeuilSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Parametre seuil selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        //Les données du tableau
        Object[][] data = {
                {"BAGUETTE", "28", "34"},
                {"FLUTE", "28", "12"},
                {"CROISSANT", "24", "72"},
                {"PAIN AU CHOCOLAT", "32", "21"}
        };

        //Les titres des colonnes
        String  title[] = {"PRODUITS", "SEUIL", "TAILLE FOURNEE"};
        JTable tab_paramSeuil = new JTable(data, title);
        tab_paramSeuil.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_paramSeuil.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_paramSeuil);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_paramSeuil = new JScrollPane(tab_paramSeuil);
        pan_paramSeuil.add(tbc_paramSeuil, BorderLayout.CENTER);
        lab_paramJour.setFont(new Font("Serif", Font.PLAIN, 30));
        lab_paramHeure.setFont(new Font("Serif", Font.PLAIN, 30));
        lab_paramMinute.setFont(new Font("Serif", Font.PLAIN, 30));
    }

    public void paramMdpSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Parametre MDP selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void paramPrixSelect(){
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Parametre prix selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        //Les données du tableau
        Object[][] data = {
                {"BAGUETTE", "0.70"},
                {"FLUTE", "0.90"},
                {"CROISSANT", "0.90"},
                {"PAIN AU CHOCOLAT", "1.00"}
        };

        //Les titres des colonnes
        String  title[] = {"PRODUIT", "PRIX"};
        JTable tab_paramPrix = new JTable(data, title);
        tab_paramPrix.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_paramPrix.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_paramPrix);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_paramPrix = new JScrollPane(tab_paramPrix);
        pan_paramPrix.add(tbc_paramPrix, BorderLayout.CENTER);
    }

    private void updateRowHeights(JTable table)
    {
        try
        {
            for (int row = 0; row < table.getRowCount(); row++)
            {
                int rowHeight = table.getRowHeight();

                for (int column = 0; column < table.getColumnCount(); column++)
                {
                    Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                    rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                }

                table.setRowHeight(row, rowHeight);
            }
        }
        catch(ClassCastException e) {}
    }
}