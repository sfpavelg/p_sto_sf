package com.javamentor.qa.platform.security.jwt;

import com.javamentor.qa.platform.dao.abstracts.model.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetailsService implements UserDetailsService {

    private final UserDao userDao;

    public DetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.getByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Email %s не найден.", email)));
    }
}
