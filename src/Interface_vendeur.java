/**
 * Created by Elio on 29/11/2014.
 */
import java.awt.EventQueue;

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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.GridLayout;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTable;



public class Interface_vendeur extends JFrame {
    private JTable tab_commande;
    private JTable tab_jeter;
    private float total_prix=0;
    //vector produits
    // Produit vitrine[] = null;
    Vector<Produit> vitrine = new Vector<Produit>();
    Vector<Produit> commandes= new Vector<Produit>();
    Vector<Produit> jeter= new Vector<Produit>();

    final JLabel lab_prix = new JLabel("");
    ///////////////////////////PARTIE TIMER////////////////////////////////////////////////////////////////
    /*




	*/
    ///////////////////////////////////////////////////////////////////////////////////////////////////////


    void timer_refresh_produits_jeter()
    {
        for(int i=0 ; i<vitrine.size(); i++)
        {

            //System.out.println (vitrine.elementAt(i).getNom()+" "+vitrine.elementAt(i).getQuantite()+" "+vitrine.elementAt(i).getPrix()+" "+vitrine.elementAt(i).getPerime()+ " "+vitrine.elementAt(i).getDate()+" "+vitrine.elementAt(i).getTime());
            if(vitrine.elementAt(i).getPerime()== 1)
            {

                JOptionPane.showMessageDialog(null, vitrine.elementAt(i).getNom()+" perimè", "Perimè "+ vitrine.elementAt(i).getNom(), JOptionPane.WARNING_MESSAGE);
                Produit aux = new Produit(vitrine.elementAt(i).getNom(),vitrine.elementAt(i).getPrix(),vitrine.elementAt(i).getQuantite(),vitrine.elementAt(i).getDate(),vitrine.elementAt(i).getTime(),vitrine.elementAt(i).getPerime());
                vitrine.remove(i);
                ajouter_produit_a_jeter(aux);
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

    void ajouter_produit_a_jeter(Produit prod_baguette)
    {
        Produit aux = new Produit(prod_baguette.getNom(),prod_baguette.getPrix(),prod_baguette.getQuantite(),prod_baguette.getDate(),prod_baguette.getTime(),prod_baguette.getPerime());


        DefaultTableModel model = (DefaultTableModel) tab_jeter.getModel();
        model.addRow(new Object[]{aux.getNom(),Float.toString(aux.getQuantite())});
        jeter.add(aux);

        System.out.println("\n");
        System.out.println("JETER");
        for(int i=0 ; i<jeter.size(); i++)
        {

            System.out.println (jeter.elementAt(i).getNom()+" "+jeter.elementAt(i).getQuantite()+" "+jeter.elementAt(i).getPrix()+" "+jeter.elementAt(i).getPerime()+ " "+jeter.elementAt(i).getDate()+" "+jeter.elementAt(i).getTime());

        }
        System.out.println("\n");

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

//TODO      hacer una funcion con el codigo de abajo

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
                //System.out.println("test para las dates=1 "+prod_baguette.getNom()+ " "+prod_baguette.getDate()+ " " + prod_baguette.getTime() );
                //ajouter au vector de la commande
                //	System.out.println("ANADIR A PRODUIT =1 "+ nom+" "+prod_baguette.getQuantite());
                ajouter_produit_a_commande(prod_baguette,nom);
                ajouter_produit_a_jtable(prod_baguette,nom);
                // TODO supprimer le produit du vector vitrine et de la BDD
                //pour le supprimer, je vais modifier le nom et après il faut justement faire la suppresion par une requete de la BDD
                //System.out.println("indice del producto a suprimir = "+pos_produit_en_vitrine);
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

        setTitle("Vendeur");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1446, 879);

        //Connection avec la BDD
        final GestionBDD base= new GestionBDD();
        vitrine=base.getVitrine(vitrine);
        for(int i=0 ; i<vitrine.size(); i++)
        {

            System.out.println (vitrine.elementAt(i).getNom()+" "+vitrine.elementAt(i).getQuantite()+" "+vitrine.elementAt(i).getPrix()+" "+vitrine.elementAt(i).getPerime()+ " "+vitrine.elementAt(i).getDate()+" "+vitrine.elementAt(i).getTime());

        }
        System.out.println("\n");


        JPanel pan_pain = new JPanel();

        JPanel pan_vienn = new JPanel();

        JPanel pan_boisson = new JPanel();



        JLabel lblBoisson = new JLabel("BOISSON");
        lblBoisson.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JButton btn_raisin = new JButton("Raisin");
        btn_raisin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("Raisin");
            }
        });

