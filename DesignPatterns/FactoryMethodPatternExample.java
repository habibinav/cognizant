interface Document {
    void open();
    void save();
    void close();
}

class WordDocument implements Document {
    public void open()  { System.out.println("Opening Word document (.docx)"); }
    public void save()  { System.out.println("Saving Word document (.docx)"); }
    public void close() { System.out.println("Closing Word document (.docx)"); }
}

class PdfDocument implements Document {
    public void open()  { System.out.println("Opening PDF document (.pdf)"); }
    public void save()  { System.out.println("Saving PDF document (.pdf)"); }
    public void close() { System.out.println("Closing PDF document (.pdf)"); }
}

class ExcelDocument implements Document {
    public void open()  { System.out.println("Opening Excel document (.xlsx)"); }
    public void save()  { System.out.println("Saving Excel document (.xlsx)"); }
    public void close() { System.out.println("Closing Excel document (.xlsx)"); }
}

abstract class DocumentFactory {
    public abstract Document createDocument();

    public Document openDocument() {
        Document doc = createDocument();
        doc.open();
        return doc;
    }
}

class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new WordDocument(); }
}

class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new PdfDocument(); }
}

class ExcelDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new ExcelDocument(); }
}

public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Test ===\n");

        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        System.out.println("--- Word Document ---");
        Document word = wordFactory.openDocument();
        word.save();
        word.close();

        System.out.println("\n--- PDF Document ---");
        Document pdf = pdfFactory.openDocument();
        pdf.save();
        pdf.close();

        System.out.println("\n--- Excel Document ---");
        Document excel = excelFactory.openDocument();
        excel.save();
        excel.close();

        System.out.println("\n=== Factory Method Pattern Demo Complete ===");
    }
}
