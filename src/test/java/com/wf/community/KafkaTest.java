package com.wf.community;

import com.wf.community.util.MailClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/13 11:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class KafkaTest {

    @Resource
    private KafkaProducer kafkaProducer;
    @Test
    public void mailTest() throws Exception {
        kafkaProducer.sendMessage("test","你好");
        kafkaProducer.sendMessage("test","在吗？");
        Thread.sleep(30000);
    }

    @Test
    public void testKafka() {
        kafkaProducer.sendMessage("test", "你好");
        kafkaProducer.sendMessage("test", "在吗");

        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

@Component
class KafkaProducer{
    @Resource
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String topic, String cont){
        kafkaTemplate.send(topic, cont);
    }
}

@Component
class KafkaConsumer {

    @KafkaListener(topics = {"test"})
    public void msgHandle(ConsumerRecord record) {
        System.out.println(record.value());
    }
}