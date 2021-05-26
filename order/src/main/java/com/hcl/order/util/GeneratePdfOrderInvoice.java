package com.hcl.order.util;

import com.hcl.order.model.ItemQuantity;
import com.hcl.order.model.Order;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

public class GeneratePdfOrderInvoice {
    private static final Logger logger = LoggerFactory.getLogger(GeneratePdfOrderInvoice.class);

    public static void OrderReport(Order order) throws DocumentException, IOException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:/FoodOrderingSystemDesign/invoiceReport_" + order.getId() + ".pdf"));
        document.open();

        // Invoice Header
        Paragraph paragraph = new Paragraph("Invoice Order : " + order.getId());
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        // Customer Details
        paragraph = new Paragraph("Customer Details : " + order.getUserInfo().getFirstName());
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);

        // Restaurant Details
        paragraph = new Paragraph("Restaurant Details : Sayaji");
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);

        paragraph = new Paragraph("Ordered Items");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        // Order List
        PdfPTable table = new PdfPTable(4);
        // Table Header
        Stream.of("Item Name", "Price", "Quantity", "Total Price").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(1.5f);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });

        for (ItemQuantity item : order.getItems()) {
            // Table Body
            table.addCell(item.getName());
            table.addCell(String.valueOf(item.getPrice()));
            table.addCell(String.valueOf(item.getQuantity()));
            table.addCell(String.valueOf(item.getQuantity() * item.getPrice()));
        }
        document.add(table);
        Double total = order.getItems().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();

        paragraph = new Paragraph("Total Cost : " + total);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);

        double totalTax = (total / 100) * 5;
        paragraph = new Paragraph("Total Tax : (" + 5 + " % ) : " + totalTax);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);

        paragraph = new Paragraph("Total Bill : " + (total + totalTax));
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);

        document.close();

    }

}
