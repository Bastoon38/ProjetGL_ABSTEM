



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Interface_cuisson extends JFrame {

    private JPanel contentPane;
    private JTable jtab_cuisson;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Interface_cuisson frame = new Interface_cuisson();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Interface_cuisson() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        setBounds(100, 100, 946, 864);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        GestionBDD base=new GestionBDD();
        Vector<Produit> cuisson= new Vector<Produit>();

        JButton btn_debut = new JButton("DEBUT");
        btn_debut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {



                final int rowSelected = jtab_cuisson.getSelectedRow();

                DefaultTableModel model_1 = new DefaultTableModel();
                model_1 = (DefaultTableModel) jtab_cuisson.getModel();

                String nom=jtab_cuisson.getModel().getValueAt(rowSelected, 0).toString();
                String quant=jtab_cuisson.getModel().getValueAt(rowSelected, 2).toString();
               final int int_quan=Integer.parseInt(quant);

                TimerTask timerTask = new TimerTask() {
                    int sec=(60*int_quan);
                    public void run() {

                        if(sec!=-1)
                        {
                            jtab_cuisson.setValueAt(sec, rowSelected, 2);
                            sec--;
                        }
                        else
                        {
                            cancel();
                        }

                    }
                };

                // creation du timer
                Timer timer = new Timer();

                //timer avec la fonction à executer, le retard et l'interval de repetition
                timer.scheduleAtFixedRate(timerTask, 0, 1000);



            }
        });

        btn_debut.setForeground(Color.BLACK);
        btn_debut.setFont(new Font("Tahoma", Font.PLAIN, 34));
        btn_debut.setBackground(Color.GREEN);
        btn_debut.setBounds(1486, 269, 250, 122);



        contentPane.add(btn_debut);

        JButton btn_fini = new JButton("FINI");
        btn_fini.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {



                int rowSelected = jtab_cuisson.getSelectedRow();

                DefaultTableModel model_1 = new DefaultTableModel();
                model_1 = (DefaultTableModel) jtab_cuisson.getModel();

                String nom=jtab_cuisson.getModel().getValueAt(rowSelected, 0).toString();
                String quant=jtab_cuisson.getModel().getValueAt(rowSelected, 2).toString();
                int int_quan=Integer.parseInt(quant);

                //Si le temps de cuisson est fini
                if(int_quan==0)
                {
                    //TODO
                    //AJouter le produit dans vitrine
                    model_1.removeRow(jtab_cuisson.getSelectedRow());
                    jtab_cuisson.repaint();
                }


            }
        });
        btn_fini.setFont(new Font("Tahoma", Font.PLAIN, 34));
        btn_fini.setBackground(Color.RED);
        btn_fini.setBounds(1486, 515, 250, 122);
        contentPane.add(btn_fini);

        jtab_cuisson = new JTable(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Nom","Quantite","Time"}
        ) {
            Class[] columnTypes = new Class[] {
                    Object.class, Integer.class,Integer.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        jtab_cuisson.setFont(new java.awt.Font("Arial", 0, 20));
        jtab_cuisson.setRowHeight(80);
        jtab_cuisson.setBounds(187, 161, 1146, 682);
        contentPane.add(jtab_cuisson);

        cuisson=base.getFour(cuisson);

        DefaultTableModel model = (DefaultTableModel) jtab_cuisson.getModel();
        for (int i=0; i<cuisson.size();i++)
        {

            model.addRow(new Object[]{cuisson.elementAt(i).getNom(),cuisson.elementAt(i).getQuantite(),base.getTime_cuisson(cuisson.elementAt(i).getNom())});
        }

    }
}
