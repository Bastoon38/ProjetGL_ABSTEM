/**
 * Created by Bastien on 02/01/2015.
 */

import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.TabSettings;


import javax.swing.*;
import javax.swing.text.*;
import java.awt.font.TextAttribute;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.AttributedString;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;


public class GenerationDocument {

    public GenerationDocument(LinkedList listCmd, int taille){
        Document document = new Document(PageSize.A4);

        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("DD-MM-YYYY");
        DateTimeFormatter formatterRef = DateTimeFormatter.ofPattern("DDMMYYYYHHmm");
        LocalDateTime today = LocalDateTime.now();

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("C:/"));
        chooser.setDialogTitle("Selection dossier enregistrement");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
        } else {
            System.out.println("No Selection ");
            JOptionPane.showMessageDialog(null, "Création du PDF annulée", "Création PDF",  JOptionPane.ERROR_MESSAGE);
        }

        try {
            PdfWriter.getInstance(document,new FileOutputStream(chooser.getSelectedFile() + "\\BonDeCommande_" + formatterRef.format(today) +".pdf"));
            document.open();

            if (document.isOpen())
            {
                ClassLoader classLoader = getClass().getClassLoader();
                URL file = classLoader.getResource("images/logoLBM.png");
                Image img_lbm = Image.getInstance(file);
                img_lbm.setAlignment(Element.ALIGN_CENTER);

                Paragraph par_titre = new Paragraph("***** BON DE COMMANDE *****");
                Paragraph par_retourLigne = new Paragraph("\n");

                Paragraph par_date = new Paragraph("date: " + formatterDate.format(today));
                //par_date.setAlignment(Element.ALIGN_LEFT);

                Paragraph par_ref = new Paragraph("ref: " + formatterRef.format(today));

                Paragraph par_adressePerso = new Paragraph("Adresse de livraison:\nCPE LYON\n43 Boulevard du 11 Novembre 1918\n69616 Villeurbanne");
                par_titre.setAlignment(Element.ALIGN_CENTER);
                par_adressePerso.setAlignment(Element.ALIGN_LEFT);

                PdfPTable table = new PdfPTable(2);
                table.addCell("PRODUIT");
                table.addCell("QUANTITE COMMANDEE");

                for(int i=0;i<taille ; i++){
                    table.addCell(listCmd.get(i).toString());
                }

                Paragraph par_signature = new Paragraph("Signature:");

                document.add(img_lbm);
                document.add(par_titre);
                document.add(par_retourLigne);
                document.add(par_date);
                document.add(par_ref);
                document.add(par_retourLigne);
                document.add(par_adressePerso);
                document.add(par_retourLigne);
                document.add(par_retourLigne);
                document.add(table);
                document.add(par_retourLigne);
                document.add(par_signature);
                document.close();
                JOptionPane.showMessageDialog(null, "Création du PDF réussie", "Création PDF",  JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "Création du PDF échouée", "Création PDF",  JOptionPane.ERROR_MESSAGE);

        } catch (DocumentException de) {

            de.printStackTrace();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