        JButton btn_pomme = new JButton("Pomme");
        btn_pomme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("Pomme");
            }
        });

        JButton btn_orange = new JButton("Orange");
        btn_orange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("Orange");
            }
        });

        JButton btn_oasis = new JButton("Oasis");
        btn_oasis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("Oasis");
            }
        });

        JButton btn_sprite = new JButton("Sprite");
        btn_sprite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("Sprite");
            }
        });

        JButton btn_fanta = new JButton("Fanta");
        btn_fanta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("Fanta");
            }
        });

        JButton btn_coca = new JButton("Coca_cola");
        btn_coca.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("Coca cola");
            }
        });
        GroupLayout gl_pan_boisson = new GroupLayout(pan_boisson);
        gl_pan_boisson.setHorizontalGroup(
                gl_pan_boisson.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                .addContainerGap(97, Short.MAX_VALUE)
                                .addGroup(gl_pan_boisson.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                                .addGroup(gl_pan_boisson.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(btn_pomme, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_orange, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_oasis, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_sprite, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_fanta, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_coca, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_raisin, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
                                                .addGap(79))
                                        .addGroup(Alignment.TRAILING, gl_pan_boisson.createSequentialGroup()
                                                .addComponent(lblBoisson)
                                                .addGap(171))))
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
                                .addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addComponent(btn_pomme, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                .addGap(37)
                                .addComponent(btn_raisin, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                .addGap(36))
        );
        this.setExtendedState(MAXIMIZED_BOTH);
        pan_boisson.setLayout(gl_pan_boisson);

        JPanel pan_commande = new JPanel();
        pan_commande.setLayout(null);

        JButton btn_decon = new JButton("Deconnection");
        btn_decon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //TODO FAIRE LA DECONNEXION DU VENDEUR ET ARRIVER A LA PAGE CONNEXION
            }
        });
        btn_decon.setBounds(243, 0, 136, 36);
        pan_commande.add(btn_decon);

        JButton btn_logo = new JButton("logo");
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
        btn_logo.setBounds(10, 11, 209, 122);
        pan_commande.add(btn_logo);

        JButton btn_payer = new JButton("PAYER");
        btn_payer.setBounds(41, 724, 442, 199);
        pan_commande.add(btn_payer);

        JLabel lblTotal = new JLabel("TOTAL");
        lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTotal.setBounds(75, 678, 81, 35);
        pan_commande.add(lblTotal);

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
        btn_crois.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("Croissant");
            }
        });

        JButton btn_painauchoc = new JButton("Pain_choc");
        btn_painauchoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("Pain au chocolat");
            }
        });

        JButton btn_sucre = new JButton("Brioche_sucre");
        btn_sucre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("Brioche sucre");
            }
        });

        JButton btn_painaulait = new JButton("Pain_lait");
        btn_painaulait.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("Pain au lait");
            }
        });

        JButton btn_tartecitron = new JButton("Tarte_citron");
        btn_tartecitron.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("Tarte citron");
            }
        });

        JButton btn_tartepra = new JButton("Tarte_praline");
        btn_tartepra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("Tarte praline");
            }
        });
        GroupLayout gl_pan_vienn = new GroupLayout(pan_vienn);
        gl_pan_vienn.setHorizontalGroup(
                gl_pan_vienn.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_vienn.createSequentialGroup()
                                .addGroup(gl_pan_vienn.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_pan_vienn.createSequentialGroup()
                                                .addGap(132)
                                                .addComponent(lblVie))
                                        .addGroup(gl_pan_vienn.createSequentialGroup()
                                                .addGap(84)
                                                .addGroup(gl_pan_vienn.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(btn_crois, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_tartepra, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_painauchoc, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_sucre, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_painaulait, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_tartecitron, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(40, Short.MAX_VALUE))
        );
        gl_pan_vienn.setVerticalGroup(
                gl_pan_vienn.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_vienn.createSequentialGroup()
                                .addGap(23)
                                .addComponent(lblVie)
                                .addGap(18)
                                .addComponent(btn_crois, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addGap(59)
                                .addComponent(btn_painauchoc, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addGap(59)
                                .addComponent(btn_sucre, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addGap(62)
                                .addComponent(btn_painaulait, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addComponent(btn_tartecitron, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addGap(59)
                                .addComponent(btn_tartepra, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addGap(31))
        );
        pan_vienn.setLayout(gl_pan_vienn);



        JLabel lab_pain = new JLabel("PAIN");
        lab_pain.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JButton btn_baguettes = new JButton("Baguette");
        btn_baguettes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gerer_produit_selectione("Baguette");

            }
        });

        JButton btn_flute = new JButton("Flute");
        btn_flute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gerer_produit_selectione("Flute");
            }
        });


        // Create columns names
        String columnNamesJeter[] = { "Nom", "Quantite" };

        // Create some data
        String dataValuesJeter[][] ={ };

        tab_jeter = new JTable( dataValuesJeter, columnNamesJeter );
        DefaultTableModel model_1 = new DefaultTableModel(dataValuesJeter,columnNamesJeter);
        tab_jeter = new JTable(model_1);
        tab_jeter.setFont(new java.awt.Font("Arial", 0, 20));

        tab_jeter.getColumnModel().getColumn(0).setPreferredWidth(90);
        tab_jeter.getColumnModel().getColumn(1).setPreferredWidth(30);

        tab_jeter.setRowHeight(60);

        JButton btn_jeter = new JButton("JETER");
        btn_jeter.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btn_jeter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                int rowSelected = tab_jeter.getSelectedRow();

                String nom=tab_jeter.getModel().getValueAt(rowSelected, 0).toString();


                //on garde en la variable i la position du premièr produit avec le même nom
                for(int i=0 ; i<jeter.size(); i++)
                {
                    if(nom.equals(jeter.elementAt(i).getNom()))
                    {
                        jeter.remove(i);

                        DefaultTableModel model = new DefaultTableModel();
                        model = (DefaultTableModel) tab_jeter.getModel();
                        model.removeRow(tab_jeter.getSelectedRow());
                        tab_commande.repaint();
                        break;
                    }
                }

                //System.out.println(rowSelected);
                //Supprimer le row
                DefaultTableModel model_1 = new DefaultTableModel();
                model_1 = (DefaultTableModel) tab_jeter.getModel();
                model_1.removeRow(tab_jeter.getSelectedRow());
                tab_jeter.repaint();

            }
        });

        GroupLayout gl_pan_pain = new GroupLayout(pan_pain);
        gl_pan_pain.setHorizontalGroup(
                gl_pan_pain.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_pan_pain.createSequentialGroup()
                                .addGroup(gl_pan_pain.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_pan_pain.createSequentialGroup()
                                                .addGap(148)
                                                .addComponent(lab_pain))
                                        .addGroup(gl_pan_pain.createSequentialGroup()
                                                .addGap(61)
                                                .addGroup(gl_pan_pain.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(btn_flute, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_baguettes, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(gl_pan_pain.createSequentialGroup()
                                                .addGap(23)
                                                .addComponent(tab_jeter, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_pan_pain.createSequentialGroup()
                                                .addGap(80)
                                                .addComponent(btn_jeter, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        gl_pan_pain.setVerticalGroup(
                gl_pan_pain.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_pain.createSequentialGroup()
                                .addGap(27)
                                .addComponent(lab_pain)
                                .addGap(18)
                                .addComponent(btn_baguettes, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
                                .addGap(36)
                                .addComponent(btn_flute, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(btn_jeter, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(tab_jeter, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        pan_pain.setLayout(gl_pan_pain);
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(pan_pain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                                .addComponent(pan_vienn, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(pan_boisson, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(pan_commande, GroupLayout.PREFERRED_SIZE, 577, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(pan_vienn, GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
                                        .addComponent(pan_boisson, GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
                                        .addComponent(pan_pain, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(11)
                                                .addComponent(pan_commande, GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)))
                                .addContainerGap())
        );






        //panel.add(table1);



        // Create columns names
        String columnNames[] = { "Nom", "Quantite", "Value","Prix-unit" };

        // Create some data
        String dataValues[][] =
                {


                };

        // Create a new table instance
        //table = new JTable( dataValues, columnNames );
        DefaultTableModel model = new DefaultTableModel(dataValues,columnNames);
        tab_commande = new JTable(model);

        tab_commande.setFont(new java.awt.Font("Arial", 0, 20));
        tab_commande.setBounds(41, 253, 442, 414);
        tab_commande.getColumnModel().getColumn(0).setPreferredWidth(90);
        tab_commande.getColumnModel().getColumn(1).setPreferredWidth(30);
        tab_commande.getColumnModel().getColumn(2).setPreferredWidth(30);

        tab_commande.setRowHeight(27);

        //Hide the column prix-unit
        tab_commande.getColumnModel().getColumn(3).setMaxWidth(0);
        tab_commande.getColumnModel().getColumn(3).setMinWidth(0);
        tab_commande.getColumnModel().getColumn(3).setPreferredWidth(0);

        pan_commande.add(tab_commande);

        JLabel lblNom = new JLabel("NOM");
        lblNom.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNom.setBounds(66, 228, 46, 14);
        pan_commande.add(lblNom);

        JLabel lblQuantite = new JLabel("QUANTITE");
        lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblQuantite.setBounds(230, 224, 87, 22);
        pan_commande.add(lblQuantite);

        JLabel lblValue = new JLabel("PRIX");
        lblValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblValue.setBounds(397, 226, 73, 18);
        pan_commande.add(lblValue);

        JButton btn_plus= new JButton("+");
        btn_plus.setFont(new Font("Tahoma", Font.BOLD, 18));
        btn_plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {


                int rowSelected = tab_commande.getSelectedRow();
                String nom = tab_commande.getModel().getValueAt(rowSelected, 0).toString();

                gerer_produit_selectione(nom);

            }
        });
        btn_plus.setBounds(106, 180, 65, 37);
        pan_commande.add(btn_plus);

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
        btn_less.setBounds(215, 180, 65, 37);
        pan_commande.add(btn_less);


        lab_prix.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lab_prix.setBounds(172, 676, 108, 39);
        lab_prix.setText("0.0");

        pan_commande.add(lab_prix);

        JLabel lab_euro = new JLabel("\u20AC");
        lab_euro.setFont(new Font("Tahoma", Font.BOLD, 18));
        lab_euro.setBounds(290, 686, 27, 27);
        pan_commande.add(lab_euro);

        //TODO faire le timer de jeter
        timer_refresh_produits_jeter();

        getContentPane().setLayout(groupLayout);
        this.setExtendedState(MAXIMIZED_BOTH);
    }

}



