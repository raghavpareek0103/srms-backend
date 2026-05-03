package com.srms.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendResultPdf(String to, byte[] pdf) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("Student Result");
            helper.setText("Please find attached result PDF.");

            // ✅ CORRECT WAY
            helper.addAttachment(
                    "result.pdf",
                    new ByteArrayResource(pdf)
            );

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send result email", e);
        }
    }
    public void sendAbsentMail(String to, String studentName) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, false);

            helper.setTo(to);
            helper.setSubject("Attendance Alert");
            helper.setText(
                    "Your child " + studentName + " is ABSENT today."
            );

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendFeeDueMail(String to, String studentName, double dueAmount) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, false);

            helper.setTo(to);
            helper.setSubject("Fee Due Reminder");
            helper.setText(
                    "Dear Parent,\n\n" +
                            "Pending fee for student " + studentName +
                            " is ₹" + dueAmount + ".\n" +
                            "Please clear the dues at the earliest.\n\n" +
                            "School Administration"
            );

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendAnnouncementMail(String to, String title, String message) {

        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mail, false);

            helper.setTo(to);
            helper.setSubject("School Announcement: " + title);
            helper.setText(message);

            mailSender.send(mail);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
