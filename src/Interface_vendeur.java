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
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTable;



public class Interface_vendeur extends JFrame {
    private JTable tab_commande;
    private JTable tab_jeter;
    private float total_prix=0;
    ArrayList<Produit> vitrine =new ArrayList <Produit>();
    ArrayList<Produit> commande =new ArrayList <Produit>();
    ArrayList<Produit> jeter =new ArrayList <Produit>();
    // GestionBDD bdd =new GestionBDD();

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
        //Vector<Produit> vecProduit = new Vector<Produit>();
        //vecProduit=bdd.recupStockVitrine();


        final JLabel lab_prix = new JLabel("");
        JPanel pan_pain = new JPanel();

        JPanel pan_vienn = new JPanel();

        JPanel pan_boisson = new JPanel();

        JLabel lblBoisson = new JLabel("BOISSON");
        lblBoisson.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JButton btn_raisin = new JButton("Raisiin");
        btn_raisin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean bases_donnees=false;
                // S'il y a des produits dans la base de donnes

                if(bases_donnees)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas de minute maid raisin", "minute maid raisin", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Raisin");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Raisin", "1", "2","2"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
            }
        });

        JButton btn_pomme = new JButton("Pomme");
        btn_pomme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean base_de_datos=false;
                // S'il y a des produits dans la base de donnes
                if(base_de_datos)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas de minute maid pomme", "minute maid pomme", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Pomme");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Pomme", "1", "2","2"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
            }
        });

        JButton btn_orange = new JButton("Orange");
        btn_orange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean base_de_datos=false;
                // S'il y a des produits dans la base de donnes
                if(base_de_datos)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas de minute maid Orange", "minute maid orange", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Orange");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Orange", "1", "2","2"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
            }
        });

        JButton btn_oasis = new JButton("Oasis");
        btn_oasis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean base_de_datos=false;
                // S'il y a des produits dans la base de donnes
                if(base_de_datos)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas Oasis", "Oasis", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Oasis");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Oasis", "1", "2","2"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
            }
        });

        JButton btn_sprite = new JButton("Sprite");
        btn_sprite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean base_de_datos=false;
                // S'il y a des produits dans la base de donnes
                if(base_de_datos)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas de Sprite", "Sprite", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Sprite");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Sprite", "1", "2","2"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
            }
        });

        JButton btn_fanta = new JButton("Fanta");
        btn_fanta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean base_de_datos=false;
                // S'il y a des produits dans la base de donnes
                if(base_de_datos)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas de Fanta", "Fanta", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Fanta");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Fanta", "1", "2","2"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
            }
        });

        JButton btn_coca = new JButton("Coca_cola");
        btn_coca.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean base_de_datos=false;
                // S'il y a des produits dans la base de donnes
                if(base_de_datos)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas de Coca cola", "Coca cola", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Coca_cola");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Coca_cola", "1", "2","2"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
            }
        });
        GroupLayout gl_pan_boisson = new GroupLayout(pan_boisson);
        gl_pan_boisson.setHorizontalGroup(
                gl_pan_boisson.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                .addGap(120)
                                .addComponent(lblBoisson)
                                .addContainerGap(124, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, gl_pan_boisson.createSequentialGroup()
                                .addContainerGap(97, Short.MAX_VALUE)
                                .addGroup(gl_pan_boisson.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btn_pomme, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_orange, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_oasis, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_sprite, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_fanta, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_coca, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_raisin, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
                                .addGap(79))
        );
        gl_pan_boisson.setVerticalGroup(
                gl_pan_boisson.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                .addGap(23)
                                .addComponent(lblBoisson)
                                .addGap(27)
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
            }
        });
        btn_logo.setBounds(10, 11, 209, 122);
        pan_commande.add(btn_logo);

        JButton btn_payer = new JButton("PAYER");
        btn_payer.setBounds(73, 724, 251, 89);
        pan_commande.add(btn_payer);

        btn_payer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(total_prix>0)
                {
                    Interface_paiement paiement=new Interface_paiement(total_prix);
                    paiement.setVisible(true);
                    paiement.setLocationRelativeTo(null);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Merci d'ajouter des produits", "Commande Vide", JOptionPane.WARNING_MESSAGE);
                }

            }
        });



        JLabel lblTotal = new JLabel("TOTAL");
        lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTotal.setBounds(75, 678, 81, 35);
        pan_commande.add(lblTotal);

        JLabel lblVie = new JLabel("VIENNOISERIE");
        lblVie.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JButton btn_crois = new JButton("Croissant");
        btn_crois.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean base_de_datos=false;
                // S'il y a des produits dans la base de donnes
                if(base_de_datos)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas des croissants", "croissant", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Croissant");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Croissant", "1", "1.2","1.2"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
            }
        });

        JButton btn_painauchoc = new JButton("Pain_choc");
        btn_painauchoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean base_de_datos=false;
                // S'il y a des produits dans la base de donnes
                if(base_de_datos)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas des pains au chocolat", "pain au chocolat", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Pain_choc");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Pain_choc", "1", "1.2","1.2"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
            }
        });

        JButton btn_sucre = new JButton("Brioche_sucre");
        btn_sucre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean base_de_datos=false;
                // S'il y a des produits dans la base de donnes
                if(base_de_datos)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas des brioches", "briochest", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Brioche_sucre");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Brioche_sucre", "1", "1.5","1.5"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
            }
        });

        JButton btn_painaulait = new JButton("Pain_lait");
        btn_painaulait.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean base_de_datos=false;
                // S'il y a des produits dans la base de donnes
                if(base_de_datos)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas des pains au lait", "pains au lait", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Pain_lait");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Pain_lait", "1", "1.5","1.5"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
            }
        });

        JButton btn_tartecitron = new JButton("Tarte_citron");
        btn_tartecitron.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                boolean base_de_datos=false;
                // S'il y a des produits dans la base de donnes
                if(base_de_datos)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas des tartes au citron", "tarte au citron", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Tarte_citron");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Tarte_citron", "1", "1.8","1.8"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
            }
        });

        JButton btn_tartepra = new JButton("Tarte_praline");
        btn_tartepra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean base_de_datos=false;
                // S'il y a des produits dans la base de donnes
                if(base_de_datos)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas des tartes praline", "tarte praline", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Tarte_praline");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Tarte_praline", "1", "1.8","1.8"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
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
                boolean base_de_datos=false;
                // S'il y a des produits dans la base de donnes
                if(base_de_datos)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas des baguettes", "baguettes", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Baguette");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Baguette", "1", "0.8","0.8"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
            }
        });

        JButton btn_flute = new JButton("Flute");
        btn_flute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean base_de_datos=false;
                // S'il y a des produits dans la base de donnes
                if(base_de_datos)
                {
                    JOptionPane.showMessageDialog(null, "Il n'a pas des flutes", "flute", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    //parcourir la liste des produits dans la commande
                    int ver=verifier("Flute");
                    if(ver!=20)
                    {

                        //System.out.println(rowSelected);
                        String ex = tab_commande.getModel().getValueAt(ver, 1).toString();
                        int quant=Integer.parseInt(ex);
                        quant++;
                        String prix_unit=tab_commande.getModel().getValueAt(ver, 3).toString();
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
                        model.addRow(new Object[]{"Flute", "1", "1","1"});
                        mis_a_jour_total();
                        lab_prix.setText(String.valueOf(total_prix));

                    }
                }
            }
        });


        // Create columns names
        String columnNamesJeter[] = { "Nom", "Quantite" };

        // Create some data
        String dataValuesJeter[][] =
                {

                        { "Flute", "4" },
                        { "Croissant", "12" },


                };

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
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(11)
                                .addComponent(pan_commande, GroupLayout.PREFERRED_SIZE, 837, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(142, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(pan_vienn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
                                        .addComponent(pan_boisson, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
                                        .addComponent(pan_pain, GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE))
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
        tab_commande.setBounds(41, 253, 318, 414);
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
        lblQuantite.setBounds(193, 224, 87, 22);
        pan_commande.add(lblQuantite);

        JLabel lblValue = new JLabel("PRIX");
        lblValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblValue.setBounds(306, 224, 73, 18);
        pan_commande.add(lblValue);

        JButton btn_plus= new JButton("+");
        btn_plus.setFont(new Font("Tahoma", Font.BOLD, 18));
        btn_plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                int rowSelected = tab_commande.getSelectedRow();
                //System.out.println(rowSelected);
                String ex = tab_commande.getModel().getValueAt(rowSelected, 1).toString();
                int quant=Integer.parseInt(ex);
                quant++;
                String prix_unit=tab_commande.getModel().getValueAt(rowSelected, 3).toString();
                float prix_unitaire=Float.parseFloat(prix_unit);
                float prix_total=prix_unitaire*quant;
                //prix_total = (float)(Math.floor(prix_total * 100) / 100);
                round(prix_total,1);
                prix_total = (float)(Math.floor(prix_total * 10) / 10);
                //Increment of the price
                tab_commande.setValueAt(String.valueOf(prix_total), rowSelected, 2);
                //Increment of the quantity
                tab_commande.setValueAt(Integer.toString(quant), rowSelected, 1);
                //refresh
                tab_commande.repaint();
                mis_a_jour_total();
                lab_prix.setText(String.valueOf(total_prix));
            }
        });
        btn_plus.setBounds(106, 180, 65, 37);
        pan_commande.add(btn_plus);

        JButton btn_less = new JButton("-");
        btn_less.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int rowSelected = tab_commande.getSelectedRow();
                //System.out.println(rowSelected);
                String ex=tab_commande.getModel().getValueAt(rowSelected, 1).toString();
                int quant=Integer.parseInt(ex);
                //	System.out.println(quant);
                if(quant>1)
                {
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
                    //System.out.println(rowSelected);
                    //Supprimer le row
                    DefaultTableModel model = new DefaultTableModel();
                    model = (DefaultTableModel) tab_commande.getModel();
                    model.removeRow(tab_commande.getSelectedRow());
                    tab_commande.repaint();
                    mis_a_jour_total();
                    lab_prix.setText(String.valueOf(total_prix));

                }

                //System.out.println(quant);
                tab_commande.setValueAt(Integer.toString(quant), rowSelected, 1);
                tab_commande.repaint();
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


        getContentPane().setLayout(groupLayout);
        this.setExtendedState(MAXIMIZED_BOTH);
    }

}



