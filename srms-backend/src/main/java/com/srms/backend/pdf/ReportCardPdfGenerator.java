package com.srms.backend.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.srms.backend.dto.ReportCardDto;
import com.srms.backend.entity.Attendance;

import java.io.ByteArrayOutputStream;

public class ReportCardPdfGenerator {

    public static byte[] generate(ReportCardDto dto) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph("FINAL REPORT CARD"));
            document.add(new Paragraph("Student: " + dto.getStudentName()));
            document.add(new Paragraph("Class: " + dto.getClassName()));
            document.add(new Paragraph(" "));

            // ===== RESULT =====
            document.add(new Paragraph("RESULT"));
            document.add(new Paragraph("Maths: " + dto.getResult().getMaths()));
            document.add(new Paragraph("Science: " + dto.getResult().getScience()));
            document.add(new Paragraph("English: " + dto.getResult().getEnglish()));
            document.add(new Paragraph("Percentage: " + dto.getResult().getPercentage()));
            document.add(new Paragraph("Grade: " + dto.getResult().getGrade()));
            document.add(new Paragraph(" "));

            // ===== ATTENDANCE =====
            long presentDays = dto.getAttendanceList()
                    .stream().filter(a -> a.isAttended()).count();

            document.add(new Paragraph("ATTENDANCE"));
            document.add(new Paragraph("Total Days: " + dto.getAttendanceList().size()));
            document.add(new Paragraph("Present Days: " + presentDays));
            document.add(new Paragraph(" "));

            // ===== FEES =====
            document.add(new Paragraph("FEES"));
            dto.getFees().forEach(fee -> {
                try {
                    document.add(new Paragraph(
                            "Total: ₹" + fee.getTotalAmount() +
                                    ", Paid: ₹" + fee.getPaidAmount() +
                                    ", Due: ₹" + fee.getDueAmount()
                    ));
                } catch (Exception ignored) {}
            });

            document.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return out.toByteArray();
    }
}
