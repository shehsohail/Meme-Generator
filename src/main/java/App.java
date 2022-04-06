package edu.stevens.friccobo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
  public static void main( String[] args ) throws IOException
  {
    // Create a document and add a page to it
    PDDocument document = new PDDocument();
    PDPage page = new PDPage();
    document.addPage( page );

    // Create a new font object selecting one of the PDF base fonts
    PDFont font = PDType1Font.HELVETICA_BOLD;

    // Start a new content stream which will "hold" the to be created content
    PDPageContentStream contentStream = new PDPageContentStream(document, page);

    // Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
    contentStream.beginText();
    contentStream.setFont( font, 12 );
    contentStream.newLineAtOffset( 100, 700 );
    contentStream.showText( "Evan S Everett" );
    contentStream.endText();

    // Make sure that the content stream is closed:
    contentStream.close();

    // Save the results and ensure that the document is properly closed:
    document.save( "Hello World.pdf");
    document.close();
  }
}
