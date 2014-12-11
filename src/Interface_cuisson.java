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




    public Interface_cuisson() {

        contentPane = new JPanel();
        setContentPane(contentPane);

        btn_Deconnecter = new JButton("Déconnecter");
        btn_Deconnecter.setBounds(809, 11, 109, 23);
        contentPane.add(btn_Deconnecter);


        lblServiceCuisson = new JLabel("SERVICE CUISSON");
        lblServiceCuisson.setHorizontalAlignment(SwingConstants.CENTER);
        lblServiceCuisson.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblServiceCuisson.setBounds(115, 15, 579, 78);
        contentPane.add(lblServiceCuisson);


        pan_1 = new JPanel();                 //  premier panel
        pan_1.setBounds(50, 149, 821, 66);
        contentPane.add(pan_1);
        pan_1.setLayout(new FlowLayout());

        btn_debut1 = new JButton("Début");
        btn_debut1.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_debut1.setBorderPainted(false);
        btn_debut1.setBackground(new Color(0, 204, 0));
        btn_debut1.setBounds(674, 11, 121, 43);                      // rajouter cette ligne pour tous les boutons (il leur faut leur taille)
        pan_1.add(btn_debut1);

        btn_fini1 = new JButton("Fini");
        btn_fini1.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_fini1.setBorderPainted(false);
        btn_fini1.setBackground(SystemColor.activeCaptionBorder);
        btn_fini1.setBounds(654, 11, 111, 43);
        pan_1.add(btn_fini1);

        pan_2 = new JPanel();             // deuxième panel
        pan_2.setBounds(50, 226, 821, 66);
        contentPane.add(pan_2);
        pan_2.setLayout(new FlowLayout());

        btn_debut2 = new JButton("Début");
        btn_debut2.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_debut2.setBorderPainted(false);
        btn_debut2.setBackground(new Color(0, 204, 0));
        btn_debut2.setBounds(574, 11, 121, 43);
        pan_2.add(btn_debut2);

        btn_fini2 = new JButton("Fini");
        btn_fini2.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_fini2.setBorderPainted(false);
        btn_fini2.setBackground(SystemColor.activeCaptionBorder);
        btn_fini2.setBounds(674, 11, 121, 43);
        pan_2.add(btn_fini2);

        pan_3 = new JPanel();                 //troisième panel
        pan_3.setBounds(50, 303, 821, 66);
        contentPane.add(pan_3);
        pan_3.setLayout(new FlowLayout());

        btn_debut3 = new JButton("Début");
        btn_debut3.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_debut3.setBorderPainted(false);
        btn_debut3.setBackground(new Color(0, 204, 0));
        btn_debut3.setBounds(374, 11, 121, 43);
        pan_3.add(btn_debut3);

        btn_fini3 = new JButton("Fini");
        btn_fini3.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_fini3.setBorderPainted(false);
        btn_fini3.setBackground(SystemColor.activeCaptionBorder);
        btn_fini3.setBounds(674, 11, 121, 43);
        pan_3.add(btn_fini3);

        pan_4 = new JPanel();                 //quatrième panel
        pan_4.setBounds(50, 380, 821, 66);
        contentPane.add(pan_4);
        pan_4.setLayout(new FlowLayout());

        btn_debut4 = new JButton("Début");
        btn_debut4.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_debut4.setBorderPainted(false);
        btn_debut4.setBackground(new Color(0, 204, 0));
        btn_debut4.setBounds(274, 11, 121, 43);
        pan_4.add(btn_debut4);

        btn_fini4 = new JButton("Fini");
        btn_fini4.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_fini4.setBorderPainted(false);
        btn_fini4.setBackground(SystemColor.activeCaptionBorder);
        btn_fini4.setBounds(674, 11, 121, 43);
        pan_4.add(btn_fini4);

        pan_5 = new JPanel();         // cinquième panel
        pan_5.setBounds(50, 457, 821, 66);
        contentPane.add(pan_5);
        pan_5.setLayout(new FlowLayout());

        btn_debut5 = new JButton("Début");
        btn_debut5.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_debut5.setBorderPainted(false);
        btn_debut5.setBackground(new Color(0, 204, 0));
        btn_debut1.setBounds(74, 11, 121, 43);
        pan_5.add(btn_debut5);

        btn_fini5 = new JButton("Fini");
        btn_fini5.setFont(new Font("Tahoma", Font.BOLD, 13));
        btn_fini5.setBorderPainted(false);
        btn_fini5.setBackground(SystemColor.activeCaptionBorder);
        btn_fini5.setBounds(674, 11, 121, 43);
        pan_5.add(btn_fini5);

    }
}