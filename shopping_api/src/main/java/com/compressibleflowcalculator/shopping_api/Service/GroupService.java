package com.compressibleflowcalculator.shopping_api.Service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.compressibleflowcalculator.shopping_api.Entity.Group;
import com.compressibleflowcalculator.shopping_api.Repository.GroupRepository;

@Service
public class GroupService {

    @Autowired
    private KafkaTemplate<String, Object> template;

    @Autowired
    private GroupRepository GroupRepository;

    public void NewGroup(Group message) {
        // template.send("groups", message.getId(), null, message.getId(), message);
        CompletableFuture<SendResult<String, Object>> result = template.send("groups", message);
        // result.get();
        result.whenComplete((r, ex) -> {

        });
    }

}
