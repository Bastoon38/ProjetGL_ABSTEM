/**
 * Created by Elio on 29/11/2014.
 */
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.GridLayout;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTable;

import java.awt.FlowLayout;





public class Interface_vendeur extends JFrame {
    private JTable tab_commande;
    private JTable tab_jeter_1;
    private float total_prix=0;
    //vector produits
    // Produit vitrine[] = null;
    Vector<Produit> vitrine = new Vector<Produit>();
    Vector<Produit> commandes= new Vector<Produit>();
    Vector<Produit> jeter= new Vector<Produit>();

    final JLabel lab_prix = new JLabel("");
    ///////////////////////////PARTIE TIMER////////////////////////////////////////////////////////////////
    /*
    // Appeller à la fonction chaque x minutes avec le timer
    timer_refresh_produits_jeter();


	*/
    ///////////////////////////////////////////////////////////////////////////////////////////////////////


    void timer_refresh_produits_jeter()
    {
        for(int i=0 ; i<vitrine.size(); i++)
        {

            java.util.Date fechaActual = new java.util.Date();

            if(vitrine.elementAt(i).getDate().after(fechaActual))
            {
                //System.out.println ("PAS PERIME "+vitrine.elementAt(i).getNom()+" "+vitrine.elementAt(i).getQuantite()+" "+vitrine.elementAt(i).getPrix()+" "+vitrine.elementAt(i).getPerime()+ " "+vitrine.elementAt(i).getDate()+" "+vitrine.elementAt(i).getTime());
            }
            else
            {
                String date_now =new SimpleDateFormat("yyyy-MM-dd").format(fechaActual);
                String date_produit=vitrine.elementAt(i).getDate().toString();

                // QUAND LES DATES SONT EGALS == MEME JOUR
                if(date_now.equals(date_produit))
                {
                    //time now
                    int hours_now=fechaActual.getHours();
                    int minutes_now=fechaActual.getMinutes();
                    int seconds_now=fechaActual.getSeconds();

                    Time now= new Time(hours_now,minutes_now,seconds_now);

                    //time produit
                    int hours=vitrine.elementAt(i).getTime().getHours();
                    int minutes=vitrine.elementAt(i).getTime().getMinutes();
                    int seconds=vitrine.elementAt(i).getTime().getSeconds();
                    Time produit=new Time(hours,minutes,seconds);
                    //si le produit est perimé
                    if(now.after(produit))
                    {
                        vitrine.elementAt(i).setPerime(1);
                    }
                }
                else
                {
                    vitrine.elementAt(i).setPerime(1);
                }
            }

        }
        for(int i=0 ; i<vitrine.size(); i++)
        {

            //System.out.println (vitrine.elementAt(i).getNom()+" "+vitrine.elementAt(i).getQuantite()+" "+vitrine.elementAt(i).getPrix()+" "+vitrine.elementAt(i).getPerime()+ " "+vitrine.elementAt(i).getDate()+" "+vitrine.elementAt(i).getTime());
            if(vitrine.elementAt(i).getPerime()== 1)
            {
                System.out.println ("VITRINE PERIME "+vitrine.elementAt(i).getNom()+"	"+vitrine.elementAt(i).getQuantite()+"	"+vitrine.elementAt(i).getPrix()+"	"+vitrine.elementAt(i).getPerime()+"	"+vitrine.elementAt(i).getDate()+"	"+vitrine.elementAt(i).getTime());

                JOptionPane.showMessageDialog(null, vitrine.elementAt(i).getNom()+" périmé(e)", "Périmé(e) "+ vitrine.elementAt(i).getNom(), JOptionPane.WARNING_MESSAGE);
                Produit aux = new Produit(vitrine.elementAt(i).getNom(),vitrine.elementAt(i).getPrix(),vitrine.elementAt(i).getQuantite(),vitrine.elementAt(i).getDate(),vitrine.elementAt(i).getTime(),vitrine.elementAt(i).getPerime());
                vitrine.remove(i);
                jeter.add(aux);
                i--;

                DefaultTableModel model = (DefaultTableModel) tab_jeter_1.getModel();
                model.addRow(new Object[]{aux.getNom(),Integer.toString(aux.getQuantite())});
            }
        }


    }



