package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserDtoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDtoServiceImpl implements UserDtoService {

    private final UserDtoDao userDtoDao;

    public UserDtoServiceImpl(UserDtoDao userDtoDao) {
        this.userDtoDao = userDtoDao;
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        return userDtoDao.getById(id);
    }
}
