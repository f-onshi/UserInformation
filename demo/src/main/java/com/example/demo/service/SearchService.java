package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.repository.SearchRepository;
import com.example.demo.model.*;
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
