package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.UserDao;
import com.javamentor.qa.platform.exception.ApiChangeUserPasswordException;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl extends ReadWriteServiceImpl<User, Long> implements UserService {
    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        super(userDao);
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void persist(User user) {
        super.persist(user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    @Transactional
    public void changeUserPassword(String userPassword, User user)  {
        if (userPassword == null || userPassword.isEmpty()) {
            throw new ApiChangeUserPasswordException("The password must not be empty");
        }
        if (userPassword.length() < 6) {
            throw new ApiChangeUserPasswordException("The password must be at least 6 characters long");
        }
        if (userPassword.matches("[0-9]+")) {
            throw new ApiChangeUserPasswordException("The password should not consist only of numbers");
        }
        user.setPassword(passwordEncoder.encode(userPassword));
        userDao.changeUserPassword(user);
    }
}
