package com.bookmyshow.bms.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    // Inject the sender email defined in application.properties
    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends a confirmation email to the client after a successful booking.
     * @param toEmail The client's email address.
     * @param userName The name of the client.
     * @param bookingId The unique booking ID.
     * @param seats The list of booked seats.
     */
    public void sendBookingConfirmation(String toEmail, String userName, Long bookingId, List<String> seats) {
        
        // --- NOTE: In a real system, fetch Movie/Show details here based on showId ---
        String movieTitle = "Movie Title Placeholder";
        String showDetails = "Theatre Name, Show Time"; 
        // ----------------------------------------------------------------------------

        SimpleMailMessage message = new SimpleMailMessage();
        
        // Handle null email gracefully (e.g. log error or skip)
        if (toEmail == null || toEmail.isEmpty()) {
            System.err.println("Cannot send email: Recipient email is null or empty.");
            return;
        }

        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("✅ Booking Confirmation: Your Tickets (ID: " + bookingId + ")");
        
        String body = String.format(
            "Dear %s,\n\n" +
            "Your booking has been confirmed! Here are your details:\n\n" +
            "--------------------------------------------------\n" +
            "  Movie: %s\n" +
            "  Show: %s\n" +
            "  Seats: %s\n" +
            "  Booking ID: %d\n" +
            "--------------------------------------------------\n\n" +
            "Thank you for booking with MOVIEBOOKER!",
            userName, 
            movieTitle, 
            showDetails, 
            String.join(", ", seats), 
            bookingId
        );
        
        message.setText(body);
        
        try {
            mailSender.send(message);
            System.out.println("Email sent successfully to: " + toEmail);
        } catch (Exception e) {
            // Log the error but don't fail the booking if the email is secondary.
            System.err.println("Error sending email to " + toEmail + ": " + e.getMessage());
        }
    }
}