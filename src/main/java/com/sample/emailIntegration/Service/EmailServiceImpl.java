package com.sample.emailIntegration.Service;

import com.sample.emailIntegration.Entity.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

// Annotation
@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details) {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    // Method 2
    // To send an email with attachment
    public String
    sendMailWithAttachment(EmailDetails details) {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        String metaTrack = "workflow-release-testing-germany-1";
        String track = "Alpha Testing Germany 1";
        try {

            // Setting multipart as true for attachments t0 be send
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            String msgbopdy = "<h2>Dear Approver <h2>" +
                    "<p> The quality gate for " + track + " , " + metaTrack + " iswaiting for the approval, </p>" +
                    "<p>please approve. </p>";
            mimeMessageHelper.setText(msgbopdy, true);
            mimeMessageHelper.setSubject(details.getSubject());


            // Adding the attachment
            /*FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);*/

            // Sending the mail
            javaMailSender.send(mimeMessage);

            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {
            e.printStackTrace();
            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
}