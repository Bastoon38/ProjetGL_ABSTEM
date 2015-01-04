import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.geom.Arc2D;
import java.util.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton btn_majMdp;
    private JPasswordField psw_ancienMDP;
    private JPanel pan_panelDeco;
    private JPanel pan_panel1;
    private JButton btn_managerDeco;
    private JButton btn_majPrix;
    private JPanel pan_vitTab;
    private JPanel pan_vitGraph;
    private JPanel pan_stockTab;
    private JPanel pan_stockGraph;
    private JPanel pan_bilanTab;
    private JPanel pan_bilanGraph;
    private JPanel pan_cuissonTab;
    private JPanel pan_cuissonGraph;
    private JPanel pan_cuissonGraph1;
    private JPanel pan_cuissonGraph2;
    private JButton btn_suppLigne;
    private JButton btn_graphVitrine;
    private JTable tab_cmd;
    private JTable tab_att;
    private JTable tab_paramPrix;
    private JTable tab_seuil;
    private JTable tab_vitrine;
    private JTable tab_stock;
    private JTable tab_cuisson;
    private String jour;
    private String heure;
    private String minute;
    private String heureFinal;
    private JTable tab_bilan;


    private String password = "manager";


    public Interface_manager()  {

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        System.out.println("JFrame.MAXIMIZED_BOTH = " + JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1200, 900));

        add(pan_panel1);

        ClassLoader classLoader = getClass().getClassLoader();
        URL file = classLoader.getResource("images/param64.png");
        System.out.println(file);
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
                Connexion connexion = new Connexion();
                connexion.setVisible(true);
                setVisible(false);
            }
        });


        // MISE EN PLACE DES PREMIERES PAGE POUR LE DEMARRAGE (SANS AVOIR A CLIQUER DESSUS)

        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==2 || column==1;
            }

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
        model.addColumn("QUANTITE");
        model.addColumn("COMMANDER ?");

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

                int taille=0;
                int flag=0;
                GestionBDD baseDonnee = new GestionBDD();

                LinkedList<Object> listCmd = new LinkedList<Object>();

                for ( int i=0; i<30 ;i++)
                    listCmd.add(0);

                try {
                    for (int i=0; i<15 ; i++)
                    {
                        if (tab_cmd.getValueAt(i,2).equals(true))
                        {
                            int valeurSaisie;
                            System.out.println("tab_cmd.getValueAt(i,1) =========" + tab_cmd.getValueAt(i,1).getClass());
                            valeurSaisie = Integer.parseInt(tab_cmd.getValueAt(i, 1).toString());
                            System.out.println("valeurSaisie =========" + valeurSaisie);
                            if( valeurSaisie> 1 && valeurSaisie < 5000)
                            {
                                flag = 1;
                                baseDonnee.ajouterCommandeFournisseur(tab_cmd.getValueAt(i,0), tab_cmd.getValueAt(i,1));
                                listCmd.set(taille, tab_cmd.getValueAt(i,0));
                                taille++;
                                listCmd.set(taille, tab_cmd.getValueAt(i,1));
                                taille++;
                            }
                            else
                                JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre entre 1 et 5000" + "\n" + "Tableau ligne:" + i, "SAISIE INCORRECTE",  JOptionPane.ERROR_MESSAGE);
                        }

                    }

                    if (flag == 1)
                    {
                        GenerationDocument doc = new GenerationDocument(listCmd,taille);
                    }
                    commandeSelect();
                }
                catch (NumberFormatException de)
                {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir un nombre entre 1 et 5000", "SAISIE INCORRECTE",  JOptionPane.ERROR_MESSAGE);
                }

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
                try {
                    for (int i=0; i<tabAttendu.length ; i++)
                    {
                        if (tabAttendu[i].getFlag() == true)
                        {
                            JPanel panel = new JPanel();
                            JLabel jour = new JLabel("jour : ");
                            JLabel mois = new JLabel("mois : ");
                            JLabel annee = new JLabel("annee : ");
                            JTextField jour1 = new JTextField(2);
                            JTextField mois1 = new JTextField(2);
                            JTextField annee1 = new JTextField(4);

                            panel.add(jour);
                            panel.add(jour1);
                            panel.add(mois);
                            panel.add(mois1);
                            panel.add(annee);
                            panel.add(annee1);
                            String[] options = new String[]{"OK", "Cancel"};
                            int option = JOptionPane.showOptionDialog(null, panel, "Date: "+tabAttendu[i].getNom(),
                                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(option == 0) // pressing OK button
                            {
                                int an =  Integer.parseInt(annee1.getText());
                                int mo =  Integer.parseInt(mois1.getText());
                                int jo =  Integer.parseInt(jour1.getText());
                                String str_annee = annee1.getText();
                                String str_mois = mois1.getText();
                                String str_jour = jour1.getText();

                                if(str_mois.length() == 1)
                                    str_mois = "0"+mois1.getText();
                                if(str_jour.length() == 1)
                                    str_jour = "0"+jour1.getText();

                                if (jo>0 && jo<=31 && mo>0 && mo<=12 && an>=2015)
                                {
                                    String dat = str_annee+"-"+str_mois+"-"+str_jour+" "+"00:00:00";
                                    System.out.println("dat === " + dat);
                                    baseDonnee.ajouterStock(tabAttendu[i].getNom(), tabAttendu[i].getQuantite(),dat);
                                    baseDonnee.supprimerCommande(tabAttendu[i].getNom(), tabAttendu[i].getQuantite());
                                }
                                else
                                    JOptionPane.showMessageDialog(null, "Mauvaise saisie : 0<jour<31 et 0<mois<12 et 2015<année", "SAISIE INCORRECTE",  JOptionPane.ERROR_MESSAGE);
                            }
                        }

                    }
                }
                catch (NumberFormatException de)
                {
                    JOptionPane.showMessageDialog(null, "Mauvaise saisie : 0<jour<31 et 0<mois<12 et 2015<année", "SAISIE INCORRECTE",  JOptionPane.ERROR_MESSAGE);
                }

                commandeSelect();
            }
        });

        btn_suppLigne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int dialogResult = JOptionPane.showConfirmDialog(null, "Confirmer la suppression de l'élément selectionné ?", "CONFIRMATION SUPPRESSION",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION)
                {
                    GestionBDD baseDonnee = new GestionBDD();
                    int rowSelected = tab_att.getSelectedRow();

                    baseDonnee.supprimerCommande(tab_att.getValueAt(rowSelected, 0), tab_att.getValueAt(rowSelected, 1));
                    ((DefaultTableModel)tab_att.getModel()).removeRow(rowSelected);
                }
            }
        });


        btn_majPrix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GestionBDD baseDonnee = new GestionBDD();

                try
                {
                    for (int i=0; i<15 ; i++)
                    {
                        String produit = tab_paramPrix.getValueAt(i,0).toString();
                        String prix = tab_paramPrix.getValueAt(i,1).toString();
                        float float_prix = Float.parseFloat(prix);
                        System.out.println("float_prix = " + float_prix);
                        if(float_prix>0.0 && float_prix < 10.0 )
                            baseDonnee.majPrix(tab_paramPrix.getValueAt(i,0).toString(),tab_paramPrix.getValueAt(i,1).toString());
                        else
                            JOptionPane.showMessageDialog(null, "Element: "+produit +" non modifié car mauvaise saisie", "Mise à jour des prix",  JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(null, "Mise à jour des prix avec une saisie correcte réussie !", "Mise à jour des prix",  JOptionPane.INFORMATION_MESSAGE);

                }
                catch (NumberFormatException de)
                {
                    JOptionPane.showMessageDialog(null, "Mauvaise saisie : 0<prix<10", "Mise à jour des prix",  JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        btn_majMdp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GestionBDD baseDonnee = new GestionBDD();
                String mdpEncours  = baseDonnee.getMdp();
                //if(mdpEncours.equals(null))
                  //  JOptionPane.showMessageDialog(null, "echec", "SAISIE REUSSIE", JOptionPane.ERROR_MESSAGE);
                System.out.println("mdpEncours = " + mdpEncours);
                String ancienSaisi = psw_ancienMDP.getText();
                String nouvMdp = psw_nouvMotDePasse.getText();
                String confMdp = psw_confirmeChgt.getText();
                if (ancienSaisi.equals(mdpEncours))
                {
                    if (nouvMdp.equals(confMdp))
                        JOptionPane.showMessageDialog(null, baseDonnee.setMdp(nouvMdp), "SAISIE REUSSIE", JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null,"Echec de la mide a jour du mot de passe", "SAISIE NON REUSSIE", JOptionPane.ERROR_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null,"Echec de la mide a jour du mot de passe", "SAISIE NON REUSSIE", JOptionPane.ERROR_MESSAGE);
            }
        });


        /************************************************************************************/
        /****************** Les données du tableau pour la page parametre seuil *************/
        /************************************************************************************/
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

        cmb_jour.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                paramSeuilSelect();
            }
        });

        cmb_heure.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                paramSeuilSelect();
            }
        });

        cmb_minute.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                paramSeuilSelect();
            }
        });

        btn_paramOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionBDD baseDonnee = new GestionBDD();
                try
                {
                    for (int i = 0; i<15 ; i++)
                    {
                        String produit = tab_seuil.getValueAt(i,0).toString();
                        String seuil = tab_seuil.getValueAt(i,1).toString();
                        String fournee = tab_seuil.getValueAt(i,2).toString();
                        int int_seuil = Integer.parseInt(seuil.toString());
                        int int_fournee = Integer.parseInt(fournee.toString());
                        if(int_seuil>0 && int_seuil < 100 && int_fournee>0 && int_fournee < 100 )
                            baseDonnee.majSeuil(produit,jour ,heureFinal,seuil, fournee);
                        else
                            JOptionPane.showMessageDialog(null, "Element: "+produit +" non modifié car mauvaise saisie", "Mise à jour des seuils",  JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(null, "Mise à jour des seuils avec une saisie correcte réussie !", "Mise à jour des seuils",  JOptionPane.INFORMATION_MESSAGE);

                }
                catch (NumberFormatException de)
                {
                    JOptionPane.showMessageDialog(null, "Mauvaise saisie : 0<seuil<100 et 0<fournee<100", "Mise à jour des seuils",  JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btn_paramDefaut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GestionBDD baseDonnee = new GestionBDD();
                jour = cmb_jour.getSelectedItem().toString();
                heure = cmb_heure.getSelectedItem().toString();
                minute = cmb_minute.getSelectedItem().toString();
                heureFinal = ( heure+"."+minute );
                System.out.println("jour = " + jour);
                System.out.println("heure = " + heureFinal);

                Produit[] tabSeuilIni = null;

                tabSeuilIni = baseDonnee.getSeuil(jour,heureFinal);

                DefaultTableModel model = new DefaultTableModel();
                tab_seuil = new JTable(model);

                // Create a couple of columns
                model.addColumn("PRODUIT");
                model.addColumn("SEUIL");
                model.addColumn("FOURNEE");

                // Append a row
                for (int i = 0; i < tabSeuilIni.length; i++)
                {
                    model.addRow(new Object[]{tabSeuilIni[i].getNom().toUpperCase(), tabSeuilIni[i].getSeuilIni(), tabSeuilIni[i].getFourneeIni()});
                }

                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                tab_seuil.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                tab_seuil.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                tab_seuil.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

                tab_seuil.setFont(new Font("Serif", Font.PLAIN, 30));
                tab_seuil.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
                updateRowHeights(tab_seuil);
                //tab_att.setEnabled(true);
                //Nous ajoutons notre tableau à notre contentPane dans un scroll
                //Sinon les titres des colonnes ne s'afficheront pas !
                JScrollPane tbc_seuil = new JScrollPane(tab_seuil);
                pan_paramSeuil.removeAll();
                pan_paramSeuil.add(tbc_seuil, BorderLayout.CENTER);

            }
        });

        cuissonSelect();
       // commandeSelect();
        paramSeuilSelect();
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
        tab_bilan = new JTable(data, title);

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
        pan_bilanTab.removeAll();
        pan_bilanTab.add(tbc_bilan, BorderLayout.CENTER);

        /************************************* GRAPHIQUE ********************************************/

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<String, Integer> repartitionVendu = new HashMap<String, Integer>();
        Map<String, Integer> repartitionJete = new HashMap<String, Integer>();

        for ( int i=0; i<tab_bilan.getRowCount(); i++)
        {
            repartitionVendu.put(tab_bilan.getValueAt(i,0).toString(), (Integer) tab_bilan.getValueAt(i,1));
            repartitionJete.put(tab_bilan.getValueAt(i,0).toString(), (Integer) tab_bilan.getValueAt(i,2));
        }


        for ( int i=0; i<tab_bilan.getRowCount(); i++)
            dataset.addValue(repartitionVendu.get(tab_bilan.getValueAt(i, 0)),"VENDU",  tab_bilan.getValueAt(i,0).toString());

        for ( int i=0; i<tab_bilan.getRowCount(); i++)
            dataset.addValue(repartitionJete.get(tab_bilan.getValueAt(i, 0)), "JETE",  tab_bilan.getValueAt(i,0).toString());

        JFreeChart barChart = ChartFactory.createBarChart3D("GRAPHIQUE BILAN", "Produit", "Nombre", dataset);

        ChartPanel cPanel = new ChartPanel(barChart);

        pan_bilanGraph.removeAll();
        pan_bilanGraph.add(cPanel, BorderLayout.CENTER);


        lab_recette.setText("RECETTE = 13976 €");
        lab_recette.setFont(new Font("Serif", Font.BOLD, 23));
        pan_bilanTab.add(lab_recette, BorderLayout.SOUTH);
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
        model.addColumn("RECU ?");

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
        tab_cuisson = new JTable(model);

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
        pan_cuissonTab.removeAll();
        pan_cuissonTab.add(tbc_cuisson, BorderLayout.CENTER);

        /************************************* GRAPHIQUE 1 ********************************************/

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<String, Integer> repartition = new HashMap<String, Integer>();

        for ( int i=0; i<tab_cuisson.getRowCount(); i++)
        {
            if(tab_cuisson.getValueAt(i,2).equals("EN COURS"))
                repartition.put(tab_cuisson.getValueAt(i,0).toString(), (Integer) tab_cuisson.getValueAt(i,1));
        }

        for ( int i=0; i<tab_cuisson.getRowCount(); i++)
        {
            if(tab_cuisson.getValueAt(i,2).equals("EN COURS"))
                dataset.addValue(repartition.get(tab_cuisson.getValueAt(i, 0)), tab_cuisson.getValueAt(i,0).toString(),  tab_cuisson.getValueAt(i,0).toString());
        }

        JFreeChart barChart = ChartFactory.createBarChart3D("GRAPHIQUE CUISSON EN COURS", "Produit", "Nombre", dataset);

        ChartPanel cPanel = new ChartPanel(barChart);

        pan_cuissonGraph1.removeAll();
        pan_cuissonGraph1.add(cPanel, BorderLayout.CENTER);


        /************************************* GRAPHIQUE 2 ********************************************/

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        Map<String, Integer> repartition2 = new HashMap<String, Integer>();

        for ( int i=0; i<tab_cuisson.getRowCount(); i++)
        {
            if(tab_cuisson.getValueAt(i,2).equals("EN ATTENTE"))
                repartition2.put(tab_cuisson.getValueAt(i,0).toString(), (Integer) tab_cuisson.getValueAt(i,1));
        }

        for ( int i=0; i<tab_cuisson.getRowCount(); i++)
        {
            if(tab_cuisson.getValueAt(i,2).equals("EN ATTENTE"))
                dataset2.addValue(repartition2.get(tab_cuisson.getValueAt(i, 0)), tab_cuisson.getValueAt(i,0).toString(),  tab_cuisson.getValueAt(i,0).toString());
        }

        JFreeChart barChart2 = ChartFactory.createBarChart3D("GRAPHIQUE CUISSON EN ATTENTE", "Produit", "Nombre", dataset2);

        ChartPanel cPanel2 = new ChartPanel(barChart2);

        pan_cuissonGraph2.removeAll();
        pan_cuissonGraph2.add(cPanel2, BorderLayout.CENTER);
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
        tab_vitrine = new JTable(data, title);

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
        pan_vitTab.removeAll();
        pan_vitTab.add(tbc_vitrine, BorderLayout.CENTER);

        /************************************* GRAPHIQUE ********************************************/

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<String, Integer> repartition = new HashMap<String, Integer>();

        for ( int i=0; i<tab_vitrine.getRowCount(); i++)
            repartition.put(tab_vitrine.getValueAt(i,0).toString(), (Integer) tab_vitrine.getValueAt(i,1));

        for ( int i=0; i<tab_vitrine.getRowCount(); i++)
            dataset.addValue(repartition.get(tab_vitrine.getValueAt(i, 0)), tab_vitrine.getValueAt(i,0).toString(),  tab_vitrine.getValueAt(i,0).toString());

        JFreeChart barChart = ChartFactory.createBarChart3D("GRAPHIQUE VITRINE", "Produit", "Nombre", dataset);

        ChartPanel cPanel = new ChartPanel(barChart);

        pan_vitGraph.removeAll();
        pan_vitGraph.add(cPanel, BorderLayout.CENTER);
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
        tab_stock = new JTable(data, title);

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
        pan_stockTab.removeAll();
        pan_stockTab.add(tbc_stock, BorderLayout.CENTER);

        /****************************** GRAPHIQUE  ********************************/

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<String, Integer> repartition = new HashMap<String, Integer>();

        for ( int i=0; i<tab_stock.getRowCount(); i++)
            repartition.put(tab_stock.getValueAt(i,0).toString(), (Integer) tab_stock.getValueAt(i,1));

        for ( int i=0; i<tab_stock.getRowCount(); i++)
            dataset.addValue(repartition.get(tab_stock.getValueAt(i, 0)), tab_stock.getValueAt(i,0).toString(),  tab_stock.getValueAt(i,0).toString());

        JFreeChart barChart = ChartFactory.createBarChart3D("GRAPHIQUE STOCK", "Produit", "Nombre", dataset);

        ChartPanel cPanel = new ChartPanel(barChart);

        pan_stockGraph.removeAll();
        pan_stockGraph.add(cPanel, BorderLayout.CENTER);
    }

    public void paramSeuilSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Parametre seuil selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        GestionBDD baseDonnee = new GestionBDD();
        jour = cmb_jour.getSelectedItem().toString();
        heure = cmb_heure.getSelectedItem().toString();
        minute = cmb_minute.getSelectedItem().toString();
        heureFinal = ( heure+"."+minute );
        System.out.println("jour = " + jour);
        System.out.println("heure = " + heureFinal);

        Produit[] tabSeuil = null;

        tabSeuil = baseDonnee.getSeuil(jour,heureFinal);

        DefaultTableModel model = new DefaultTableModel();
        tab_seuil = new JTable(model);

        // Create a couple of columns
        model.addColumn("PRODUIT");
        model.addColumn("SEUIL");
        model.addColumn("FOURNEE");

        // Append a row
        for (int i = 0; i < tabSeuil.length; i++)
        {
            model.addRow(new Object[]{tabSeuil[i].getNom().toUpperCase(), tabSeuil[i].getSeuil(), tabSeuil[i].getFournee()});
        }


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tab_seuil.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tab_seuil.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tab_seuil.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        tab_seuil.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_seuil.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_seuil);
        //tab_att.setEnabled(true);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_seuil = new JScrollPane(tab_seuil);
        pan_paramSeuil.removeAll();
        pan_paramSeuil.add(tbc_seuil, BorderLayout.CENTER);

    }

    public void paramMdpSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Parametre MDP selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public void paramPrixSelect() {
        // TODO: mettre les actions à effectuer quand on clique sur l'onglet ci-dessus
        //JOptionPane.showMessageDialog(null, "Parametre prix selectionné", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        Produit[] tabProduit = null;

        GestionBDD baseDonnee = new GestionBDD();
        tabProduit= baseDonnee.getProduit();

        //Les données du tableau
        Object[][] data = {
                {tabProduit[0].getNom(), tabProduit[0].getPrix()},
                {tabProduit[1].getNom(), tabProduit[1].getPrix()},
                {tabProduit[2].getNom(), tabProduit[2].getPrix()},
                {tabProduit[3].getNom(), tabProduit[3].getPrix()},
                {tabProduit[4].getNom(), tabProduit[4].getPrix()},
                {tabProduit[5].getNom(), tabProduit[5].getPrix()},
                {tabProduit[6].getNom(), tabProduit[6].getPrix()},
                {tabProduit[7].getNom(), tabProduit[7].getPrix()},
                {tabProduit[8].getNom(), tabProduit[8].getPrix()},
                {tabProduit[9].getNom(), tabProduit[9].getPrix()},
                {tabProduit[10].getNom(), tabProduit[10].getPrix()},
                {tabProduit[11].getNom(), tabProduit[11].getPrix()},
                {tabProduit[12].getNom(), tabProduit[12].getPrix()},
                {tabProduit[13].getNom(), tabProduit[13].getPrix()},
                {tabProduit[14].getNom(), tabProduit[14].getPrix()},
        };

        //Les titres des colonnes
        String title[] = {"PRODUIT", "PRIX"};
        tab_paramPrix = new JTable(data, title);
        tab_paramPrix.setFont(new Font("Serif", Font.PLAIN, 30));
        tab_paramPrix.getTableHeader().setFont(new Font("Serif", Font.BOLD, 23));
        updateRowHeights(tab_paramPrix);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane tbc_paramPrix = new JScrollPane(tab_paramPrix);
        if(pan_paramPrix.getComponentCount() == 2)
            pan_paramPrix.remove(1);
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
            if (tab[i].getNom().equals("BAGUETTE"))
            {
                System.out.println("dans baguette");
                tabAffich.set(0, tabAffich.get(0) + tab[i].getQuantite());
            }

            else if (tab[i].getNom().equals("FLUTE"))
                tabAffich.set(1, tabAffich.get(1) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("CROISSANT"))
                tabAffich.set(2, tabAffich.get(2) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("PAIN AU CHOCOLAT"))
                tabAffich.set(3, tabAffich.get(3) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("BRIOCHE SUCRE"))
                tabAffich.set(4, tabAffich.get(4) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("PAIN AU LAIT"))
                tabAffich.set(5, tabAffich.get(5) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("TARTE CITRON"))
                tabAffich.set(6, tabAffich.get(6) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("TARTE PRALINE"))
                tabAffich.set(7, tabAffich.get(7) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("COCA COLA"))
                tabAffich.set(8, tabAffich.get(8) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("FANTA"))
                tabAffich.set(9, tabAffich.get(9) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("SPRITE"))
                tabAffich.set(10, tabAffich.get(10) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("OASIS"))
                tabAffich.set(11, tabAffich.get(11) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("ORANGE"))
                tabAffich.set(12, tabAffich.get(12) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("POMME"))
                tabAffich.set(13, tabAffich.get(13) + tab[i].getQuantite());

            else if (tab[i].getNom().equals("RAISIN"))
                tabAffich.set(14, tabAffich.get(14) + tab[i].getQuantite());
        }
        System.out.print(tabAffich);
        return tabAffich;
    }

    public void setPassword(String mdp) {this.password=mdp;}
    public String getPassword() {return password;}

}