    void ajouter_produit_a_jtable(Produit prod_baguette, String nom)
    {
        Produit aux = new Produit(prod_baguette.getNom(),prod_baguette.getPrix(),1,prod_baguette.getDate(),prod_baguette.getTime(),prod_baguette.getPerime());


        int ver=verifier(nom);
        if(ver!=20)
        {

            //System.out.println(rowSelected);
            String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
            int quant=Integer.parseInt(ex);
            quant++;
            String prix_unit=Float.toString(aux.getPrix());
            float prix_unitaire=Float.parseFloat(prix_unit);
            float prix_total=prix_unitaire*quant;
            //prix_total = (float)(Math.floor(prix_total * 100) / 100);
            round(prix_total,1);
            prix_total = (float)(Math.floor(prix_total * 10) / 10);
            //Increment of the price
            tab_commande.setValueAt(String.valueOf(prix_total), ver, 2);
            //Increment of the quantity
            tab_commande.setValueAt(Integer.toString(quant), ver, 1);
            //refresh
            tab_commande.repaint();
            mis_a_jour_total();
            lab_prix.setText(String.valueOf(total_prix));
        }
        else
        {
            DefaultTableModel model = (DefaultTableModel) tab_commande.getModel();
            model.addRow(new Object[]{nom, "1", Float.toString(aux.getPrix()),Float.toString(aux.getPrix())});
            mis_a_jour_total();
            lab_prix.setText(String.valueOf(total_prix));
        }
    }




    void ajouter_produit_a_commande(Produit prod_baguette, String nom)
    {
        System.out.println("\n");

        Produit aux = new Produit(prod_baguette.getNom(),prod_baguette.getPrix(),1,prod_baguette.getDate(),prod_baguette.getTime(),prod_baguette.getPerime());
        //System.out.println ("	"+aux.getNom()+"	"+aux.getQuantite()+"	"+aux.getDate()+"	"+aux.getTime()+"	"+aux.getPerime());

        aux.setNom(nom);
        //System.out.println ("	"+aux.getNom()+"	"+aux.getQuantite()+"	"+aux.getDate()+"	"+aux.getTime()+"	"+aux.getPerime());

        commandes.add(aux);

        for (int i=0; i<commandes.size();i++)
        {

            System.out.println ("COMMANDES	"+commandes.elementAt(i).getNom()+" "+commandes.elementAt(i).getDate()+" "+commandes.elementAt(i).getTime());

        }
        System.out.println("\n");
    }


