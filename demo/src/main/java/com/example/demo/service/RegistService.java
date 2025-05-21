package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.UserDto;
import com.example.demo.repository.*;


@Service
public class RegistService {

    @Autowired
    private RegistRepository registRepository;

    public void registService(UserDto form) {
        registRepository.userRegist(form);
    }
}
