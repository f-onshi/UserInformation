package com.example.securingweb.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.securingweb.demo.model.UserDto;
import com.example.securingweb.demo.repository.*;


@Service
public class RegistService {

    @Autowired
    private RegistRepository registRepository;

    public void registService(UserDto form) {
        registRepository.userRegist(form);
    }
}