    void gerer_produit_selectione(String nom)
    {

        Produit prod_baguette=null;
        Date date_baguette=null;
        Time time_baguette=null;
        int quant_baguette=0;
        int pos_produit_en_vitrine=-1;

        Time aux_time_baguette=null;
        Date aux_date_baguette=null;


        for(int i=0 ; i<vitrine.size(); i++)
        {


            //System.out.println("nombres "+nom  +" "+vitrine.elementAt(i).getNom()+"  "+vitrine.elementAt(i).getPerime());
            if(nom.equals(vitrine.elementAt(i).getNom()) && vitrine.elementAt(i).getPerime()==0)
            {


                //System.out.println("aceptados "+nom  +" "+vitrine.elementAt(i).getNom()+"  "+vitrine.elementAt(i).getPerime());
                if(date_baguette==null)
                {
                    pos_produit_en_vitrine=i;
                    prod_baguette=vitrine.elementAt(i);
                    date_baguette=prod_baguette.getDate();
                    time_baguette=prod_baguette.getTime();
                    quant_baguette=prod_baguette.getQuantite();
                    //System.out.println("primera "+prod_baguette.getNom()+ " "+prod_baguette.getDate()+ " " + prod_baguette.getTime() );

                }
                else
                {
                    //aux_date_baguette est la date du produit qu'on essaye à verifier

                    aux_date_baguette=vitrine.elementAt(i).getDate();

                    //System.out.println("fechas "+aux_date_baguette+ " "+prod_baguette.getDate());

                    //Si la date est avant que la date de notre produit dejà pris
                    if(aux_date_baguette.before(date_baguette))
                    {
                        pos_produit_en_vitrine=i;
                        prod_baguette=vitrine.elementAt(i);
                        date_baguette=prod_baguette.getDate();
                        time_baguette=prod_baguette.getTime();
                        quant_baguette=prod_baguette.getQuantite();
                    }
                    else
                    {
                        if(date_baguette.before(aux_date_baguette))
                        {

                        }
                        else //Les dates sont les mêmes pour les deux produits à comparer
                        {

                            //aux_date_baguette est la date du produit qu'on essaye à verifier

                            aux_time_baguette=vitrine.elementAt(i).getTime();
                            //System.out.println("fechas identicas" + aux_time_baguette +" "+time_baguette);
                            if(aux_time_baguette.before(time_baguette))
                            {
                                //System.out.println("Fecha del producto de vitrina anterior al producto que ya tenia");
                                pos_produit_en_vitrine=i;
                                prod_baguette=vitrine.elementAt(i);
                                date_baguette=prod_baguette.getDate();
                                time_baguette=prod_baguette.getTime();
                                quant_baguette=prod_baguette.getQuantite();
                            }

                        }
                    }
                }
            }
        }
        if(prod_baguette==null)
        {
            JOptionPane.showMessageDialog(null, "Il n'a pas "+nom+" en vitrine", "Warning "+ nom, JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            int quantity=vitrine.elementAt(pos_produit_en_vitrine).getQuantite();
            // SI ON A PLUSIERS ELEMENTS DE CET PRODUIT
            if(quantity>1)
            {
                //System.out.println("CANTIDADDDDD "+ quantity);
                int quantity_final=quantity-1;
                vitrine.elementAt(pos_produit_en_vitrine).setQuantite(quantity_final);
                //System.out.println("CANTIDADDDDD FINAL "+ quantity_final);
                //System.out.println("test para las dates!=1 "+prod_baguette.getNom()+ " "+prod_baguette.getDate()+ " " + prod_baguette.getTime() );


                for(int l=0 ; l<vitrine.size(); l++)
                {
                    System.out.println ("VITRINE "+vitrine.elementAt(l).getNom()+"	"+vitrine.elementAt(l).getQuantite()+"	"+vitrine.elementAt(l).getPrix()+"	"+vitrine.elementAt(l).getPerime()+"	"+vitrine.elementAt(l).getDate()+"	"+vitrine.elementAt(l).getTime());
                }
                System.out.println("\n");


                //System.out.println("ANADIR A PRODUIT !=1 "+ nom+" "+prod_baguette.getQuantite());
                ajouter_produit_a_commande(prod_baguette,nom);
                ajouter_produit_a_jtable(prod_baguette,nom);
            }
            // SI ON A SEULEMENT 1 PRODUIT, on doit le supprimer
            else
            {

                ajouter_produit_a_commande(prod_baguette,nom);
                ajouter_produit_a_jtable(prod_baguette,nom);

                vitrine.elementAt(pos_produit_en_vitrine).setNom("null");
                vitrine.elementAt(pos_produit_en_vitrine).setQuantite(quantity-1);

                for(int i=0 ; i<vitrine.size(); i++)
                {
                    System.out.println ("VITRINE "+vitrine.elementAt(i).getNom()+"	"+vitrine.elementAt(i).getQuantite()+"	"+vitrine.elementAt(i).getPrix()+"	"+vitrine.elementAt(i).getPerime()+"	"+vitrine.elementAt(i).getDate()+"	"+vitrine.elementAt(i).getTime());

                }
                System.out.println("\n");

            }
        }
    }






    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    //fonction qui returne 20 s'on ne trouve pas le produit dans la liste et sinon il returne le row du produit
    public int verifier(String nom)
    {
        int res=20;
        String text;
        int rows = tab_commande.getRowCount();
        for (int i = 0; i < rows; i++)
        {
            text=tab_commande.getValueAt(i, 0).toString();
            if(nom.equals(text))
            {
                //Le produit est dejà dans la liste
                res=i;
                return res;
            }
            // System.out.printf("num is %.5f\n", total);
        }

        return res;
    }

    public void mis_a_jour_total()
    {
        float res,total=0;
        String text;
        int rows = tab_commande.getRowCount();
        for (int i = 0; i < rows; i++)
        {
            text=tab_commande.getValueAt(i, 2).toString();
            res= Float.parseFloat(text);
            total =total+ res;
            // System.out.printf("num is %.5f\n", total);
        }
        total = (float)(Math.floor(total * 100) / 100);
        //	System.out.printf("num is %.2f\n", total);
        total_prix=total;
        //	System.out.printf("num is %.2f\n", total_prix);
    }

    /**
     * Create the frame.
     */
    public Interface_vendeur() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Vendeur");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1446, 879);
        ClassLoader classLoader = getClass().getClassLoader();
        URL file;
        //Connection avec la BDD
        final GestionBDD base= new GestionBDD();
        vitrine=base.getVitrine(vitrine);
        for(int i=0 ; i<vitrine.size(); i++)
        {

            System.out.println (vitrine.elementAt(i).getNom()+" "+vitrine.elementAt(i).getQuantite()+" "+vitrine.elementAt(i).getPrix()+" "+vitrine.elementAt(i).getPerime()+ " "+vitrine.elementAt(i).getDate()+" "+vitrine.elementAt(i).getTime());

        }
        System.out.println("\n");


        JPanel pan_pain = new JPanel();
        pan_pain.setBounds(0, 16, 463, 924);

        JPanel pan_vienn = new JPanel();
        pan_vienn.setBounds(463, 4, 432, 963);

        JPanel pan_boisson = new JPanel();
        pan_boisson.setBounds(910, 4, 419, 938);



        JLabel lblBoisson = new JLabel("BOISSON");
        lblBoisson.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JButton btn_raisin = new JButton("Raisin");
        file = classLoader.getResource("images/raisin.jpg");
        Icon icn_raisin = new ImageIcon(file);
        btn_raisin.setIcon(icn_raisin);
       // btn_raisin.setIcon(new ImageIcon("images/raisin.jpg"));
        btn_raisin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("RAISIN");
            }
        });

        JButton btn_pomme = new JButton("Pomme");
        file = classLoader.getResource("images/pomme.jpg");
        Icon icn_pomme = new ImageIcon(file);
        btn_pomme.setIcon(icn_pomme);
       // btn_pomme.setIcon(new ImageIcon("images/pomme.jpg"));
        btn_pomme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("POMME");
            }
        });

        JButton btn_orange = new JButton("Orange");
        file = classLoader.getResource("images/orange.jpg");
        Icon icn_orange = new ImageIcon(file);
        btn_orange.setIcon(icn_orange);
        //btn_orange.setIcon(new ImageIcon("images/orange.jpg"));
        btn_orange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("ORANGE");
            }
        });

        JButton btn_oasis = new JButton("Oasis");
        file = classLoader.getResource("images/oasis.jpg");
        Icon icn_oasis = new ImageIcon(file);
        btn_oasis.setIcon(icn_oasis);
        //btn_oasis.setIcon(new ImageIcon("images/oasis.jpg"));
        btn_oasis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("OASIS");
            }
        });

        JButton btn_sprite = new JButton("Sprite");
        file = classLoader.getResource("images/sprite.jpg");
        Icon icn_sprite = new ImageIcon(file);
        btn_sprite.setIcon(icn_sprite);
       // btn_sprite.setIcon(new ImageIcon("images/sprite.jpg"));
        btn_sprite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("SPRITE");
            }
        });

        JButton btn_fanta = new JButton("Fanta");
        file = classLoader.getResource("images/fanta.jpg");
        Icon icn_fanta = new ImageIcon(file);
        btn_fanta.setIcon(icn_fanta);
        //btn_fanta.setIcon(new ImageIcon("images/fanta.jpg"));
        btn_fanta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("FANTA");
            }
        });

        JButton btn_coca = new JButton("Coca_cola");
        file = classLoader.getResource("images/coca_cola.jpg");
        Icon icn_coca_cola = new ImageIcon(file);
        btn_coca.setIcon(icn_coca_cola);
       // btn_coca.setIcon(new ImageIcon("images/coca_cola.jpg"));
        btn_coca.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("COCA COLA");
            }
        });
        this.setExtendedState(MAXIMIZED_BOTH);

        JPanel pan_commande = new JPanel();
        pan_commande.setBounds(1344, 28, 495, 924);

        JButton btn_decon = new JButton("Deconnection");
        btn_decon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connexion connexion = new Connexion();
                connexion.setVisible(true);
                setVisible(false);
            }
        });

        JButton btn_logo = new JButton("logo");
       // btn_logo.setIcon(new ImageIcon("images/logo.jpg"));
        file = classLoader.getResource("images/logo.jpg");
        Icon icn_logo = new ImageIcon(file);
        btn_logo.setIcon(icn_logo);
        btn_logo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel model = (DefaultTableModel)tab_commande.getModel();
                int rows = model.getRowCount();
                for(int i = rows - 1; i >=0; i--)
                {
                    model.removeRow(i);
                }
                mis_a_jour_total();
                lab_prix.setText(String.valueOf(total_prix));

                //Vider le vector commandes et ajouter les commandes au vector vitrine
                for( int i=0; i<commandes.size();i++)
                {
                    System.out.println("Ajouter vitrine");
                    vitrine.add(commandes.get(i));
                }

                System.out.println("VIder commandes");
                commandes.clear();

                System.out.println("\n");
                System.out.println("VITRINE");
                for(int i=0 ; i<vitrine.size(); i++)
                {

                    System.out.println (vitrine.elementAt(i).getNom()+" "+vitrine.elementAt(i).getQuantite()+" "+vitrine.elementAt(i).getPrix()+" "+vitrine.elementAt(i).getPerime()+ " "+vitrine.elementAt(i).getDate()+" "+vitrine.elementAt(i).getTime());

                }
                System.out.println("\n");
                System.out.println("COMMANDES");
                for(int i=0 ; i<commandes.size(); i++)
                {

                    System.out.println (commandes.elementAt(i).getNom()+" "+commandes.elementAt(i).getQuantite()+" "+commandes.elementAt(i).getPrix()+" "+commandes.elementAt(i).getPerime()+ " "+commandes.elementAt(i).getDate()+" "+commandes.elementAt(i).getTime());

                }


            }
        });

        JButton btn_payer = new JButton("PAYER");
       // btn_payer.setIcon(new ImageIcon("images/payer.jpg"));
        file = classLoader.getResource("images/payer.jpg");
        Icon icn_payer = new ImageIcon(file);
        btn_payer.setIcon(icn_payer);

        JLabel lblTotal = new JLabel("TOTAL");
        lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel lblVie = new JLabel("VIENNOISERIE");
        lblVie.setFont(new Font("Tahoma", Font.PLAIN, 18));

        btn_payer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(total_prix>0)
                {
                    //recuperer les nouveaux produits ajoutés à la BDD vitrine
                    vitrine=base.getVitrine(vitrine);

                    for(int i=0 ; i<vitrine.size(); i++)
                    {
                        String nul="null";
                        if(nul.equals(vitrine.elementAt(i).getNom()))
                        {
                            //Si l'element du vitrine à pour nom = null on va le supprimer
                            vitrine.remove(i);
                        }
                        System.out.println (vitrine.elementAt(i).getNom()+" "+vitrine.elementAt(i).getQuantite()+" "+vitrine.elementAt(i).getPrix()+" "+vitrine.elementAt(i).getPerime()+ " "+vitrine.elementAt(i).getDate()+" "+vitrine.elementAt(i).getTime());

                    }


                    Interface_paiement paiement=new Interface_paiement(vitrine,commandes,tab_commande,total_prix, lab_prix);
                    paiement.setVisible(true);
                    paiement.setLocationRelativeTo(null);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Merci d'ajouter des produits", "Commande Vide", JOptionPane.WARNING_MESSAGE);
                }

            }
        });





        JButton btn_crois = new JButton("Croissant");
        file = classLoader.getResource("images/croissant.jpg");
        Icon icn_crois = new ImageIcon(file);
        btn_crois.setIcon(icn_crois);
        //btn_crois.setIcon(new ImageIcon("images/croissant.jpg"));
        btn_crois.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("CROISSANT");
            }
        });

        JButton btn_painauchoc = new JButton("Pain_choc");
        file = classLoader.getResource("images/pain_chocolat.jpg");
        Icon icn_painauchoc = new ImageIcon(file);
        btn_painauchoc.setIcon(icn_painauchoc);
        //btn_painauchoc.setIcon(new ImageIcon("images/pain_chocolat.jpg"));
        btn_painauchoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("PAIN AU CHOCOLAT");
            }
        });

        JButton btn_sucre = new JButton("Brioche_sucre");
        file = classLoader.getResource("images/brioche_sucre.jpg");
        Icon icn_sucre = new ImageIcon(file);
        btn_sucre.setIcon(icn_sucre);
       // btn_sucre.setIcon(new ImageIcon("images/brioche_sucre.jpg"));
        btn_sucre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("BRIOCHE SUCRE");
            }
        });

        JButton btn_painaulait = new JButton("Pain_lait");
        file = classLoader.getResource("images/pain_lait.jpg");
        Icon icn_painaulait = new ImageIcon(file);
        btn_painaulait.setIcon(icn_painaulait);
       // btn_painaulait.setIcon(new ImageIcon("images/pain_lait.jpg"));
        btn_painaulait.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("PAIN AU LAIT");
            }
        });

        JButton btn_tartecitron = new JButton("Tarte_citron");
        file = classLoader.getResource("images/tarte_citron.jpg");
        Icon icn_tartecitron = new ImageIcon(file);
        btn_tartecitron.setIcon(icn_tartecitron);
       // btn_tartecitron.setIcon(new ImageIcon("images/tarte_citron.jpg"));
        btn_tartecitron.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("TARTE CITRON");
            }
        });

        JButton btn_tartepra = new JButton("Tarte_praline");
        file = classLoader.getResource("images/tarte_praline.jpg");
        Icon icn_tartepra = new ImageIcon(file);
        btn_tartepra.setIcon(icn_tartepra);
       // btn_tartepra.setIcon(new ImageIcon("images/tarte_praline.jpg"));
        btn_tartepra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("TARTE PRALINE");
            }
        });


        // Create columns names
        String columnNamesJeter[] = { "Nom", "Quantite" };
        // Create some data
        String dataValuesJeter[][] ={ };


      // tab_jeter_1 = new JTable( model_1 );
      //  tab_jeter_1.setRowHeight(27);




        // Create columns names
        String columnNames[] = { "Nom", "Quantite", "Value","Prix-unit" };

        // Create some data
        String dataValues[][] = { };

        // Create a new table instance
       // DefaultTableModel model = new DefaultTableModel(dataValues,columnNames);
        tab_commande = new JTable(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Nom", "Quantite", "Value","Prix-unit"}
        ) {
            Class[] columnTypes = new Class[] {
                    Object.class, Integer.class,Integer.class
            };
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        //tab_commande = new JTable(model);

        tab_commande.setFont(new java.awt.Font("Arial", 0, 20));
        tab_commande.getColumnModel().getColumn(0).setPreferredWidth(90);
        tab_commande.getColumnModel().getColumn(1).setPreferredWidth(30);
        tab_commande.getColumnModel().getColumn(2).setPreferredWidth(30);

        tab_commande.setRowHeight(27);

        //Hide the column prix-unit
        tab_commande.getColumnModel().getColumn(3).setMaxWidth(0);
        tab_commande.getColumnModel().getColumn(3).setMinWidth(0);
        tab_commande.getColumnModel().getColumn(3).setPreferredWidth(0);

        JLabel lblNom = new JLabel("NOM");
        lblNom.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel lblQuantite = new JLabel("QUANTITE");
        lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel lblValue = new JLabel("PRIX");
        lblValue.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JButton btn_plus= new JButton("+");
        btn_plus.setFont(new Font("Tahoma", Font.BOLD, 18));
        btn_plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {


                int rowSelected = tab_commande.getSelectedRow();
                String nom = tab_commande.getModel().getValueAt(rowSelected, 0).toString();

                gerer_produit_selectione(nom);

            }
        });

        JButton btn_less = new JButton("-");
        btn_less.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                int i;
                int rowSelected = tab_commande.getSelectedRow();
                //System.out.println(rowSelected);
                String nom=tab_commande.getModel().getValueAt(rowSelected, 0).toString();
                String ex=tab_commande.getModel().getValueAt(rowSelected, 1).toString();
                int quant=Integer.parseInt(ex);
                //	System.out.println(quant);

                //on garde en la variable i la position du premièr produit avec le même nom
                for(i=0 ; i<vitrine.size(); i++)
                {
                    if(nom.equals(commandes.elementAt(i).getNom()))
                    {
                        System.out.println(i);
                        break;
                    }
                }

                if(quant>1)
                {
                    Produit aux = new Produit(commandes.elementAt(i).getNom(),commandes.elementAt(i).getPrix(),commandes.elementAt(i).getQuantite(),commandes.elementAt(i).getDate(),commandes.elementAt(i).getTime(),commandes.elementAt(i).getPerime());
                    vitrine.add(aux);
                    commandes.remove(i);
                    quant--;
                    int rowSelectedless = tab_commande.getSelectedRow();
                    String prix_unit=tab_commande.getModel().getValueAt(rowSelectedless, 3).toString();
                    float prix_unitaire=Float.parseFloat(prix_unit);
                    float prix_total=prix_unitaire*quant;
                    //prix_total = (float)(Math.floor(prix_total * 100) / 100);
                    round(prix_total,1);
                    prix_total = (float)(Math.floor(prix_total * 10) / 10);
                    //Increment of the price
                    tab_commande.setValueAt(String.valueOf(prix_total), rowSelectedless, 2);
                    //refresh
                    tab_commande.repaint();
                    mis_a_jour_total();
                    lab_prix.setText(String.valueOf(total_prix));

                }
                else
                {
                    Produit aux = new Produit(commandes.elementAt(i).getNom(),commandes.elementAt(i).getPrix(),commandes.elementAt(i).getQuantite(),commandes.elementAt(i).getDate(),commandes.elementAt(i).getTime(),commandes.elementAt(i).getPerime());
                    vitrine.add(aux);
                    commandes.remove(i);
                    //System.out.println(rowSelected);
                    //Supprimer le row
                    DefaultTableModel model = new DefaultTableModel();
                    model = (DefaultTableModel) tab_commande.getModel();
                    model.removeRow(tab_commande.getSelectedRow());
                    tab_commande.repaint();
                    mis_a_jour_total();
                    lab_prix.setText(String.valueOf(total_prix));

                }

                //Mettre à jour la quantitè
                tab_commande.setValueAt(Integer.toString(quant), rowSelected, 1);
                tab_commande.repaint();

                System.out.println("\n");
                System.out.println("VITRINE");
                for(int j=0 ; j<vitrine.size(); j++)
                {

                    System.out.println (vitrine.elementAt(j).getNom()+" "+vitrine.elementAt(j).getQuantite()+" "+vitrine.elementAt(j).getPrix()+" "+vitrine.elementAt(j).getPerime()+ " "+vitrine.elementAt(j).getDate()+" "+vitrine.elementAt(j).getTime());

                }
                System.out.println("\n");
                System.out.println("COMMANDES");
                for(int l=0 ; l<commandes.size(); l++)
                {

                    System.out.println (commandes.elementAt(l).getNom()+" "+commandes.elementAt(l).getQuantite()+" "+commandes.elementAt(l).getPrix()+" "+commandes.elementAt(l).getPerime()+ " "+commandes.elementAt(l).getDate()+" "+commandes.elementAt(l).getTime());

                }

            }
        });
        btn_less.setFont(new Font("Tahoma", Font.BOLD, 18));


        lab_prix.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lab_prix.setText("0.0");

        JLabel lab_euro = new JLabel("\u20AC");
        lab_euro.setFont(new Font("Tahoma", Font.BOLD, 18));
        getContentPane().setLayout(null);
        getContentPane().add(pan_pain);

        JButton btn_flute = new JButton("Flute");
         file = classLoader.getResource("images/flute.jpg");
        Icon icn_flu = new ImageIcon(file);
        btn_flute.setIcon(icn_flu);
       // btn_flute.setIcon(new ImageIcon("images/flute.jpg"));
        btn_flute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gerer_produit_selectione("FLUTE");
            }
        });

        JButton btn_baguettes = new JButton("Baguette");

        file = classLoader.getResource("images/baguette.jpg");
        Icon icn_bag = new ImageIcon(file);
        btn_baguettes.setIcon(icn_bag);
       // btn_baguettes.setIcon(new ImageIcon("images/baguette.jpg"));
        btn_baguettes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("BAGUETTE");

            }
        });


        JLabel lab_pain = new JLabel("PAIN");
        lab_pain.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tab_jeter_1 = new JTable(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Nom", "Quantite"}
        ) {
            Class[] columnTypes = new Class[] {
                    Object.class, Integer.class
            };
           @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        tab_jeter_1.setFont(new java.awt.Font("Arial", 0, 20));

        JButton btn_jeter = new JButton("JETER");
        btn_jeter.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btn_jeter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                int rowSelected = tab_jeter_1.getSelectedRow();

                DefaultTableModel model_1 = new DefaultTableModel();
                model_1 = (DefaultTableModel) tab_jeter_1.getModel();

                String nom = tab_jeter_1.getModel().getValueAt(rowSelected, 0).toString();
                String quant = tab_jeter_1.getModel().getValueAt(rowSelected, 1).toString();
                int int_quan = Integer.parseInt(quant);



                for (int u = 0; u < jeter.size(); u++) {
                    int int_quanty = jeter.elementAt(u).getQuantite();
                    if (jeter.elementAt(u).getNom().equals(nom) && int_quan == int_quanty) {
                        model_1.removeRow(tab_jeter_1.getSelectedRow());
                        jeter.remove(u);
                        tab_jeter_1.repaint();
                        base.ajouterPerime(nom, int_quanty);
                        String mensaje = base.supprimerVitrine(nom,int_quanty);
                        JOptionPane.showMessageDialog(null, mensaje, "Jeter " + nom, JOptionPane.WARNING_MESSAGE);

                    }
                }


            }
        });

        tab_jeter_1.setRowHeight(40);
        GroupLayout gl_pan_pain = new GroupLayout(pan_pain);
        gl_pan_pain.setHorizontalGroup(
                gl_pan_pain.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_pain.createSequentialGroup()
                                .addGap(147)
                                .addComponent(lab_pain))
                        .addGroup(gl_pan_pain.createSequentialGroup()
                                .addGap(61)
                                .addComponent(btn_baguettes, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_pain.createSequentialGroup()
                                .addGap(61)
                                .addComponent(btn_flute, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_pain.createSequentialGroup()
                                .addGap(61)
                                .addComponent(btn_jeter))
                        .addGroup(gl_pan_pain.createSequentialGroup()
                                .addGap(61)
                                .addComponent(tab_jeter_1, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))
        );
        gl_pan_pain.setVerticalGroup(
                gl_pan_pain.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_pain.createSequentialGroup()
                                .addComponent(lab_pain)
                                .addGap(4)
                                .addComponent(btn_baguettes, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addGap(67)
                                .addComponent(btn_flute, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
                                .addGap(79)
                                .addComponent(btn_jeter, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                .addGap(4)
                                .addComponent(tab_jeter_1, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
        );
        pan_pain.setLayout(gl_pan_pain);
        getContentPane().add(pan_vienn);
        GroupLayout gl_pan_vienn = new GroupLayout(pan_vienn);
        gl_pan_vienn.setHorizontalGroup(
                gl_pan_vienn.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_vienn.createSequentialGroup()
                                .addGap(154)
                                .addComponent(lblVie))
                        .addGroup(gl_pan_vienn.createSequentialGroup()
                                .addGap(84)
                                .addComponent(btn_crois, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_vienn.createSequentialGroup()
                                .addGap(84)
                                .addComponent(btn_painauchoc, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_vienn.createSequentialGroup()
                                .addGap(84)
                                .addComponent(btn_sucre, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_vienn.createSequentialGroup()
                                .addGap(84)
                                .addComponent(btn_painaulait, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_vienn.createSequentialGroup()
                                .addGap(84)
                                .addComponent(btn_tartecitron, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_vienn.createSequentialGroup()
                                .addGap(84)
                                .addComponent(btn_tartepra, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
        );
        gl_pan_vienn.setVerticalGroup(
                gl_pan_vienn.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_vienn.createSequentialGroup()
                                .addGap(18)
                                .addComponent(lblVie)
                                .addGap(18)
                                .addComponent(btn_crois, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                                .addGap(14)
                                .addComponent(btn_painauchoc, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                                .addGap(8)
                                .addComponent(btn_sucre, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                                .addGap(8)
                                .addComponent(btn_painaulait, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                                .addGap(8)
                                .addComponent(btn_tartecitron, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                                .addGap(8)
                                .addComponent(btn_tartepra, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
        );
        pan_vienn.setLayout(gl_pan_vienn);
        getContentPane().add(pan_boisson);
        GroupLayout gl_pan_boisson = new GroupLayout(pan_boisson);
        gl_pan_boisson.setHorizontalGroup(
                gl_pan_boisson.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                .addGap(184)
                                .addComponent(lblBoisson))
                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                .addGap(97)
                                .addComponent(btn_coca, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                .addGap(97)
                                .addComponent(btn_fanta, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                .addGap(97)
                                .addComponent(btn_sprite, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                .addGap(97)
                                .addComponent(btn_oasis, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                .addGap(97)
                                .addComponent(btn_orange, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                .addGap(97)
                                .addComponent(btn_pomme, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                .addGap(97)
                                .addComponent(btn_raisin, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
        );
        gl_pan_boisson.setVerticalGroup(
                gl_pan_boisson.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                .addGap(32)
                                .addComponent(lblBoisson)
                                .addGap(18)
                                .addComponent(btn_coca, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                .addGap(41)
                                .addComponent(btn_fanta, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                .addGap(42)
                                .addComponent(btn_sprite, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                .addGap(38)
                                .addComponent(btn_oasis, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                .addGap(42)
                                .addComponent(btn_orange, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                .addGap(43)
                                .addComponent(btn_pomme, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                .addGap(37)
                                .addComponent(btn_raisin, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
        );
        pan_boisson.setLayout(gl_pan_boisson);
        getContentPane().add(pan_commande);
        GroupLayout gl_pan_commande = new GroupLayout(pan_commande);
        gl_pan_commande.setHorizontalGroup(
                gl_pan_commande.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_commande.createSequentialGroup()
                                .addGap(9)
                                .addComponent(btn_logo, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
                                .addGap(30)
                                .addComponent(btn_decon))
                        .addGroup(gl_pan_commande.createSequentialGroup()
                                .addGap(123)
                                .addComponent(btn_plus)
                                .addGap(66)
                                .addComponent(btn_less))
                        .addGroup(gl_pan_commande.createSequentialGroup()
                                .addGap(70)
                                .addComponent(lblNom)
                                .addGap(118)
                                .addComponent(lblQuantite)
                                .addGap(77)
                                .addComponent(lblValue, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_commande.createSequentialGroup()
                                .addGap(9)
                                .addComponent(tab_commande, GroupLayout.PREFERRED_SIZE, 468, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pan_commande.createSequentialGroup()
                                .addGap(62)
                                .addComponent(lblTotal)
                                .addGap(54)
                                .addComponent(lab_prix, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                                .addGap(9)
                                .addComponent(lab_euro))
                        .addGroup(gl_pan_commande.createSequentialGroup()
                                .addGap(9)
                                .addComponent(btn_payer, GroupLayout.PREFERRED_SIZE, 468, GroupLayout.PREFERRED_SIZE))
        );
        gl_pan_commande.setVerticalGroup(
                gl_pan_commande.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_commande.createSequentialGroup()
                                .addGroup(gl_pan_commande.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btn_logo, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_decon))
                                .addGap(47)
                                .addGroup(gl_pan_commande.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btn_plus, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_less, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                                .addGap(8)
                                .addGroup(gl_pan_commande.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblNom)
                                        .addComponent(lblQuantite)
                                        .addComponent(lblValue))
                                .addGap(8)
                                .addComponent(tab_commande, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
                                .addGap(8)
                                .addGroup(gl_pan_commande.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lab_prix, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_pan_commande.createSequentialGroup()
                                                .addGap(8)
                                                .addComponent(lab_euro)))
                                .addGap(8)
                                .addComponent(btn_payer, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
        );
        pan_commande.setLayout(gl_pan_commande);
        this.setExtendedState(MAXIMIZED_BOTH);

        //TODO faire le timer de jeter
        try {
            Thread.sleep(3000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        TimerTask timerTask = new TimerTask() {
            public void run() {
                timer_refresh_produits_jeter();
                java.util.Date maDate = new java.util.Date();
                System.out.println("Tâche vérification péremption vitrine lancée le " + maDate.toString());
            }
        };

        Timer timer = new Timer();   // creation du timer
        timer.scheduleAtFixedRate(timerTask, 0, 120000); //timer répétitive toutes les 2 minutes
        System.out.println("Tâche vérification péremption vitrine lancée toutes les 2 minutes");
    }


}



