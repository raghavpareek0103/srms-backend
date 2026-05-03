package com.srms.backend.pdf;

import com.srms.backend.entity.Result;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

public class ResultPdfGenerator {

    public static byte[] generate(Result result) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph("STUDENT RESULT"));
            document.add(new Paragraph("Name: " + result.getStudentName()));
            document.add(new Paragraph("Class: " + result.getClassName()));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Maths: " + result.getMaths()));
            document.add(new Paragraph("Science: " + result.getScience()));
            document.add(new Paragraph("English: " + result.getEnglish()));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Total: " + result.getTotal()));
            document.add(new Paragraph("Percentage: " + result.getPercentage()));
            document.add(new Paragraph("Grade: " + result.getGrade()));

            document.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return out.toByteArray();
    }
}
