package com.example.demo.seivice;

import com.example.demo.dao.AuthorityDao;
import com.example.demo.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    public Authority getAuthorityById(long id) {
        return authorityDao.findById(id);
    }
}
