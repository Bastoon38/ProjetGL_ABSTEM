



import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Timer;


public class Interface_cuisson extends JFrame {

    private JPanel contentPane;
    private JTable jtab_cuisson;
    private JTable tab_jeter_1;
    private JButton btn_fini;
    private ClassLoader classLoader;

    Vector<Produit> stock = new Vector<Produit>();
    Vector<Produit> jeter= new Vector<Produit>();

    void timer_refresh_produits_jeter()
    {
        for(int i=0 ; i<stock.size(); i++)
        {
            java.util.Date fechaActual = new java.util.Date();

            if(stock.elementAt(i).getDate().after(fechaActual))
            {
                //System.out.println ("PAS PERIME "+vitrine.elementAt(i).getNom()+" "+vitrine.elementAt(i).getQuantite()+" "+vitrine.elementAt(i).getPrix()+" "+vitrine.elementAt(i).getPerime()+ " "+vitrine.elementAt(i).getDate()+" "+vitrine.elementAt(i).getTime());
            }

            else
            {
                String date_now =new SimpleDateFormat("yyyy-MM-dd").format(fechaActual);
                String date_produit=stock.elementAt(i).getDate().toString();

                // QUAND LES DATES SONT EGALES == MEME JOUR
                if(date_now.equals(date_produit))
                {
                    //time now
                    int hours_now=fechaActual.getHours();
                    int minutes_now=fechaActual.getMinutes();
                    int seconds_now=fechaActual.getSeconds();

                    Time now= new Time(hours_now,minutes_now,seconds_now);

                    //time produit
                    int hours=stock.elementAt(i).getTime().getHours();
                    int minutes=stock.elementAt(i).getTime().getMinutes();
                    int seconds=stock.elementAt(i).getTime().getSeconds();
                    Time produit=new Time(hours,minutes,seconds);
                    //si le produit est perimé
                    if(now.after(produit))
                    {
                        //GestionBDD gestion = new GestionBDD();
                        //gestion.supprimerStock(stock.elementAt(i).getNom(), 1);
                        JOptionPane.showMessageDialog(null, stock.elementAt(i).getNom()+" du Stock périmé(e)", "Périmé "+ stock.elementAt(i).getNom(), JOptionPane.WARNING_MESSAGE);
                        Produit aux = new Produit(stock.elementAt(i).getNom(),stock.elementAt(i).getPrix(),stock.elementAt(i).getQuantite(),stock.elementAt(i).getDate(),stock.elementAt(i).getTime(),stock.elementAt(i).getPerime());
                        stock.remove(i);
                        jeter.add(aux);
                        i--;

                        DefaultTableModel model = (DefaultTableModel) tab_jeter_1.getModel();
                        model.addRow(new Object[]{aux.getNom(),Integer.toString(aux.getQuantite())});
                    }
                }
                else
                {
                    //GestionBDD gestion = new GestionBDD();
                    //gestion.supprimerStock(stock.elementAt(i).getNom(), 1);
                    JOptionPane.showMessageDialog(null, stock.elementAt(i).getNom()+" périmé", "Périmé "+ stock.elementAt(i).getNom(), JOptionPane.WARNING_MESSAGE);
                    Produit aux = new Produit(stock.elementAt(i).getNom(),stock.elementAt(i).getPrix(),stock.elementAt(i).getQuantite(),stock.elementAt(i).getDate(),stock.elementAt(i).getTime(),stock.elementAt(i).getPerime());
                    stock.remove(i);
                    jeter.add(aux);
                    i--;

                    DefaultTableModel model = (DefaultTableModel) tab_jeter_1.getModel();
                    model.addRow(new Object[]{aux.getNom(),Integer.toString(aux.getQuantite())});
                }
            }
        }
    }

