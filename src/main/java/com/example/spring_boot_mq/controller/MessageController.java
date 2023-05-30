package com.example.spring_boot_mq.controller;

import com.example.spring_boot_mq.model.StoredMessage;
import com.example.spring_boot_mq.service.StoredMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final StoredMessageService storedMessageService;

    @GetMapping
    public List<StoredMessage> getAllMessages() {
        return storedMessageService.getAllMessages();
    }

    @GetMapping("/{id}/send")
    public void sendToActiveMq(@PathVariable Long id) {
        storedMessageService.sendMessageToActiveMq(id);
    }
}
