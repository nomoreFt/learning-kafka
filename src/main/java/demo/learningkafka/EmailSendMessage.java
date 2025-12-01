package demo.learningkafka;

public record EmailSendMessage(
        String from,
        String to,
        String subject,
        String body
) {
}
