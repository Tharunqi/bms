package com.bookmyshow.bms.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
     * 
     * @param toEmail The client's email address.
     * @param booking The booking object containing details.
     */
    public void sendBookingConfirmation(String toEmail, com.bookmyshow.bms.model.Booking booking) {

        String movieTitle = "N/A";
        String showDetails = "N/A";

        if (booking.getShow() != null) {
            if (booking.getShow().getMovie() != null) {
                movieTitle = booking.getShow().getMovie().getTitle();
            }
            if (booking.getShow().getTheatre() != null) {
                showDetails = booking.getShow().getTheatre().getName() + ", " + booking.getShow().getShowTime();
            }
        }

        SimpleMailMessage message = new SimpleMailMessage();

        // Handle null email gracefully (e.g. log error or skip)
        if (toEmail == null || toEmail.isEmpty()) {
            System.err.println("Cannot send email: Recipient email is null or empty.");
            return;
        }

        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("✅ Booking Confirmation: Your Tickets (ID: " + booking.getId() + ")");

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
                booking.getUserName(),
                movieTitle,
                showDetails,
                booking.getSeatNumbers(),
                booking.getId());

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