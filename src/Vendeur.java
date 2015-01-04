/**
 * Created by Elio on 17/12/2014.
 */
import java.util.ArrayList;


public class Vendeur {

    private float total;

    private Interface_vendeur iv;


    public Vendeur (Interface_vendeur b) {
        this.iv = b;
        iv.setVisible(true);

    }




}