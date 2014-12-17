import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Thomas on 09/12/2014.
 */
public class Connexion extends JFrame {

    private JButton btn_vendeur;
    private JButton btn_cuisinier;
    private JButton btn_manager;
    private JPanel pan_panel1;

    public  Connexion()
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(pan_panel1);

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
    }

    public void  affichePageVendeur(){
        // TODO: mettre les actions à effectuer quand on clique sur le bouton vendeur
        Interface_vendeur vendeur = new Interface_vendeur();
        vendeur.setVisible(true);
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
        Interface_manager manager = new Interface_manager();
        manager.setVisible(true);
        this.setVisible(false);
    }
}