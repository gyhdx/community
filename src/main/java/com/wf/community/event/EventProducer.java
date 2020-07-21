package com.wf.community.event;

import com.alibaba.fastjson.JSONObject;
import com.wf.community.entity.Event;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/21 13:10
 */
@Component
public class EventProducer {

    @Resource
    private KafkaTemplate kafkaTemplate;

    //处理事件
    public void fireEvent(Event event) {
        kafkaTemplate.send(event.getTopic(), JSONObject.toJSONString(event));
    }
}
