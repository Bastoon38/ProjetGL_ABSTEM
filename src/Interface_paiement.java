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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.GridLayout;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.SwingConstants;



public class Interface_paiement extends JFrame {

    private float total_prix=0;
    private boolean paye=false;
    private boolean billet=false;
    private JTextField textField;


    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    //fonction qui returne 20 s'on ne trouve pas le produit dans la liste et sinon il returne le row du produit

    /**
     * Create the frame.
     */
    public Interface_paiement(float total_ihm_vendeur) {
        this.total_prix=total_ihm_vendeur;
        //System.out.println(total_ihm_vendeur);
        setTitle("Paiement");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1446, 879);
        JPanel pan_pain = new JPanel();

        JPanel pan_vienn = new JPanel();

        JPanel pan_boisson = new JPanel();
        final JButton btnBillet = new JButton("Billet");
        final JButton btnCheque = new JButton("Cheque");
        final JButton btnCb = new JButton("CB");
        btnCb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                paye=true;
                billet=false;
                btnCb.setBorderPainted(true);
                btnCb.setBorder(BorderFactory.createLineBorder(Color.green,12));
                btnBillet.setBorderPainted(false);
                btnCheque.setBorderPainted(false);
            }
        });
        GroupLayout gl_pan_boisson = new GroupLayout(pan_boisson);
        gl_pan_boisson.setHorizontalGroup(
                gl_pan_boisson.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                .addGap(80)
                                .addComponent(btnCb, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(83, Short.MAX_VALUE))
        );
        gl_pan_boisson.setVerticalGroup(
                gl_pan_boisson.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_boisson.createSequentialGroup()
                                .addGap(77)
                                .addComponent(btnCb, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(602, Short.MAX_VALUE))
        );
        this.setExtendedState(MAXIMIZED_BOTH);
        pan_boisson.setLayout(gl_pan_boisson);


        btnBillet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paye=true;
                billet=true;
                btnBillet.setBorderPainted(true);
                btnBillet.setBorder(BorderFactory.createLineBorder(Color.green,12));
                btnCb.setBorderPainted(false);
                btnCheque.setBorderPainted(false);
            }
        });

        JButton btnPayer = new JButton("payer");
        btnPayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(paye)
                {
                    if(billet)
                    {
                        Interface_billet ibillet=new Interface_billet(total_prix);
                        ibillet.setVisible(true);
                        ibillet.setLocationRelativeTo(null);
                        setVisible(false);
                    }
                    else
                    {
                        //JOptionPane.showMessageDialog(null, "Paiement accepté", "Paiement accepté", JOptionPane.WARNING_MESSAGE);
                        int result = JOptionPane.showConfirmDialog((Component) null, "Voulez vous confirmer le paiement ?",
                                "Confirmer paiement ?", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.YES_OPTION)
                        {

                            //Supprimer les produits de la commande dans la base de donnès

                            JOptionPane.showMessageDialog(null, "Paiement accepté", "Paiement accepté", JOptionPane.WARNING_MESSAGE);
                            //Aller à la page principal du vendeur
                            dispose();
                            // Il faut mettre à zero l'interface vendeur (supprimer les produits dans la base de données et vider la liste des commandes)

                        }
                        else
                        {

                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Choisir le mode de paiement", "mode paiement", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton btnAnnuler = new JButton("annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        GroupLayout gl_pan_vienn = new GroupLayout(pan_vienn);
        gl_pan_vienn.setHorizontalGroup(
                gl_pan_vienn.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_pan_vienn.createSequentialGroup()
                                .addContainerGap(98, Short.MAX_VALUE)
                                .addGroup(gl_pan_vienn.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnAnnuler, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnPayer, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBillet, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE))
                                .addGap(96))
        );
        gl_pan_vienn.setVerticalGroup(
                gl_pan_vienn.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_vienn.createSequentialGroup()
                                .addGap(72)
                                .addComponent(btnBillet, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
                                .addGap(185)
                                .addComponent(btnPayer, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addGap(33)
                                .addComponent(btnAnnuler, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(49, Short.MAX_VALUE))
        );
        pan_vienn.setLayout(gl_pan_vienn);


        btnCheque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                paye=true;
                billet=false;
                btnCb.setBorderPainted(false);
                btnBillet.setBorderPainted(false);
                btnCheque.setBorderPainted(true);
                btnCheque.setBorder(BorderFactory.createLineBorder(Color.green,12));
            }
        });

        JLabel label = new JLabel();
        label.setFont(new Font("Tahoma", Font.PLAIN, 44));

        textField = new JTextField(100);
        textField.setHorizontalAlignment(SwingConstants.LEFT);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 40));
        textField.setColumns(10);
        textField.setText(Float.toString(total_prix));

        JLabel lab_payer = new JLabel("TOTAL PAYER");
        lab_payer.setFont(new Font("Tahoma", Font.PLAIN, 28));

        GroupLayout gl_pan_pain = new GroupLayout(pan_pain);
        gl_pan_pain.setHorizontalGroup(
                gl_pan_pain.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_pain.createSequentialGroup()
                                .addGap(61)
                                .addGroup(gl_pan_pain.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(btnCheque, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_pan_pain.createSequentialGroup()
                                                .addGroup(gl_pan_pain.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(lab_payer, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                                                .addGap(28)
                                                .addComponent(label, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                                                .addGap(48)))
                                .addContainerGap(52, Short.MAX_VALUE))
        );
        gl_pan_pain.setVerticalGroup(
                gl_pan_pain.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pan_pain.createSequentialGroup()
                                .addGap(67)
                                .addComponent(btnCheque, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                                .addGroup(gl_pan_pain.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_pan_pain.createSequentialGroup()
                                                .addComponent(lab_payer, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(gl_pan_pain.createSequentialGroup()
                                                .addComponent(label, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
                                                .addGap(209))))
        );
        pan_pain.setLayout(gl_pan_pain);
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(pan_pain, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(pan_vienn, GroupLayout.PREFERRED_SIZE, 667, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(pan_boisson, GroupLayout.PREFERRED_SIZE, 636, GroupLayout.PREFERRED_SIZE)
                                .addGap(3))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(pan_boisson, GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
                        .addComponent(pan_vienn, GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(pan_pain, GroupLayout.PREFERRED_SIZE, 982, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );


        getContentPane().setLayout(groupLayout);
        this.setExtendedState(MAXIMIZED_BOTH);
    }
}

