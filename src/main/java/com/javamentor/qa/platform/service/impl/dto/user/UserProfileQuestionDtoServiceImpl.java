package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.user.UserProfileQuestionDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileQuestionDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileQuestionDtoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileQuestionDtoServiceImpl implements UserProfileQuestionDtoService {

    private final UserProfileQuestionDtoDao userProfileQuestionDtoDao;
    private final TagDtoDao tagDtoDao;

    public UserProfileQuestionDtoServiceImpl(UserProfileQuestionDtoDao userProfileQuestionDtoDao, TagDtoDao tagDtoDao) {
        this.userProfileQuestionDtoDao = userProfileQuestionDtoDao;
        this.tagDtoDao = tagDtoDao;
    }

    @Override
    public List<UserProfileQuestionDto> getAllUserAuthorizedQuestions(User user) {
        return userProfileQuestionDtoDao.getAllUserProfileQuestionDtoByUserId(user.getId()).stream().peek(
                userProfileQuestionDto -> userProfileQuestionDto.setTags(
                        tagDtoDao.getTagDtoById(userProfileQuestionDto.getQuestionId())
                )).collect(Collectors.toList());
    }
}