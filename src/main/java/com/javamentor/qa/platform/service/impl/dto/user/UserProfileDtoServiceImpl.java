package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.tag.UserTagFavoriteDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.user.UserDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileDto;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileDtoService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserProfileDtoServiceImpl implements UserProfileDtoService {
    private UserDtoDao userDtoDao;
    private UserTagFavoriteDtoDao userTagFavoriteDtoDao;
    @Override
    public UserProfileDto getUserProfileDtoByUserId(Long userId) throws NotFoundException {
            Optional<UserProfileDto> userProfileDto = userDtoDao.getUserProfileDtoByUserId(userId);
            if(userProfileDto.isEmpty()) {
                throw new NotFoundException("User with this id:" + userId + " not found");
            }
            UserProfileDto userProfileDtoWithListTag = userProfileDto.get();
            userProfileDtoWithListTag.setUserTagFavoriteDtoList(userTagFavoriteDtoDao.getByUserId(userId));
            return userProfileDtoWithListTag;
    }
}
