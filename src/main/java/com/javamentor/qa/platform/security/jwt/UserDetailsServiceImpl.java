package com.javamentor.qa.platform.security.jwt;

import com.javamentor.qa.platform.dao.abstracts.model.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.getByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Email %s не найден.", email)));
    }
}
