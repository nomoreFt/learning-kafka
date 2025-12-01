package demo.learningkafka;

public record SendEmailRequest(
        String from,
        String to,
        String subject,
        String body
) {
}
