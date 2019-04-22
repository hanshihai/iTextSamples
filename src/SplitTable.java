/*
 * $Id: SplitTable.java,v 1.3 2005/05/09 11:52:48 blowagie Exp $
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

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Break a large table up into different smaller tables in order to save memory.
 */
public class SplitTable {
	/**
	 * Break a large table up into several smaller tables for memory management
	 * purposes.
	 * 
	 * @param args
	 *            the number of rows for each table fragment.
	 */
	public static void main(String[] args) {

		System.out.println("Split Table");
		// step1
		Document document = new Document(PageSize.A4, 10, 10, 10, 10);
		try {
			// step2
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
					"SplitTable.pdf"));
			// step3
			document.open();
			// step4

            PdfContentByte cb = writer.getDirectContent();
            PdfPTable table = new PdfPTable(10);
            for (int k = 1; k <= 100; ++k) {
                table.addCell("The number " + k);
            }
            table.setTotalWidth(800);
            table.writeSelectedRows(0, 5, 0, -1, 50, 650, cb);
            document.newPage();
            table.writeSelectedRows(5, -1, 0, -1, 50, 650, cb);
            document.close();
		} catch (Exception de) {
			de.printStackTrace();
		}
		// step5
		document.close();
	}
}