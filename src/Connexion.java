import javax.swing.*;

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
    }
}