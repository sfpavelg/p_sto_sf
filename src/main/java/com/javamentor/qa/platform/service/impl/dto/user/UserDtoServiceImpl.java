package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.user.UserDtoDao;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserDtoService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserDtoServiceImpl extends PageDtoService<UserDto> implements UserDtoService {

    private final UserDtoDao userDtoDao;

    public UserDtoServiceImpl(Map<String, PageDtoDao<UserDto>> beansMap, UserDtoDao userDtoDao) {
        super(beansMap);
        this.userDtoDao = userDtoDao;
    }

    @Override
    public UserDto getById(Long id) throws NotFoundException {
        Optional<UserDto> userDtoOptional = userDtoDao.getById(id);
        if (userDtoOptional.isPresent()) {
            return userDtoOptional.get();
        }

        throw new NotFoundException("User with id = " + id + " not found");
    }

    @Override
    public PageDto<UserDto> getUsersByPersistDateTime(HashMap<String, Object> param) throws NotFoundException {
        param.put("daoDtoImpl", "userDtoDaoImpl");
        param.put("sortBy", "persistDateTime");
        PageDto<UserDto> page = pageDto(param);

        if (page.getItems().isEmpty()) {
            throw new NotFoundException("The page with " + param.get("currentPageNumber") + " number was not found");
        }

        return page;
    }
}