    public Interface_cuisson() {
        this.setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1200, 900));
        classLoader = getClass().getClassLoader();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 946, 864);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final GestionBDD base=new GestionBDD();
         Vector<Produit> cuisson= new Vector<Produit>();

        JButton btn_debut = new JButton("DEBUT");
        btn_debut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                final int rowSelected = jtab_cuisson.getSelectedRow();

                DefaultTableModel model_1 = new DefaultTableModel();
                model_1 = (DefaultTableModel) jtab_cuisson.getModel();

                String nom=jtab_cuisson.getModel().getValueAt(rowSelected, 0).toString();
                String quan=jtab_cuisson.getModel().getValueAt(rowSelected, 1).toString();
                String time=jtab_cuisson.getModel().getValueAt(rowSelected, 2).toString();
                String ids=jtab_cuisson.getModel().getValueAt(rowSelected, 3).toString();
                final int int_temps=Integer.parseInt(time);
                final int id3=Integer.parseInt(ids);

                GestionBDD baseDonnee = new GestionBDD();
                baseDonnee.majCuisson(nom, quan,id3);


                TimerTask timerTask = new TimerTask() {
                    int sec=(60*int_temps);
                    public void run() {
                        if(sec>=0)
                        {
                            int hor=sec/3600;
                            int min=(sec-(3600*hor))/60;
                            int seg=sec-((hor*3600)+(min*60));

                            jtab_cuisson.setValueAt(min + "m " + seg + "s", rowSelected, 2);
                            sec--;
                        }
                        else
                        {
                            //playSound();
                            jtab_cuisson.setValueAt("FIN", rowSelected, 2);
                            cancel();
                        }
                    }
                };

                // creation du timer
                Timer timer = new Timer();
                //timer avec la fonction à executer, le retard et l'interval de repetition
                timer.schedule(timerTask, 0, 1000);
            }
        });

        btn_debut.setForeground(Color.BLACK);
        btn_debut.setFont(new Font("Tahoma", Font.PLAIN, 34));
        btn_debut.setBackground(Color.GREEN);
        btn_debut.setBounds(1486, 269, 250, 122);



        contentPane.add(btn_debut);

        btn_fini = new JButton("FINI");
        btn_fini.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                int rowSelected = jtab_cuisson.getSelectedRow();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
                LocalDateTime today = LocalDateTime.now();

                DefaultTableModel model_1 = new DefaultTableModel();
                model_1 = (DefaultTableModel) jtab_cuisson.getModel();


                String nom=jtab_cuisson.getModel().getValueAt(rowSelected, 0).toString();
                String quant=jtab_cuisson.getModel().getValueAt(rowSelected, 1).toString();
                int int_quan=Integer.parseInt(quant);
                String time=jtab_cuisson.getModel().getValueAt(rowSelected, 2).toString();
                String ids=jtab_cuisson.getModel().getValueAt(rowSelected, 3).toString();
                final int id4=Integer.parseInt(ids);

                //Si le temps de cuisson est fini
                if(time.equals("FIN"))
                {
                    //TODO
                    //AJouter le produit dans vitrine et supprimer le produit dans four
                    base.supprimerCuisson(id4);

                    if ( nom.toUpperCase().equals("BAGUETTE"))
                    {
                        LocalDateTime datePerime = today.plusHours(12);
                        String date = formatter.format(datePerime);
                        base.ajouterVitrine(nom,int_quan,date);
                    }
                    else if ( nom.toUpperCase().equals("FLUTE") || nom.toUpperCase().equals("TARTE CITRON") || nom.toUpperCase().equals("TARTE PRALINE") )
                    {
                        LocalDateTime datePerime = today.plusHours(24);
                        String date = formatter.format(datePerime);
                        base.ajouterVitrine(nom,int_quan,date);
                    }
                    else if (nom.toUpperCase().equals("CROISSANT") ||nom.toUpperCase().equals("PAIN AU CHOCOLAT") ||nom.toUpperCase().equals("BRIOCHE SUCRE") ||nom.toUpperCase().equals("PAIN AU LAIT"))
                    {
                        LocalDateTime datePerime = today.plusHours(6);
                        String date = formatter.format(datePerime);
                        base.ajouterVitrine(nom,int_quan,date);
                    }
                    else if (nom.toUpperCase().equals("COCA COLA") ||nom.toUpperCase().equals("SPRITE") ||nom.toUpperCase().equals("FANTA") ||nom.toUpperCase().equals("OASIS") ||nom.toUpperCase().equals("ORANGE") ||nom.toUpperCase().equals("RAISIN") || nom.toUpperCase().equals("POMME"))
                    {
                        String dateBoisson = base.getDatePerimeBoisson(nom.toUpperCase());
                        System.out.println(nom);
                        System.out.println(int_quan);
                        System.out.println(dateBoisson);
                        base.ajouterVitrine(nom,int_quan,dateBoisson);
                    }
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
                new String[] {"Nom","Quantite","Time","ID"}
        ) {
            Class[] columnTypes = new Class[] {
                    Object.class, Integer.class,Integer.class,Integer.class
            };
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        jtab_cuisson.setFont(new java.awt.Font("Arial", 0, 20));
        jtab_cuisson.setRowHeight(100);
        jtab_cuisson.setBounds(187, 161, 1020, 682);
        //JScrollPane tbc_cuisson = new JScrollPane(jtab_cuisson);
        //contentPane.removeAll();

        //contentPane.add(tbc_cuisson, BorderLayout.CENTER).
        contentPane.add(jtab_cuisson);

        JLabel lblNom = new JLabel("Nom");
        lblNom.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNom.setBounds(197, 94, 200, 50);
        contentPane.add(lblNom);

        JLabel labQuantite = new JLabel("Quantité");
        labQuantite.setFont(new Font("Tahoma", Font.PLAIN, 24));
        labQuantite.setBounds(654, 94, 200, 50);
        contentPane.add(labQuantite);

        JLabel lab_time = new JLabel("Timer");
        lab_time.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lab_time.setBounds(1006, 94, 200, 50);
        contentPane.add(lab_time);

        final TimerTask timerTask2 = new TimerTask() {
            public void run() {
                timer_refresh_produits_jeter();
                java.util.Date maDate = new java.util.Date();
                System.out.println("Tâche vérification péremption stock lancée le " + maDate.toString());
            }
        };

        JButton button = new JButton("Déconnexion");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connexion connexion = new Connexion();
                connexion.setVisible(true);
                setVisible(false);
                timerTask2.cancel();
            }
        });
        button.setBounds(1699, 16, 127, 29);
        contentPane.add(button);

        JButton btnRefresh = new JButton("REFRESH");
        btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 34));
        btnRefresh.setBackground(Color.GRAY);

        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)jtab_cuisson.getModel();
                int rows = model.getRowCount();
                for(int i = rows - 1; i >=0; i--)
                {
                    model.removeRow(i);
                }
                Vector<Produit> cuisson= new Vector<Produit>();
                cuisson= base.getFour(cuisson);

                for (int i=0; i<cuisson.size();i++)
                {
                   // model.addRow(new Object[]{cuisson.elementAt(i).getNom(),cuisson.elementAt(i).getQuantite(),1,cuisson.elementAt(i).getid()});

                    model.addRow(new Object[]{cuisson.elementAt(i).getNom(),cuisson.elementAt(i).getQuantite(),base.getTime_cuisson(cuisson.elementAt(i).getNom(),cuisson.elementAt(i).getid()),cuisson.elementAt(i).getid()});
                }
                for (int i=0; i<cuisson.size();i++)
                {
                    if(cuisson.elementAt(i).getcuisson()==1)
                    {
                        int j=0;
                        while(cuisson.elementAt(i).getid()!=Integer.parseInt(jtab_cuisson.getModel().getValueAt(j, 3).toString()))
                        {
                            j++;
                        }
                        final int p=j;
                        TimerTask timerTask = new TimerTask() {
                            int sec=Integer.parseInt(jtab_cuisson.getModel().getValueAt(p, 2).toString());
                            public void run() {
                                if(sec>=0)
                                {
                                    int hor=sec/3600;
                                    int min=(sec-(3600*hor))/60;
                                    int seg=sec-((hor*3600)+(min*60));

                                    jtab_cuisson.setValueAt(min + "m " + seg + "s",p, 2);
                                    sec--;
                                }
                                else
                                {
                                    //playSound();
                                    System.out.println("finnnnnnn");
                                    jtab_cuisson.setValueAt("FIN", p, 2);
                                    cancel();
                                }
                            }
                        };

                        // creation du timer
                        Timer timer = new Timer();

                        //timer avec la fonction à executer, le retard et l'interval de repetition
                        timer.scheduleAtFixedRate(timerTask, 0, 1000);

                    }
                }
            }
        });
        btnRefresh.setBounds(1486, 700, 250, 122);
        contentPane.add(btnRefresh);

        cuisson=base.getFour(cuisson);

        DefaultTableModel model = (DefaultTableModel) jtab_cuisson.getModel();
        for (int i=0; i<cuisson.size();i++)
        {
            //model.addRow(new Object[]{cuisson.elementAt(i).getNom(),cuisson.elementAt(i).getQuantite(),1,cuisson.elementAt(i).getid()});

             model.addRow(new Object[]{cuisson.elementAt(i).getNom(),cuisson.elementAt(i).getQuantite(),base.getTime_cuisson(cuisson.elementAt(i).getNom(),cuisson.elementAt(i).getid()),cuisson.elementAt(i).getid()});
        }
        for (int i=0; i<cuisson.size();i++)
        {
            if(cuisson.elementAt(i).getcuisson()==1)
            {
                int j=0;
                while(cuisson.elementAt(i).getid()!=Integer.parseInt(jtab_cuisson.getModel().getValueAt(j, 3).toString()))
                {
                    j++;
                }
               final int p=j;
                TimerTask timerTask = new TimerTask() {
                    int sec=Integer.parseInt(jtab_cuisson.getModel().getValueAt(p, 2).toString());
                    public void run() {

                        if(sec>=0)
                        {
                            int hor=sec/3600;
                            int min=(sec-(3600*hor))/60;
                            int seg=sec-((hor*3600)+(min*60));

                            jtab_cuisson.setValueAt(min + "m " + seg + "s",p, 2);
                            sec--;
                        }
                        else
                        {
                            //playSound();
                            System.out.println("finnnnnnn");
                            jtab_cuisson.setValueAt("FIN", p, 2);
                            cancel();
                        }

                    }
                };

                // creation du timer
                Timer timer = new Timer();

                //timer avec la fonction à executer, le retard et l'interval de repetition
                timer.scheduleAtFixedRate(timerTask, 0, 1000);

            }
        }


        jtab_cuisson.removeColumn(jtab_cuisson.getColumnModel().getColumn(3));

        TimerTask timerTask = new TimerTask() {
            public void run() {
                timer_refresh_produits_jeter();
                java.util.Date maDate = new java.util.Date();
                System.out.println("Tâche vérification péremption stock lancée le " + maDate.toString());
            }
        };

        Timer timer2 = new Timer();   // creation du timer
        timer2.schedule(timerTask, 0, (60000 * 60)); //timer répétitive toutes les 1h
        System.out.println("Tâche vérification péremption stock lancée toutes les 1h");
    }

    public synchronized void playSound() {

        //** add this into your application code as appropriate
// Open an input stream  to the audio file.
        classLoader = getClass().getClassLoader();
        URL file = classLoader.getResource("son/newalert.wav");
        System.out.println(file);
        System.out.println(String.valueOf(file));
        String path = (String.valueOf(file).replace("file:", ""));
        System.out.println(path);
        InputStream in = null;
        try {
            in = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

// Create an AudioStream object from the input stream.
        AudioStream as = null;
        try {
            as = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

// Use the static class member "player" from class AudioPlayer to play
// clip.
        AudioPlayer.player.start(as);

// Similarly, to stop the audio.
        // AudioPlayer.player.stop(as);
    }
}
