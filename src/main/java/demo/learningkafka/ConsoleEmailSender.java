package demo.learningkafka;

import org.springframework.stereotype.Component;

@Component
public class ConsoleEmailSender implements EmailSender {

    @Override
    public void send(EmailSendMessage message) {
        try {
            Thread.sleep(3000);
            if(message.to().equals("fail@naver.com")){
                throw  new RuntimeException("Simulated email sending failure");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf(
                "[EmailSender] Simulate sending mail from '%s' to '%s'%nSubject: %s%nBody: %s%n",
                message.from(),
                message.to(),
                message.subject(),
                message.body()
        );
    }
}

