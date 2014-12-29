import javax.swing.*;
import java.awt.*;

public class Manager {

    public String password;

    private Interface_manager im;


    public Manager(Interface_manager b) {
        this.im = b;
        this.password="manager";



        JPanel panel = new JPanel();
        JLabel label = new JLabel("Ecrivez votre Password:");
        JPasswordField pass = new JPasswordField(10);

        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"Cancel", "OK"};
        int option = JOptionPane.showOptionDialog(null, panel, "Mot de Passe",JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
        if(option == 1) // pressing OK button
        {
            char[] password_char = pass.getPassword();
            String str = String.valueOf(password_char);
            //System.out.println(str+" "+password);
            if(str.equals(password))
            {
                im.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Mot de passe incorrect", "Mot de passe", JOptionPane.WARNING_MESSAGE);
                Connexion frame1 = new Connexion();
                frame1.setVisible(true);
            }
        }
        else
        {
            Connexion frame1 = new Connexion();
            frame1.setVisible(true);
        }



    }
}