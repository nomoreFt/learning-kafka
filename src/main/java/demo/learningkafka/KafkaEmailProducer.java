package demo.learningkafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEmailProducer implements EmailProducer {
    private static final String EMAIL_SEND_TOPIC = "email.send";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaEmailProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendEmail(EmailSendMessage message) {
        kafkaTemplate.send(EMAIL_SEND_TOPIC, JsonUtils.toJsonString(message));
    }
}

