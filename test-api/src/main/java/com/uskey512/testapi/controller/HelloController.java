package com.uskey512.testapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uskey512.testapi.dto.response.MessageDto;

/**
 * Created by uskey512 on 2017/09/27.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public MessageDto sayHello() {
        MessageDto messageDto = new MessageDto();
        messageDto.setContent("hello world");

        return messageDto;
    }
}
