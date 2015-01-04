import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by Thomas on 09/12/2014.
 */
public class Connexion extends JFrame {

    private JButton btn_vendeur;
    private JButton btn_cuisinier;
    private JButton btn_manager;
    private JPanel pan_co;
    private JLabel lab_version;
    private JLabel lab_identification;
    private JLabel lab_boulangerie;
    private JLabel lab_photo1;
    private JLabel lab_photo2;

    public  Connexion()
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1200, 900));
        add(pan_co);

        btn_vendeur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                affichePageVendeur();
            }
        });

        btn_cuisinier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                affichePageCuisinier();
            }
        });

        btn_manager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                affichePageManager();
            }
        });

        lab_identification.setFont(new Font("Serif", Font.BOLD, 50));
        lab_boulangerie.setFont(new Font("Serif", Font.BOLD, 40));
        lab_version.setFont(new Font("Serif", Font.BOLD, 15));

        ClassLoader classLoader = getClass().getClassLoader();
        URL file = classLoader.getResource("images/logoABSTEM3.png");
        Icon icn_abstem = new ImageIcon(file);
        lab_photo1.setIcon(icn_abstem);

        file = classLoader.getResource("images/logoLBM4.png");
        Icon icn_lbm = new ImageIcon(file);
        lab_photo2.setIcon(icn_lbm);


    }

    public void  affichePageVendeur(){
        // TODO: mettre les actions à effectuer quand on clique sur le bouton vendeur
       Interface_vendeur vendeur = new Interface_vendeur();
        Vendeur v=new Vendeur(vendeur);
        this.setVisible(false);
    }

    public void  affichePageCuisinier(){
        // TODO: mettre les actions à effectuer quand on clique sur le bouton cuisinier
        Interface_cuisson cuisinier = new Interface_cuisson();
        cuisinier.setVisible(true);
        this.setVisible(false);
    }

    public void  affichePageManager(){
        // TODO: mettre les actions à effectuer quand on clique sur le bouton manager
    //    Interface_manager manager = new Interface_manager();
    //    manager.setVisible(true);
      //  Manager man=new Manager();
        Interface_manager manager = new Interface_manager();
        Manager man=new Manager(manager);
        this.setVisible(false);

    }
}