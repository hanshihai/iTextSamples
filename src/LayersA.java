/*
 * $Id: Layers.java,v 1.3 2005/05/09 11:52:49 blowagie Exp $
 * $Name:  $
 *
 * This code is part of the 'iText Tutorial'.
 * You can find the complete tutorial at the following address:
 * http://itextdocs.lowagie.com/tutorial/
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * itext-questions@lists.sourceforge.net
 */

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Explains the concept concerning PdfContentByte layers.
 */
public class LayersA {
    
    /**
     * Draws different things into different layers.
     * @param args no arguments needed
     */
    public static void main(String[] args) {
        
        System.out.println("Layers");
        
        // step 1: creation of a document-object
        Document document = new Document();
        
        try {
            
            // step 2: creation of the writer
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("layers.pdf"));
            
            // step 3: we open the document
            document.open();
            
            // step 4:
            
            // high level
            Paragraph p = new Paragraph();
            for (int i = 0; i < 100; i++) p.add(new Chunk("Blah blah blah blah blah. "));
            document.add(p);
            Image img = Image.getInstance("hitchcock.png");
            img.setAbsolutePosition(100, 500);
            document.add(img);
            
            // low level
            PdfContentByte cb = writer.getDirectContent();
            PdfContentByte cbu = writer.getDirectContentUnder();
            cb.setRGBColorFill(0xFF, 0xFF, 0xFF);
            cb.circle(250.0f, 500.0f, 50.0f);
            cb.fill();
            cbu.setRGBColorFill(0xFF, 0x00, 0x00);
            cbu.circle(250.0f, 500.0f, 100.0f);
            cbu.fill();
            
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        
        // step 5: we close the document
        document.close();
    }

}
