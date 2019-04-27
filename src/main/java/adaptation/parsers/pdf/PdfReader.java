package adaptation.parsers.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import java.io.File;
import java.io.IOException;

import static adaptation.parsers.file.FileUtilsWrapper.saveFileFromUrl;

public class PdfReader {

    public void pdfOperation() throws IOException {
        String dirName = "pdf";
        String pdfUrl = "pdfUrl";

        String filepath = dirName + "\\file.pdf";
        saveFileFromUrl(pdfUrl, filepath);
        PDDocument doc = PDDocument.load(new File(filepath));
        int count = doc.getNumberOfPages();

        PDDocumentInformation info = doc.getDocumentInformation();
        String he = info.getTitle();
    }
}
