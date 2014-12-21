import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;


public class Interface_billet extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private float total_prix=100;



    /**
     * Create the frame.
     */
    public Interface_billet(Vector <Produit> vitrine, final Vector <Produit> commandes, final JTable tab_commande,float total, final JLabel lab_total) {

        this.total_prix=total;
        setTitle("Billet");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1190, 840);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        final JButton btnValider = new JButton("VALIDER");
        btnValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Supprimer la liste des produits dans la base de données et fermer les Frames et mis à zero la liste des produits
                int result = JOptionPane.showConfirmDialog((Component) null, "Voulez vous confirmer le paiement ?",
                        "Confirmer paiement ?", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    lab_total.setText(String.valueOf(0));
                    //Vider le Jtable du commandes
                    DefaultTableModel model = (DefaultTableModel)tab_commande.getModel();
                    int rows = model.getRowCount();
                    for(int i = rows - 1; i >=0; i--)
                    {
                        model.removeRow(i);
                    }


                    System.out.println("COMMANDES dans paiement");

                    //Connection avec la BDD
                    GestionBDD base= new GestionBDD();
                    for(int i=0 ; i<commandes.size(); i++)
                    {

                        System.out.println (commandes.elementAt(i).getNom()+" "+commandes.elementAt(i).getQuantite()+" "+commandes.elementAt(i).getPrix()+" "+commandes.elementAt(i).getPerime()+ " "+commandes.elementAt(i).getDate()+" "+commandes.elementAt(i).getTime());
                        base.ajouterBilan(commandes.elementAt(i).getNom(),commandes.elementAt(i).getQuantite());

                    }


                    //Supprimer les produits de la commande dans la base de donnès
                    commandes.clear();
                    JOptionPane.showMessageDialog(null, "Paiement accepté", "Paiement accepté", JOptionPane.WARNING_MESSAGE);

                    dispose();
                }

            }
        });
        JButton btnAnnuler = new JButton("ANNULER");
        btnAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Close the JFrame
                dispose();
            }
        });
        final JLabel lblARendre = new JLabel("A RENDRE :");

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
        textField.setBounds(350, 199, 200, 50);
        contentPane.add(textField);
        textField.setColumns(10);
        textField.setText(Float.toString(total_prix));
        textField.setEditable(false);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
        textField_1.setColumns(10);
        textField_1.setBounds(350, 312, 200, 50);
        contentPane.add(textField_1);

        textField_1.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {

                //System.out.println(((JTextField)e.getSource()).getText());
                float value=0;
                String text = textField_1.getText();
                try {
                    value =  Float.parseFloat(text);
                }
                catch (NumberFormatException er) {
                    System.err.println("Number Format Exception");
                    er.printStackTrace();
                }
                //System.out.printf("num is %.2f\n", value );
                int cifras=(int) Math.pow(10,2);
                double total= Math.rint((value-total_prix)*cifras)/cifras;
                textField_2.setText(Double.toString(total));
                btnValider.setEnabled(true);
                Font f = lblARendre.getFont();
                lblARendre.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
            }
            public void focusGained(FocusEvent arg0) {

            }
        });



        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
        textField_2.setColumns(10);
        textField_2.setBounds(350, 531, 200, 50);
        textField_2.setEditable(false);

        contentPane.add(textField_2);

        JLabel lblPrix = new JLabel("PRIX :");
        lblPrix.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblPrix.setBounds(184, 193, 109, 50);
        contentPane.add(lblPrix);

        JLabel lblReu = new JLabel("RE\u00C7U :");
        lblReu.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblReu.setBounds(184, 312, 109, 50);
        contentPane.add(lblReu);


        lblARendre.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblARendre.setBounds(150, 528, 209, 50);
        contentPane.add(lblARendre);


        btnValider.setFont(new Font("Tahoma", Font.PLAIN, 34));
        btnValider.setBackground(Color.GREEN);
        btnValider.setForeground(Color.BLACK);
        btnValider.setBounds(772, 373, 250, 122);
        btnValider.setEnabled(false);
        contentPane.add(btnValider);


        btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 34));
        btnAnnuler.setBackground(new Color(255, 0, 0));
        btnAnnuler.setBounds(772, 528, 250, 122);
        contentPane.add(btnAnnuler);

        this.setExtendedState(MAXIMIZED_BOTH);
    }
}
