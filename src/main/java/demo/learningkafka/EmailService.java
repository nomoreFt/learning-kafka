package demo.learningkafka;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final EmailProducer emailProducer;

    public EmailService(EmailProducer emailProducer) {
        this.emailProducer = emailProducer;
    }

    public void sendEmail(SendEmailRequest request) {
        EmailSendMessage emailMessage = new EmailSendMessage(
                request.from(),
                request.to(),
                request.subject(),
                request.body()
        );

        emailProducer.sendEmail(emailMessage);
    }
}
