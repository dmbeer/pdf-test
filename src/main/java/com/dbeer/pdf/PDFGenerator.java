package com.dbeer.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dbeer on 23/08/15.
 */
public class PDFGenerator {

    public void generatePDF() {
        Path pdf = Paths.get("/tmp", "test.pdf");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pdf.toFile());
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
            document.open();
            document.add(new Phrase("Movies", FontFactory.getFont(FontFactory.HELVETICA, 26, Color.ORANGE)));
            document.add(addTables());
            document.add(new Paragraph("Second Paragraph", FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 32, Color.RED)));
            document.newPage();
            document.add(title());
            document.add(addParagraph());
            document.close();
        } catch (FileNotFoundException e) {
            Logger.getLogger(PDFGenerator.class.getName()).log(Level.SEVERE, "File Not Found {0}", pdf.toString());
            Logger.getLogger(PDFGenerator.class.getName()).log(Level.SEVERE, "File Not Found", e);
        } catch (DocumentException e) {
           Logger.getLogger(PDFGenerator.class.getName()).log(Level.SEVERE, "Document error", e);
        }


    }

    private Paragraph addTables() {
        Paragraph paragraph1 = new Paragraph();
        PdfPTable table1 = new PdfPTable(2);
        PdfPTable table2 = new PdfPTable(2);
        table1.setTotalWidth(400f);
        table1.setLockedWidth(true);
        table1.setSpacingAfter(10);
        table1.addCell("Hello Table 1");
        table1.completeRow();
        table1.addCell("Blank Cell");
        table1.addCell("Blank Cell");

        table2.setTotalWidth(300f);
        table2.setLockedWidth(true);
        table2.addCell("Hello Table 2");
        table2.completeRow();
        table2.addCell("Blank Cell");
        table2.addCell("Blank Cell");

        paragraph1.add(table1);
        paragraph1.add(table2);

        return paragraph1;
    }

    private Paragraph title() {
        Paragraph paragraph = new Paragraph();
        Chunk title = new Chunk("Declaration", FontFactory.getFont(FontFactory.HELVETICA, 24, Color.MAGENTA));
        paragraph.add(title);
        paragraph.setSpacingAfter(5f);
        return paragraph;
    }

    private Paragraph addParagraph() {
        Paragraph paragraph1 = new Paragraph();
        Phrase sentance = new Phrase("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        Phrase sentance1 = new Phrase(" Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
        Phrase sentance2 = new Phrase(" It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.");
        Phrase sentance3 = new Phrase("It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

        paragraph1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12, Color.BLACK));
        paragraph1.setFirstLineIndent(50);
        paragraph1.add(sentance);
        paragraph1.add(sentance1);
        paragraph1.add(sentance2);
        paragraph1.add(sentance3);

        return paragraph1;
    }
}
