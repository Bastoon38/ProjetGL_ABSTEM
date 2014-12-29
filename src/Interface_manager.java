import javax.swing.*;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
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
    private JButton btn_cmdProduit;
    private JButton btn_majStock;
    private JPanel pan_cmdGeneral2;
    private JPanel pan_produitACommander;
    private JPanel pan_produitAttendu;
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
    private JPanel pan_panelDeco;
    private JPanel pan_panel1;
    private JButton btn_managerDeco;
    private JTable tab_cmd;
    private JTable tab_att;


    private String motDePasse = "Manager";


    public Interface_manager() {
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
        setMinimumSize(new Dimension(1200, 900));

        add(pan_panel1);

        ClassLoader classLoader = getClass().getClassLoader();
        URL file = classLoader.getResource("images/param64.png");
        Icon icn_param = new ImageIcon(file);
        tbp_prinCat.setIconAt(3, icn_param);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/cmd64.png");
        Icon icn_cmd = new ImageIcon(file);
        tbp_prinCat.setIconAt(2, icn_cmd);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/bilan64.png");
        Icon icn_bilan = new ImageIcon(file);
        tbp_prinCat.setIconAt(1, icn_bilan);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/vue64.png");
        Icon icn_vue = new ImageIcon(file);
        tbp_prinCat.setIconAt(0, icn_vue);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/cuisson48.png");
        Icon icn_cuisson = new ImageIcon(file);
        tbp_vueSynthetique.setIconAt(0, icn_cuisson);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/vitrine48.png");
        Icon icn_vitrine = new ImageIcon(file);
        tbp_vueSynthetique.setIconAt(1, icn_vitrine);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/stock48.png");
        Icon icn_stock = new ImageIcon(file);
        tbp_vueSynthetique.setIconAt(2, icn_stock);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/graph48.png");
        Icon icn_seuil = new ImageIcon(file);
        tbp_parametre.setIconAt(0, icn_seuil);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/mdp48.png");
        Icon icn_mdp = new ImageIcon(file);
        tbp_parametre.setIconAt(1, icn_mdp);

        classLoader = getClass().getClassLoader();
        file = classLoader.getResource("images/price48.png");
        Icon icn_prix = new ImageIcon(file);
        tbp_parametre.setIconAt(2, icn_prix);

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
                switch (tbp_prinCat.getSelectedIndex()) {
                    case 0:
                        vueSynthSelect();
                        break;
                    case 1:
                        bilanJournSelect();
                        break;
                    case 2:
                        commandeSelect();
                        break;
                    case 3:
                        parametreSelect();
                        break;
                }
            }
        });

        tbp_vueSynthetique.setSelectedIndex(0);
        tbp_vueSynthetique.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (tbp_vueSynthetique.getSelectedIndex()) {
                    case 0:
                        cuissonSelect();
                        break;
                    case 1:
                        vitrineSelect();
                        break;
                    case 2:
                        stockSelect();
                        break;
                }
            }
        });

        tbp_parametre.setSelectedIndex(0);
        tbp_parametre.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (tbp_parametre.getSelectedIndex()) {
                    case 0:
                        paramSeuilSelect();
                        break;
                    case 1:
                        paramMdpSelect();
                        break;
                    case 2:
                        paramPrixSelect();
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

        btn_managerDeco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Connexion connexion = new Connexion();
                connexion.setVisible(true);
            }
        });


        // MISE EN PLACE DES PREMIERES PAGE POUR LE DEMARRAGE (SANS AVOIR A CLIQUER DESSUS)

        DefaultTableModel model = new DefaultTableModel()
        {
            //@Override
            //public boolean isCellEditable(int row, int column) {
            //    return column==2;
            //}

            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                if(columnIndex==2)
                    return Boolean.class;
                return super.getColumnClass(columnIndex);
            }
        };
        tab_cmd= new JTable(model);

        // Create a couple of columns
        model.addColumn("PRODUIT");
        model.addColumn("QUANTITE A COMMANDER");
        model.addColumn("BOOLEAN");

        // Append a row
            model.addRow(new Object[]{"BAGUETTE", 0, false});
            model.addRow(new Object[]{"FLUTE", 0, false});
            model.addRow(new Object[]{"CROISSANT", 0, false});
            model.addRow(new Object[]{"PAIN AU CHOCOLAT", 0, false});
            model.addRow(new Object[]{"BRIOCHE SUCRE", 0, false});
            model.addRow(new Object[]{"PAIN AU LAIT", 0, false});
            model.addRow(new Object[]{"TARTE AU CITRON", 0, false});
            model.addRow(new Object[]{"TARTE PRALINE", 0, false});
            model.addRow(new Object[]{"COCA-COLA", 0, false});
            model.addRow(new Object[]{"FANTA", 0, false});
            model.addRow(new Object[]{"SPRITE", 0, false});
            model.addRow(new Object[]{"OASIS", 0, false});
            model.addRow(new Object[]{"ORANGE", 0, false});
            model.addRow(new Object[]{"POMME", 0, false});
            model.addRow(new Object[]{"RAISIN", 0, false});

        tab_cmd.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_cmd.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_cmd);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_cmd = new JScrollPane(tab_cmd);
        pan_produitACommander.add(tbc_cmd, BorderLayout.CENTER);


        btn_cmdProduit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GestionBDD baseDonnee = new GestionBDD();

                for (int i=0; i<15 ; i++)
                {
                    if (tab_cmd.getValueAt(i,2).equals(true))
                        baseDonnee.ajouterCommandeFournisseur(tab_cmd.getValueAt(i,0), tab_cmd.getValueAt(i,1));
                }

                commandeSelect();
            }
        });

        btn_majStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Produit[] tabAttendu = null;

                GestionBDD baseDonnee = new GestionBDD();
                tabAttendu = baseDonnee.getCommande();

                for (int i=0; i<tabAttendu.length ; i++)
                {
                    if (tab_att.getValueAt(i,2).equals(true))
                        baseDonnee.majCommande(tabAttendu[i].getNom(),tabAttendu[i].getQuantite(),1);
                }

                tabAttendu = baseDonnee.getCommande();

                for (int i=0; i<tabAttendu.length ; i++)
                {
                    if (tabAttendu[i].getFlag() == true)
                    {
                        JPanel panel = new JPanel();
                        JLabel label = new JLabel("date (ex: 2014-12-29 17:10:04) :");
                        JTextField date = new JTextField(19);

                        panel.add(label);
                        panel.add(date);
                        String[] options = new String[]{"OK", "Cancel"};
                        int option = JOptionPane.showOptionDialog(null, panel, "Date de péremption : "+tabAttendu[i].getNom(),
                                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                null, options, options[0]);
                        if(option == 0) // pressing OK button
                        {
                            String dat = date.getText();
                            baseDonnee.ajouterStock(tabAttendu[i].getNom(), tabAttendu[i].getQuantite(),dat);
                        }
                    }

                }

                commandeSelect();
            }
        });

        /************************************************************************************/
        /****************** Les données du tableau pour la page parametre seuil *************/
        /************************************************************************************/
        Object[][] data2 = {
                {"BAGUETTE", "28", "34"},
                {"FLUTE", "28", "12"},
                {"CROISSANT", "24", "72"},
                {"PAIN AU CHOCOLAT", "32", "21"}
        };

        //Les titres des colonnes
        String title2[] = {"PRODUITS", "SEUIL", "TAILLE FOURNEE"};
        JTable tab_paramSeuil = new JTable(data2, title2);
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
        String[] str_jour = {"LUNDI", "MARDI", "MERCREDI", "JEUDI", "VENDREDI", "SAMEDI", "DIMANCHE"};
        String[] str_heure = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] str_minute = {"00", "30"};
        for (int i = 0; i < 7; i++)
            cmb_jour.addItem(str_jour[i]);
        for (int i = 0; i < 24; i++)
            cmb_heure.addItem(str_heure[i]);
        for (int i = 0; i < 2; i++)
            cmb_minute.addItem(str_minute[i]);

        cuissonSelect();
    }


    public void changeMdp() {
        // TODO: mettre les fonctions de liaison avec la BDD
        // TODO: mettre le test pour savoir si ca c'est bien passé
        JOptionPane.showMessageDialog(null, "Nouveau mot de passe enregistré", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void vueSynthSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Vue synthetique selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void bilanJournSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Bilan journalier selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        Produit[] tabBilan = null;

        GestionBDD baseDonnee = new GestionBDD();
        tabBilan = baseDonnee.getBilan();

        //Les données du tableau
        Object[][] data = {
                {tabBilan[0].getNom().toUpperCase(), tabBilan[0].getVendu(), tabBilan[0].getJete()},
                {tabBilan[1].getNom().toUpperCase(), tabBilan[1].getVendu(), tabBilan[1].getJete()},
                {tabBilan[2].getNom().toUpperCase(), tabBilan[2].getVendu(), tabBilan[2].getJete()},
                {tabBilan[3].getNom().toUpperCase(), tabBilan[3].getVendu(), tabBilan[3].getJete()},
                {tabBilan[4].getNom().toUpperCase(), tabBilan[4].getVendu(), tabBilan[4].getJete()},
                {tabBilan[5].getNom().toUpperCase(), tabBilan[5].getVendu(), tabBilan[5].getJete()},
                {tabBilan[6].getNom().toUpperCase(), tabBilan[6].getVendu(), tabBilan[6].getJete()},
                {tabBilan[7].getNom().toUpperCase(), tabBilan[7].getVendu(), tabBilan[7].getJete()},
                {tabBilan[8].getNom().toUpperCase(), tabBilan[8].getVendu(), tabBilan[8].getJete()},
                {tabBilan[9].getNom().toUpperCase(), tabBilan[9].getVendu(), tabBilan[9].getJete()},
                {tabBilan[10].getNom().toUpperCase(), tabBilan[10].getVendu(), tabBilan[10].getJete()},
                {tabBilan[11].getNom().toUpperCase(), tabBilan[11].getVendu(), tabBilan[11].getJete()},
                {tabBilan[12].getNom().toUpperCase(), tabBilan[12].getVendu(), tabBilan[12].getJete()},
                {tabBilan[13].getNom().toUpperCase(), tabBilan[13].getVendu(), tabBilan[13].getJete()},
                {tabBilan[14].getNom().toUpperCase(), tabBilan[14].getVendu(), tabBilan[14].getJete()},
        };

        //Les titres des colonnes
        String title[] = {"PRODUIT", "VENTES", "JETES"};
        JTable tab_bilan = new JTable(data, title);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tab_bilan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tab_bilan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tab_bilan.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        tab_bilan.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_bilan.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_bilan);
        tab_bilan.setEnabled(false);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_bilan = new JScrollPane(tab_bilan);
        pan_bilanJournalier.removeAll();
        pan_bilanJournalier.add(tbc_bilan, BorderLayout.CENTER);
        lab_recette.setText("RECETTE = 13976 €");
        lab_recette.setFont(new Font("Serif", Font.BOLD, 23));
        pan_bilanJournalier.add(lab_recette, BorderLayout.SOUTH);
    }

    public void commandeSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Commandes selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        Produit[] tabAtt = null;

        GestionBDD baseDonnee = new GestionBDD();
        tabAtt = baseDonnee.getCommande();

        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==2;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                if(columnIndex==2)
                    return Boolean.class;
                return super.getColumnClass(columnIndex);
            }
        };
        tab_att = new JTable(model);

        // Create a couple of columns
        model.addColumn("PRODUIT");
        model.addColumn("QUANTITE");
        model.addColumn("RECU");

        // Append a row
        for (int i = 0; i < tabAtt.length; i++)
                model.addRow(new Object[]{tabAtt[i].getNom().toUpperCase(), tabAtt[i].getQuantite(), tabAtt[i].getFlag()});


        //tab_att.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tab_att.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tab_att.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        tab_att.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_att.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_att);
        //tab_att.setEnabled(true);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_att = new JScrollPane(tab_att);
        if(pan_produitAttendu.getComponentCount() == 2)
            pan_produitAttendu.remove(1);
        pan_produitAttendu.add(tbc_att, BorderLayout.CENTER);
    }

    public void parametreSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Parametres selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void cuissonSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Cuisson selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        Produit[] tabCuisson = null;

        GestionBDD baseDonnee = new GestionBDD();
        tabCuisson = baseDonnee.getFour();

        DefaultTableModel model = new DefaultTableModel();
        JTable tab_cuisson = new JTable(model);

        // Create a couple of columns
        model.addColumn("PRODUIT");
        model.addColumn("QUANTITE");
        model.addColumn("CUISSON");

        // Append a row
        for (int i = 0; i < tabCuisson.length; i++) {
            if (tabCuisson[i].getFlag() == true)
                model.addRow(new Object[]{tabCuisson[i].getNom().toUpperCase(), tabCuisson[i].getQuantite(), "EN COURS"});
            else if (tabCuisson[i].getFlag() == false)
                model.addRow(new Object[]{tabCuisson[i].getNom().toUpperCase(), tabCuisson[i].getQuantite(), "EN ATTENTE"});
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tab_cuisson.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tab_cuisson.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tab_cuisson.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        tab_cuisson.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_cuisson.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_cuisson);
        tab_cuisson.setEnabled(false);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_cuisson = new JScrollPane(tab_cuisson);
        pan_cuisson.removeAll();
        pan_cuisson.add(tbc_cuisson, BorderLayout.CENTER);
    }

    public void vitrineSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Vitrine selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        Produit[] tabVitrine = null;
        LinkedList tab;

        GestionBDD baseDonnee = new GestionBDD();
        tabVitrine = baseDonnee.getVitrine();

        for (int i=0; i<tabVitrine.length;i++)
            System.out.println(tabVitrine[i].getQuantite());

        tab = setAfficheTab(tabVitrine, tabVitrine.length);

        //Les données du tableau
        Object[][] data = {
                {"BAGUETTE", tab.get(0)},
                {"FLUTE", tab.get(1)},
                {"CROISSANT", tab.get(2)},
                {"PAIN AU CHOCOLAT", tab.get(3)},
                {"BRIOCHE SUCRE", tab.get(4)},
                {"PAIN AU LAIT", tab.get(5)},
                {"TARTE AU CITRON", tab.get(6)},
                {"TARTE PRALINE", tab.get(7)},
                {"COCA-COLA", tab.get(8)},
                {"FANTA", tab.get(9)},
                {"SPRITE", tab.get(10)},
                {"OASIS", tab.get(11)},
                {"ORANGE", tab.get(12)},
                {"POMME", tab.get(13)},
                {"RAISIN", tab.get(14)},
        };

        //Les titres des colonnes
        String title[] = {"PRODUIT", "NOMBRE EN VITRINE"};
        JTable tab_vitrine = new JTable(data, title);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tab_vitrine.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tab_vitrine.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        tab_vitrine.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_vitrine.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_vitrine);
        tab_vitrine.setEnabled(false);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_vitrine = new JScrollPane(tab_vitrine);
        pan_vitrine.removeAll();
        pan_vitrine.add(tbc_vitrine, BorderLayout.CENTER);
    }

    public void stockSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Stock selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        Produit[] tabStock = null;
        LinkedList tab;

        GestionBDD baseDonnee = new GestionBDD();
        tabStock = baseDonnee.getStock();

        tab = setAfficheTab(tabStock, tabStock.length);

        //Les données du tableau
        Object[][] data = {
                {"BAGUETTE", tab.get(0)},
                {"FLUTE", tab.get(1)},
                {"CROISSANT", tab.get(2)},
                {"PAIN AU CHOCOLAT", tab.get(3)},
                {"BRIOCHE SUCRE", tab.get(4)},
                {"PAIN AU LAIT", tab.get(5)},
                {"TARTE AU CITRON", tab.get(6)},
                {"TARTE PRALINE", tab.get(7)},
                {"COCA-COLA", tab.get(8)},
                {"FANTA", tab.get(9)},
                {"SPRITE", tab.get(10)},
                {"OASIS", tab.get(11)},
                {"ORANGE", tab.get(12)},
                {"POMME", tab.get(13)},
                {"RAISIN", tab.get(14)},
        };

        //Les titres des colonnes
        String title[] = {"PRODUIT", "STOCK"};
        JTable tab_stock = new JTable(data, title);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tab_stock.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tab_stock.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        tab_stock.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_stock.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_stock);
        tab_stock.setEnabled(false);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_stock = new JScrollPane(tab_stock);
        pan_stock.removeAll();
        pan_stock.add(tbc_stock, BorderLayout.CENTER);
    }

    public void paramSeuilSelect() {
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
        String title[] = {"PRODUITS", "SEUIL", "TAILLE FOURNEE"};
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

    public void paramMdpSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Parametre MDP selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void paramPrixSelect() {
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
        String title[] = {"PRODUIT", "PRIX"};
        JTable tab_paramPrix = new JTable(data, title);
        tab_paramPrix.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_paramPrix.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_paramPrix);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_paramPrix = new JScrollPane(tab_paramPrix);
        pan_paramPrix.add(tbc_paramPrix, BorderLayout.CENTER);
    }

    private void updateRowHeights(JTable table) {
        try {
            for (int row = 0; row < table.getRowCount(); row++) {
                int rowHeight = table.getRowHeight();

                for (int column = 0; column < table.getColumnCount(); column++) {
                    Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                    rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                }

                table.setRowHeight(row, rowHeight);
            }
        } catch (ClassCastException e) {
        }
    }

    private LinkedList setAfficheTab(Produit[] tab, int taille) {
        LinkedList<Integer> tabAffich = new LinkedList<Integer>();

        System.out.println(taille);

        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);
        tabAffich.add(0);

        for (int i = 0; i < taille; i++) {
            if (tab[i].getNom().equals("Baguette"))
            {
                System.out.println("dans baguette");
                tabAffich.set(0, tabAffich.get(0) + tab[i].getQuantite());
            }

            else if (tab[i].getNom().equals("Flute"))
                tabAffich.set(1, tabAffich.get(1) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("Croissant"))
                tabAffich.set(2, tabAffich.get(2) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("Pain au chocolat"))
                tabAffich.set(3, tabAffich.get(3) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("Brioche sucre"))
                tabAffich.set(4, tabAffich.get(4) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("Pain au lait"))
                tabAffich.set(5, tabAffich.get(5) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("Tarte citron"))
                tabAffich.set(6, tabAffich.get(6) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("Tarte praline"))
                tabAffich.set(7, tabAffich.get(7) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("Coca Cola"))
                tabAffich.set(8, tabAffich.get(8) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("Fanta"))
                tabAffich.set(9, tabAffich.get(9) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("Sprite"))
                tabAffich.set(10, tabAffich.get(10) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("Oasis"))
                tabAffich.set(11, tabAffich.get(11) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("Orange"))
                tabAffich.set(12, tabAffich.get(12) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("Pomme"))
                tabAffich.set(13, tabAffich.get(13) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("Raisin"))
                tabAffich.set(14, tabAffich.get(14) + tab[i].getQuantite());
        }
        System.out.print(tabAffich);
        return tabAffich;
    }
}
