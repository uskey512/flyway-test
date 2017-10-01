package com.uskey512.testapi.controller;

import com.uskey512.testapi.dto.request.UserCreateDto;
import com.uskey512.testapi.dto.response.UserDto;
import com.uskey512.testapi.exception.NotFoundException;
import com.uskey512.testapi.entity.User;
import com.uskey512.testapi.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.BeanUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by uskey512 on 2017/10/01.
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "{id}")
    public UserDto index(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user notfound by id : " + id));
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);

        return userDto;
    }

    @PostMapping(value = "/create", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody UserCreateDto userCreateDto){
        User user = new User();
        log.error(userCreateDto.getName());
        user.setName(userCreateDto.getName());
        userRepository.save(user);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ RuntimeException.class })
    @ResponseBody
    public Map<String, Object> handleExcepton() {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "許可されていないメソッド");
        errorMap.put("status", HttpStatus.METHOD_NOT_ALLOWED);
        return errorMap;
    }
}
