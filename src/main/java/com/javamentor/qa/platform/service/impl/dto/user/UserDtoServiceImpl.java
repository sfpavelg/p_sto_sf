package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserDtoService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDtoServiceImpl implements UserDtoService {

    private final UserDtoDao userDtoDao;

    @Override
    public UserDto getById(Long id) throws NotFoundException {
        Optional<UserDto> userDtoOptional = userDtoDao.getById(id);
        if (userDtoOptional.isPresent()) {
            return userDtoOptional.get();
        }

        throw new NotFoundException("User with id = " + id + " not found");
    }
}
