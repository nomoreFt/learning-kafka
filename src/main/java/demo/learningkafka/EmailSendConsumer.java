package demo.learningkafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.stereotype.Component;

@Component
public class EmailSendConsumer {
    private final EmailSender emailSender;

    public EmailSendConsumer(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @KafkaListener(
            topics = "email.send",
            groupId = "email-send-group",
            concurrency = "3")
    @RetryableTopic(
            attempts = "5",
            backOff = @BackOff(delay = 1000, multiplier = 2),
            dltTopicSuffix = ".dlt",
            dltStrategy = DltStrategy.FAIL_ON_ERROR)
    public void consume(String message) {
        EmailSendMessage emailSendMessage = JsonUtils.fromJsonString(message, EmailSendMessage.class);
        emailSender.send(emailSendMessage);
    }

    @DltHandler
    public void consumeDltMessage(String message, ConsumerRecord<String, String> record) {
        System.out.printf(
                "[EmailDltConsumer] 로그 시스템에 DLT 메시지 전송: %s (topic=%s, partition=%d, offset=%d)%n",
                message,
                record.topic(),
                record.partition(),
                record.offset()
        );
        System.out.println("[EmailDltConsumer] Slack에 알림 발송");
    }
}
