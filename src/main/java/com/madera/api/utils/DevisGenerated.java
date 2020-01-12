package com.madera.api.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.madera.api.models.Client;
import com.madera.api.models.DevisEtat;
import com.madera.api.models.Projet;
import com.madera.api.models.Utilisateur;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

public class DevisGenerated {

    private Font fontTitle;
    private Font fontBold;
    private Font fontBody;
    private Font fontHeader;

    private void loadFonts() {
        BaseFont fontCalibriBoldFont = null;
        BaseFont fontCalibriFont = null;
        try {
            fontCalibriBoldFont = BaseFont.createFont("src/main/resources/fonts/OpenSans-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
            fontCalibriFont = BaseFont.createFont("src/main/resources/fonts/OpenSans-Regular.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
            this.fontTitle = new Font(fontCalibriBoldFont);
            this.fontTitle.setColor(106, 168, 79);
            this.fontTitle.setSize(26);

            this.fontHeader = new Font(fontCalibriBoldFont);
            this.fontHeader.setColor(BaseColor.WHITE);
            this.fontHeader.setSize(11);

            this.fontBody = new Font(fontCalibriFont);
            this.fontBody.setSize(11);

            this.fontBold = new Font(fontCalibriBoldFont);
            this.fontBold.setSize(16);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generate(Projet projet, DevisEtat devisEtat, Utilisateur utilisateur, Client client) {
        loadFonts();
        try {
            //Création du document
            Document document = new Document();
            //Défini que document est un pdf
            PdfWriter.getInstance(document, new FileOutputStream("devis.pdf"));
            document.open();
            document.add(buildHeader(projet.nomProjet));
            document.add(buildTableCoordinates(projet.dateProjet, projet.refProjet, client.nom));
            Chunk etatDevis = new Chunk(String.format("Etat du devis : %s", devisEtat.devisEtatLibelle), this.fontBold);
            document.add(etatDevis);
            document.add(buildTableDestAndSend(utilisateur, client));
            document.add(buildTableOfProducts());
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Paragraph buildHeader(String nomProjet) {
        Image logoMadera = null;
        Paragraph header = new Paragraph();
        try {
            //Add and resize image
            logoMadera = Image.getInstance("src/main/resources/img/logo-madera.png");
            logoMadera.scalePercent(20);
            Chunk chunk = new Chunk(String.format("DEVIS %s", nomProjet), this.fontTitle);
            Chunk glue = new Chunk(new VerticalPositionMark());
            //Construct header
            header.setAlignment(Element.ALIGN_TOP);
            header.add(chunk);
            header.add(logoMadera);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return header;
    }

    //TODO param + compléter les infos !
    private PdfPTable buildTableCoordinates(Date date, String refProjet, String refClient) {
        //Construct table of coordinates
        PdfPTable tableCoordinates = new PdfPTable(2);
        tableCoordinates.setWidthPercentage(100);
        tableCoordinates.addCell(buildCell("Madera", true, false, false));
        tableCoordinates.addCell(buildCell(String.format("Date : %s", date), false, false, false));
        tableCoordinates.addCell(buildCell("85000, Rue Enzo Ferrari, La Roche sur sur Yon", true, false, false));
        tableCoordinates.addCell(buildCell(String.format("Ref. projet : %s", refProjet), false, false, false));
        //TODO remplacer nom du contact ?
        tableCoordinates.addCell(buildCell("02 51 98 18 32", true, false, false));
        tableCoordinates.addCell(buildCell(String.format("Ref. client : %s", refClient), false, false, false));
        return tableCoordinates;
    }
    //TODO passer les params
    private PdfPTable buildTableDestAndSend(Utilisateur utilisateur, Client client) {
        PdfPTable tableDestAndSend = new PdfPTable(2);
        tableDestAndSend.setWidthPercentage(100);
        tableDestAndSend.addCell(buildCell("Destinataire", true, true,false ));
        tableDestAndSend.addCell(buildCell("Envoyer à", false, true, false));
        tableDestAndSend.addCell(buildCell(utilisateur.getNom() + " " + utilisateur.getPrenom(), true, false, false));
        tableDestAndSend.addCell(buildCell(client.nom, false, false, false));
        tableDestAndSend.addCell(buildCell("Madera", true, false, false));
        tableDestAndSend.addCell(buildCell(client.prenom, false, false, false));
        tableDestAndSend.addCell(buildCell("85000, Rue Enzo Ferrari, La Roche sur sur Yon", true, false, false));
        tableDestAndSend.addCell(buildCell("[AdresseClientFacturation]", false, false, false));
        tableDestAndSend.addCell(buildCell(utilisateur.getTel(), true, false, false));
        tableDestAndSend.addCell(buildCell(client.getNumTel(), false, false, false));
        tableDestAndSend.addCell(buildCell(utilisateur.getMail(), true, false, false));
        tableDestAndSend.addCell(buildCell(client.getMail(), false, false, false));
        return tableDestAndSend;
    }

    private PdfPCell buildCell(String content, boolean alignLeftOrRight, boolean isSubTitle, boolean hasBorder) {
        PdfPCell cell = new PdfPCell(new Phrase(new Chunk(content, isSubTitle ? this.fontBold : this.fontBody)));
        if(!hasBorder) {
            cell.setBorder(Rectangle.NO_BORDER);
        }
        cell.setPadding(8f);
        if(alignLeftOrRight) {
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        } else {
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        }
        return cell;
    }
    //TODO produitWithModule
    private PdfPTable buildTableOfProducts() {
        PdfPTable tableOfProducts = new PdfPTable(4);
        //Construct header of table
        tableOfProducts.addCell(buildCellHeader("Module"));
        tableOfProducts.addCell(buildCellHeader("Quantité"));
        tableOfProducts.addCell(buildCellHeader("Prix unitaire"));
        tableOfProducts.addCell(buildCellHeader("Total"));
        //End construct header
        //Construct body
        //End contruct body
        return tableOfProducts;
    }
    
    private PdfPCell buildCellHeader(String content) {
        PdfPCell cellHeader = new PdfPCell(new Phrase(new Chunk(content, this.fontHeader)));
        cellHeader.setPadding(8f);
        cellHeader.setBackgroundColor(new BaseColor(106, 168, 79));
        cellHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cellHeader;
    }
}
