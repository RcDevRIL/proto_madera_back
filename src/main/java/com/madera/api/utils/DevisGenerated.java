package com.madera.api.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.madera.api.models.*;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class DevisGenerated {

    private Font fontTitle;
    private Font fontSmallBold;
    private Font fontBold;
    private Font fontBody;
    private Font fontHeader;
    private Font fontItalic;

    /**
     * Construct font
     */
    private void loadFonts() {
        BaseFont fontOpenSansBoldFont = null;
        BaseFont fontOpenSansFont = null;
        BaseFont fontOpenSansItalicFont = null;
        try {
            fontOpenSansBoldFont = BaseFont.createFont("src/main/resources/fonts/OpenSans-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
            fontOpenSansFont = BaseFont.createFont("src/main/resources/fonts/OpenSans-Regular.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
            fontOpenSansItalicFont = BaseFont.createFont("src/main/resources/fonts/OpenSans-Italic.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
            this.fontTitle = new Font(fontOpenSansBoldFont);
            this.fontTitle.setColor(106, 168, 79);
            this.fontTitle.setSize(23);

            this.fontHeader = new Font(fontOpenSansBoldFont);
            this.fontHeader.setColor(BaseColor.WHITE);
            this.fontHeader.setSize(11);

            this.fontBody = new Font(fontOpenSansFont);
            this.fontBody.setSize(11);

            this.fontBold = new Font(fontOpenSansBoldFont);
            this.fontSmallBold = new Font(fontOpenSansBoldFont);
            this.fontBold.setSize(16);
            this.fontSmallBold.setSize(11);

            this.fontItalic = new Font(fontOpenSansItalicFont);
            this.fontItalic.setSize(11);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param projet
     * @param devisEtat
     * @param utilisateur
     * @param client
     * @param adresseFacturation
     * @param listProduit
     * @param listProduitModule
     * @param listDevisEcheance
     * @param listModuleComposant
     * @param listComposant
     */
    public byte[] generate(
            Projet projet,
            DevisEtat devisEtat,
            Utilisateur utilisateur,
            Client client,
            Adresse adresseFacturation,
            List<Produit> listProduit,
            List<ProduitModule> listProduitModule,
            List<DevisEtat> listDevisEcheance,
            List<ModuleComposant> listModuleComposant,
            List<Composant> listComposant
    ) {
        loadFonts();
        try {
            //Création du document
            Document document = new Document();
            //Défini que document est un pdf
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //Instance de byteArray
            PdfWriter.getInstance(document, byteArrayOutputStream);
            //Instance d'un fichier pdf sur le serveur
             PdfWriter.getInstance(document, new FileOutputStream(String.format("devis_generated/devis_%s_%s.pdf", client.id, Date.valueOf(LocalDate.now()))));
            document.open();
            //Construct first page of devis
            document.add(buildHeader(projet.nomProjet, false));
            //Saute une ligne
            document.add(new Paragraph());
            document.add(buildTableCoordinates(projet.dateProjet, projet.refProjet, client.nom));
            Chunk etatDevis = new Chunk(String.format("Etat du devis : %s", devisEtat.devisEtatLibelle), this.fontBold);
            document.add(etatDevis);
            document.add(buildTableDestAndSend(utilisateur, client, adresseFacturation));

            //Construct a table of products and modules
            double totalProjet = 0.0;
            for(Produit produit : listProduit) {
                document.add(new Chunk(String.format("\n\nProduit : %s", produit.getProduitNom()), this.fontBold));
                document.add(new Paragraph("\n\n"));
                document.add(buildTableOfProducts(produit, listProduitModule));
                document.add(buldParagraphTT(produit.getPrixProduit(), 0));
                totalProjet += produit.getPrixProduit();
            }
            Chunk projetPrix = new Chunk(String.format("Total : %s €\n", Math.round(totalProjet)), this.fontBold);
            Anchor anchor = new Anchor("https://madera-construction.fr/cctp");
            anchor.setReference("https://madera-construction.fr/cctp");
            Paragraph paragrapheProjetPrix  = new Paragraph(projetPrix);
            Paragraph urlSiteCCTP = new Paragraph(new Chunk("Le cahier des clauses techniques particulières est disponible à l'adresse suivante : \n", this.fontBody));
            urlSiteCCTP.add(anchor);
            urlSiteCCTP.setAlignment(Element.ALIGN_LEFT);
            paragrapheProjetPrix.setAlignment(Element.ALIGN_RIGHT);
            document.add(paragrapheProjetPrix);
            document.add(urlSiteCCTP);
            //End construct first page of devis
            //Construct a second page
            document.newPage();
            document.add(buildHeader(projet.nomProjet, false));
            document.add(new Chunk("Echeance de paiement :", this.fontBold));
            document.add(buildTableEcheance(totalProjet, listDevisEcheance));
            //End of second page
            //Construct a dossier technique
            document.newPage();
            document.add(buildHeader(projet.nomProjet, true));
            document.add(new Chunk(String.format("Ce document à pour bout de faire un récapitulatif des différents produits, des composants de chaque module du projet %s, ainsi que de leurs modules.\n\n", projet.nomProjet), this.fontBody));
            document.add(new Chunk("Récapitulatif des produits :\n ", this.fontBold));
            document.add(buildListeRecap(listProduit, listProduitModule));
            document.add(detailProduitAndModule(listProduit, listProduitModule, listComposant, listModuleComposant));
            document.close();
            return byteArrayOutputStream.toByteArray();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param nomProjet
     * @param isDossierTechnique
     * @return
     */
    private PdfPTable buildHeader(String nomProjet, boolean isDossierTechnique) {
        Image logoMadera = null;
        PdfPTable tableHeader = new PdfPTable(2);
        tableHeader.setWidthPercentage(100);
        try {
            //Add and resize image
            logoMadera = Image.getInstance("src/main/resources/img/logo-madera.png");
            logoMadera.scalePercent(35);
            Chunk chunk;
            if(isDossierTechnique) {
                chunk = new Chunk("DOSSIER TECHNIQUE", this.fontTitle);
            } else {
                chunk = new Chunk(String.format("DEVIS - %s", nomProjet), this.fontTitle);
            }
            //Construct header
            PdfPCell cellDevis = new PdfPCell(new Phrase(chunk));
            cellDevis.setBorder(Rectangle.NO_BORDER);
            PdfPCell cellLogoMadera = new PdfPCell(logoMadera);
            cellLogoMadera.setBorder(Rectangle.NO_BORDER);
            cellLogoMadera.setVerticalAlignment(Element.ALIGN_CENTER);
            cellLogoMadera.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableHeader.addCell(cellDevis);
            tableHeader.addCell(cellLogoMadera);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return tableHeader;
    }

    /**
     *
     * @param date
     * @param refProjet
     * @param refClient
     * @return
     */
    private PdfPTable buildTableCoordinates(Date date, String refProjet, String refClient) {
        //Construct table of coordinates
        PdfPTable tableCoordinates = new PdfPTable(2);
        tableCoordinates.setWidthPercentage(100);
        tableCoordinates.addCell(buildCell("Madera", Element.ALIGN_LEFT, false, false, false, false));
        tableCoordinates.addCell(buildCell(String.format("Date : %s", Date.valueOf(LocalDate.now())), Element.ALIGN_RIGHT, false, false, false, false));
        tableCoordinates.addCell(buildCell("85000, Rue Enzo Ferrari, La Roche sur sur Yon", Element.ALIGN_LEFT, false, false, false, false));
        tableCoordinates.addCell(buildCell(String.format("Ref. projet : %s", refProjet), Element.ALIGN_RIGHT, false, false, false, false));
        tableCoordinates.addCell(buildCell("02 51 98 18 32", Element.ALIGN_LEFT, false, false, false, false));
        tableCoordinates.addCell(buildCell(String.format("Ref. client : %s", refClient), Element.ALIGN_RIGHT, false, false, false, false));
        return tableCoordinates;
    }

    /**
     *
     * @param utilisateur
     * @param client
     * @param adresse
     * @return
     */
    private PdfPTable buildTableDestAndSend(Utilisateur utilisateur, Client client, Adresse adresse) {
        PdfPTable tableDestAndSend = new PdfPTable(2);
        tableDestAndSend.setWidthPercentage(100);
        tableDestAndSend.addCell(buildCell("Destinataire", Element.ALIGN_LEFT, true,false , false, false));
        tableDestAndSend.addCell(buildCell("Envoyer à", Element.ALIGN_RIGHT, true, false, false, false));
        tableDestAndSend.addCell(buildCell(utilisateur.getNom() + " " + utilisateur.getPrenom(), Element.ALIGN_LEFT, false, false, false, false));
        tableDestAndSend.addCell(buildCell(client.nom, Element.ALIGN_RIGHT, false, false, false, false));
        tableDestAndSend.addCell(buildCell("Madera", Element.ALIGN_LEFT, false, false, false, false));
        tableDestAndSend.addCell(buildCell(client.prenom, Element.ALIGN_RIGHT, false, false, false, false));
        tableDestAndSend.addCell(buildCell("85000, Rue Enzo Ferrari, La Roche sur sur Yon", Element.ALIGN_LEFT, false, false, false, false));
        tableDestAndSend.addCell(buildCell(adresse.getVille() + " " + adresse.getCodePostale() + ", " + adresse.getNumero() + " " + adresse.getRue() + " " + adresse.getComplement(), Element.ALIGN_RIGHT, false, false, false, false));
        tableDestAndSend.addCell(buildCell(utilisateur.getTel(), Element.ALIGN_LEFT, false, false, false, false));
        tableDestAndSend.addCell(buildCell(client.getNumTel(), Element.ALIGN_RIGHT, false, false, false, false));
        tableDestAndSend.addCell(buildCell(utilisateur.getMail(), Element.ALIGN_LEFT, false, false, false, false));
        tableDestAndSend.addCell(buildCell(client.getMail(), Element.ALIGN_RIGHT, false, false, false, false));
        return tableDestAndSend;
    }

    /**
     *
     * @param content
     * @param alignement
     * @param isSubTitle
     * @param hasBorder
     * @param isItalic
     * @param isBold
     * @return
     */
    private PdfPCell buildCell(String content, int alignement, boolean isSubTitle, boolean hasBorder, boolean isItalic, boolean isBold) {
        Chunk cellContent = new Chunk(content);
        if(isItalic) {
            cellContent.setFont(this.fontItalic);
        }
        if(isBold) {
            cellContent.setFont(this.fontSmallBold);
        }
        if(isSubTitle) {
            cellContent.setFont(this.fontBold);
        }
        PdfPCell cell = new PdfPCell(new Phrase(cellContent));
        if(!hasBorder) {
            cell.setBorder(Rectangle.NO_BORDER);
        }
        cell.setPadding(8f);
        cell.setHorizontalAlignment(alignement);
        return cell;
    }

    /**
     *
     * @param produit
     * @param listProduitModule
     * @return
     */
    private PdfPTable buildTableOfProducts(Produit produit, List<ProduitModule> listProduitModule) {
        PdfPTable tableOfProducts = new PdfPTable(4);
        tableOfProducts.setWidthPercentage(100);
        //Construct header of table
        tableOfProducts.addCell(buildCellHeader("Module"));
        tableOfProducts.addCell(buildCellHeader("Dimension"));
        tableOfProducts.addCell(buildCellHeader("Prix unitaire"));
        tableOfProducts.addCell(buildCellHeader("Total"));
        //End construct header
        //Construct body
            for(ProduitModule produitModule : listProduitModule) {
                if(produit.getProduitId() == produitModule.getProduitId()) {
                    tableOfProducts.addCell(buildCell(produitModule.getProduitModuleNom(), Element.ALIGN_BASELINE, false, true, false, false));
                    tableOfProducts.addCell(buildCell(buildDimensionModule(produitModule.getProduitModuleSectionLongueur()), Element.ALIGN_BASELINE, false, true, false, false));
                    tableOfProducts.addCell(buildCell(String.valueOf(Math.round(produitModule.getPrixModule())), Element.ALIGN_BASELINE, false, true, false, false));
                    tableOfProducts.addCell(buildCell(String.valueOf(Math.round(produitModule.getPrixModule())), Element.ALIGN_BASELINE, false, true, false, false));
                }
            }
        //End contruct body
        return tableOfProducts;
    }

    /**
     *
     * @param sectionLongueur
     * @return
     */
    private String buildDimensionModule(String sectionLongueur) {
        StringBuilder str = new StringBuilder();
        JSONObject jsonObject = new JSONObject(sectionLongueur);
        List<Object> sectionJson = jsonObject.getJSONArray("sections").toList();
        for(Object obj : sectionJson) {
            HashMap map = (HashMap)obj;
            str.append(map.get("longueur"));
            str.append("mm, ");
        }
        return str.toString();
    }

    /**
     *
     * @param content
     * @return
     */
    private PdfPCell buildCellHeader(String content) {
        PdfPCell cellHeader = new PdfPCell(new Phrase(new Chunk(content, this.fontHeader)));
        cellHeader.setPadding(8f);
        cellHeader.setBackgroundColor(new BaseColor(106, 168, 79));
        cellHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cellHeader;
    }

    /**
     *
     * @param sousTotal
     * @param remise
     * @return
     */
    private Paragraph buldParagraphTT(double sousTotal, double remise) {
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        paragraph.setPaddingTop(8f);
        paragraph.add(new Chunk(String.format("Total produit : %s €\n", Math.round(sousTotal)), this.fontBody));
        paragraph.add(new Chunk(String.format("Remise : %s €\n", Math.round(remise)), this.fontBody));
        return paragraph;
    }

    /**
     *
     * @param prixProjet
     * @param listEcheance
     * @return
     */
    private PdfPTable buildTableEcheance(double prixProjet, List<DevisEtat> listEcheance) {
        PdfPTable tableEcheance = new PdfPTable(2);
        tableEcheance.setWidthPercentage(100f);
        for(DevisEtat devisEtat : listEcheance) {
            double calculSomme = Math.round(prixProjet * devisEtat.pourcentageSomme / 100);
            prixProjet -= calculSomme;
            tableEcheance.addCell(buildCell(String.format("%s (%s )", devisEtat.devisEtatLibelle, devisEtat.pourcentageSomme), Element.ALIGN_LEFT, false, false, true, false));
            tableEcheance.addCell(buildCell(Math.round(calculSomme) + " €", Element.ALIGN_RIGHT, false, false, false, true));
        }
        return tableEcheance;
    }

    /**
     *
     * @param listProduit
     * @param listModule
     * @return
     */
    private com.itextpdf.text.List buildListeRecap(List<Produit> listProduit, List<ProduitModule> listModule) {
        com.itextpdf.text.List listPucesProduits = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
        for(Produit produit : listProduit) {
            listPucesProduits.add(new ListItem(new Chunk(produit.getProduitNom(), this.fontSmallBold)));
            com.itextpdf.text.List listPucesModules = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
            for(ProduitModule produitModule : listModule) {
                if(produitModule.getProduitId() == produit.getProduitId()) {
                    listPucesModules.add(new ListItem(new Chunk(String.format("  %s", produitModule.getProduitModuleNom()), this.fontItalic)));

                }
            }
            listPucesProduits.add(listPucesModules);
        }
        return listPucesProduits;
    }

    /**
     *
     * @param listProduit
     * @param listModule
     * @param listComposants
     * @param listModuleComposant
     * @return
     */
    private Paragraph detailProduitAndModule(
            List<Produit> listProduit,
            List<ProduitModule> listModule,
            List<Composant> listComposants,
            List<ModuleComposant> listModuleComposant
    ) {
        Paragraph paragraph = new Paragraph();
        for(Produit produit : listProduit) {
            paragraph.add(new Chunk(String.format("\n\nDescription du produit %s :\n\n", produit.getProduitNom()), this.fontSmallBold));
            for(ProduitModule produitModule : listModule) {
                if(produit.getProduitId() == produitModule.getProduitId()) {
                    paragraph.add(new Chunk(String.format("\nDétail du module %s : \n\n", produitModule.getProduitModuleNom()), this.fontItalic));
                    paragraph.add(buildTableOfComposants(produitModule.getModuleId(), listComposants ,listModuleComposant));
                }
            }
        }
        return paragraph;
    }

    /**
     *
     * @param moduleId
     * @param listComposants
     * @param listModuleComposant
     * @return
     */
    private PdfPTable buildTableOfComposants(Integer moduleId, List<Composant> listComposants, List<ModuleComposant> listModuleComposant) {
        PdfPTable tableOfComposants = new PdfPTable(5);
        tableOfComposants.setWidthPercentage(100);
        //Construct header
        tableOfComposants.addCell(buildCellHeader("Nom Composant"));
        tableOfComposants.addCell(buildCellHeader("Groupe"));
        tableOfComposants.addCell(buildCellHeader("Unité de mesure"));
        tableOfComposants.addCell(buildCellHeader("Sections"));
        tableOfComposants.addCell(buildCellHeader("Prix unitaire (€)"));
        //Construct body
        for(Composant composant : listComposants) {
            for(ModuleComposant moduleComposant : listModuleComposant) {
                if(moduleComposant.getComposantId() == composant.getComposantId() && moduleComposant.getModuleId() == moduleId) {
                    tableOfComposants.addCell(buildCell(composant.getLibelle(), Element.ALIGN_BASELINE, false, true, false, false));
                    tableOfComposants.addCell(buildCell(composant.getLibelleGroupe(), Element.ALIGN_BASELINE, false, true, false, false));
                    tableOfComposants.addCell(buildCell(composant.getUniteUsage(), Element.ALIGN_BASELINE, false, true, false, false));
                    tableOfComposants.addCell(buildCell(String.valueOf(composant.getSection().orElse(null)), Element.ALIGN_BASELINE, false, true, false, false));
                    tableOfComposants.addCell(buildCell(String.valueOf(Math.round(composant.getPrixComposant())), Element.ALIGN_BASELINE, false, true, false, false));
                }
            }
        }
        return tableOfComposants;
    }
}
