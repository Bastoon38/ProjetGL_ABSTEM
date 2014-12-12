import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JList;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * Created by Thomas on 09/12/2014. permi */

public class Interface_cuisson extends JFrame {

    private JPanel contentPane;
    private JPanel pan_1;
    private JPanel pan_2;
    private JPanel pan_3;
    private JPanel pan_4;
    private JPanel pan_5;
    private JButton btn_Deconnecter;
    private JButton btn_debut1;
    private JButton btn_fini1;
    private JButton btn_debut2;
    private JButton btn_fini2;
    private JButton btn_debut3;
    private JButton btn_fini3;
    private JButton btn_debut4;
    private JButton btn_fini4;
    private JButton btn_debut5;
    private JButton btn_fini5;
    private JLabel lblServiceCuisson;
    private JLabel  commande1;
    private JLabel  commande2;
    private JLabel  commande3;
    private JLabel  commande4;
    private JLabel  commande5;




    public Interface_cuisson() {

        setExtendedState(JFrame.MAXIMIZED_BOTH);  // pour que la fenetre s'ouvre en grand direct

        contentPane = new JPanel();  // conteneur principal
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = gbc.gridy = 0;  // première case
        gbc.weightx = 0.9;
        gbc.weighty = 1;

        lblServiceCuisson = new JLabel("SERVICE CUISSON");
        lblServiceCuisson.setHorizontalAlignment(SwingConstants.CENTER);
        lblServiceCuisson.setFont(new Font("Tahoma", Font.PLAIN, 30));
        contentPane.add(lblServiceCuisson , gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.insets = new Insets(10, 0, 0, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER; //dernier de la ligne

        btn_Deconnecter = new JButton("Déconnecter");
        contentPane.add(btn_Deconnecter,  gbc);

        ////////Premier panel de commande ////////////////

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        pan_1 = new JPanel();
        pan_1.setBackground(Color.LIGHT_GRAY);
        contentPane.add(pan_1, gbc);
        pan_1.setLayout(new GridBagLayout());

        GridBagConstraints gbc1 = new GridBagConstraints();

        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.weightx = 0.7;
        gbc1.weighty = 1;
        gbc1.anchor = GridBagConstraints.CENTER;

        commande1 = new JLabel("Zone texte commande 1");
        pan_1.add(commande1, gbc1);

        gbc1.gridx = 1;
        gbc1.gridy = 0;
        gbc1.weightx = 0.1;
        gbc1.weighty = 1;
        JLabel timer1 = new JLabel("20:00"); // à remplacer par le timer
        pan_1.add(timer1, gbc1);

        gbc1.gridx = 2;
        gbc1.gridy = 0;
        gbc1.weightx = 0.1;
        gbc1.weighty = 1;
        btn_debut1 = new JButton("Début");
        btn_debut1.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_debut1.setBorderPainted(false);
        btn_debut1.setBackground(new Color(0, 204, 0));
        pan_1.add(btn_debut1, gbc1);

        gbc1.gridx = 3;
        gbc1.gridy = 0;
        gbc1.weightx = 0.1;
        gbc1.weighty = 1;
        gbc1.gridwidth = GridBagConstraints.REMAINDER;

        btn_fini1 = new JButton("Fini");
        btn_fini1.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_fini1.setBorderPainted(false);
        btn_fini1.setBackground(SystemColor.activeCaptionBorder);
        pan_1.add(btn_fini1, gbc1);

        ////////Deuxième panel de commande ////////////////

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        pan_2 = new JPanel();
        pan_2.setBackground(Color.LIGHT_GRAY);
        contentPane.add(pan_2, gbc);
        pan_2.setLayout(new GridBagLayout());

        GridBagConstraints gbc2 = new GridBagConstraints();

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.weightx = 0.7;
        gbc2.weighty = 1;
        gbc2.anchor = GridBagConstraints.CENTER;

        commande2 = new JLabel("Zone texte commande 2");
        pan_2.add(commande2, gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.weightx = 0.1;
        gbc2.weighty = 1;
        JLabel timer2 = new JLabel("20:00"); // à remplacer par le timer
        pan_2.add(timer2, gbc2);

        gbc2.gridx = 2;
        gbc2.gridy = 0;
        gbc2.weightx = 0.1;
        gbc2.weighty = 1;
        btn_debut2 = new JButton("Début");
        btn_debut2.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_debut2.setBorderPainted(false);
        btn_debut2.setBackground(new Color(0, 204, 0));
        pan_2.add(btn_debut2, gbc2);

        gbc2.gridx = 3;
        gbc2.gridy = 0;
        gbc2.weightx = 0.1;
        gbc2.weighty = 1;
        gbc2.gridwidth = GridBagConstraints.REMAINDER;

        btn_fini2 = new JButton("Fini");
        btn_fini2.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_fini2.setBorderPainted(false);
        btn_fini2.setBackground(SystemColor.activeCaptionBorder);
        pan_2.add(btn_fini2, gbc2);

        ////////Troisième panel de commande ////////////////

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        pan_3 = new JPanel();
        pan_3.setBackground(Color.LIGHT_GRAY);
        contentPane.add(pan_3, gbc);
        pan_3.setLayout(new GridBagLayout());

        GridBagConstraints gbc3 = new GridBagConstraints();

        gbc3.gridx = 0;
        gbc3.gridy = 0;
        gbc3.weightx = 0.7;
        gbc3.weighty = 1;
        gbc3.anchor = GridBagConstraints.CENTER;

        commande3 = new JLabel("Zone texte commande 3");
        pan_3.add(commande3, gbc3);

        gbc3.gridx = 1;
        gbc3.gridy = 0;
        gbc3.weightx = 0.1;
        gbc3.weighty = 1;
        JLabel timer3 = new JLabel("20:00"); // à remplacer par le timer
        pan_3.add(timer3, gbc3);

        gbc3.gridx = 2;
        gbc3.gridy = 0;
        gbc3.weightx = 0.1;
        gbc3.weighty = 1;
        btn_debut3 = new JButton("Début");
        btn_debut3.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_debut3.setBorderPainted(false);
        btn_debut3.setBackground(new Color(0, 204, 0));
        pan_3.add(btn_debut3, gbc3);

        gbc3.gridx = 3;
        gbc3.gridy = 0;
        gbc3.weightx = 0.1;
        gbc3.weighty = 1;
        gbc3.gridwidth = GridBagConstraints.REMAINDER;

        btn_fini3 = new JButton("Fini");
        btn_fini3.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_fini3.setBorderPainted(false);
        btn_fini3.setBackground(SystemColor.activeCaptionBorder);
        pan_3.add(btn_fini3, gbc3);

        ////////Quatrième panel de commande ////////////////

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        pan_4 = new JPanel();
        pan_4.setBackground(Color.LIGHT_GRAY);
        contentPane.add(pan_4, gbc);
        pan_4.setLayout(new GridBagLayout());

        GridBagConstraints gbc4 = new GridBagConstraints();

        gbc4.gridx = 0;
        gbc4.gridy = 0;
        gbc4.weightx = 0.7;
        gbc4.weighty = 1;
        gbc4.anchor = GridBagConstraints.CENTER;

        commande4 = new JLabel("Zone texte commande 4");
        pan_4.add(commande4, gbc4);

        gbc4.gridx = 1;
        gbc4.gridy = 0;
        gbc4.weightx = 0.1;
        gbc4.weighty = 1;
        JLabel timer4 = new JLabel("20:00"); // à remplacer par le timer
        pan_4.add(timer4, gbc4);

        gbc4.gridx = 2;
        gbc4.gridy = 0;
        gbc4.weightx = 0.1;
        gbc4.weighty = 1;
        btn_debut4 = new JButton("Début");
        btn_debut4.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_debut4.setBorderPainted(false);
        btn_debut4.setBackground(new Color(0, 204, 0));
        pan_4.add(btn_debut4, gbc4);

        gbc4.gridx = 3;
        gbc4.gridy = 0;
        gbc4.weightx = 0.1;
        gbc4.weighty = 1;
        gbc4.gridwidth = GridBagConstraints.REMAINDER;

        btn_fini4 = new JButton("Fini");
        btn_fini4.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_fini4.setBorderPainted(false);
        btn_fini4.setBackground(SystemColor.activeCaptionBorder);
        pan_4.add(btn_fini4, gbc4);

        ////////Cinquième panel de commande ////////////////

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        pan_5 = new JPanel();
        pan_5.setBackground(Color.LIGHT_GRAY);
        contentPane.add(pan_5, gbc);
        pan_5.setLayout(new GridBagLayout());

        GridBagConstraints gbc5 = new GridBagConstraints();

        gbc5.gridx = 0;
        gbc5.gridy = 0;
        gbc5.weightx = 0.7;
        gbc5.weighty = 1;
        gbc5.anchor = GridBagConstraints.CENTER;

        commande5 = new JLabel("Zone texte commande 5");
        pan_5.add(commande5, gbc5);

        gbc5.gridx = 1;
        gbc5.gridy = 0;
        gbc5.weightx = 0.1;
        gbc5.weighty = 1;
        JLabel timer5 = new JLabel("20:00"); // à remplacer par le timer
        pan_5.add(timer5, gbc5);

        gbc5.gridx = 2;
        gbc5.gridy = 0;
        gbc5.weightx = 0.1;
        gbc5.weighty = 1;
        btn_debut5 = new JButton("Début");
        btn_debut5.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_debut5.setBorderPainted(false);
        btn_debut5.setBackground(new Color(0, 204, 0));
        pan_5.add(btn_debut5, gbc5);

        gbc5.gridx = 3;
        gbc5.gridy = 0;
        gbc5.weightx = 0.1;
        gbc5.weighty = 1;
        gbc5.gridwidth = GridBagConstraints.REMAINDER;

        btn_fini5 = new JButton("Fini");
        btn_fini5.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_fini5.setBorderPainted(false);
        btn_fini5.setBackground(SystemColor.activeCaptionBorder);
        pan_5.add(btn_fini5, gbc5);

    }
}