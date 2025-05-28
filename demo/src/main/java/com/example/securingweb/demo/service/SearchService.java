package com.example.securingweb.demo.service;

import org.springframework.stereotype.Service;

import com.example.securingweb.demo.model.*;
import com.example.securingweb.demo.repository.SearchRepository;

import java.util.List;

@Service
public class SearchService {
    
    private final SearchRepository userSearchRepository;

    public SearchService(SearchRepository userSearchRepository) {
        this.userSearchRepository = userSearchRepository;
    }

    public List<UserDto> searchItems(UserDto form) {

        //controllerから受け取ったUseDtoオブジェクトをリポジトリに渡す
        return userSearchRepository.searchUser(form);
    }
    
}
