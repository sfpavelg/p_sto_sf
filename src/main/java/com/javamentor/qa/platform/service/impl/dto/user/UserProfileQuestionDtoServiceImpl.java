package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.user.UserDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileQuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileQuestionDtoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileQuestionDtoServiceImpl implements UserProfileQuestionDtoService {

    private final UserDtoDao userDtoDao;
    private final TagDtoDao tagDtoDao;

    public UserProfileQuestionDtoServiceImpl(UserDtoDao userDtoDao, TagDtoDao tagDtoDao) {
        this.userDtoDao = userDtoDao;
        this.tagDtoDao = tagDtoDao;
    }

    @Override
    public List<UserProfileQuestionDto> getAllUserProfileQuestionDtoByUserId(Long userId) {
        return userDtoDao.getAllUserProfileQuestionDtoByUserId(userId).stream().peek(
                userProfileQuestionDto -> userProfileQuestionDto.setTags(
                        tagDtoDao.getTagDtoById(userProfileQuestionDto.getQuestionId())
                )).collect(Collectors.toList());
    }

    @Override
    public List<UserProfileQuestionDto> getAllUserRemovedQuestion(Long id) {
        List<UserProfileQuestionDto> list = new ArrayList<>();
        userDtoDao.getAllUserRemovedQuestion(id).stream().forEach(userProfileQuestionDto -> {
            userProfileQuestionDto
                    .setTags(tagDtoDao.getTagDtoById(userProfileQuestionDto.getQuestionId()));

            list.add(userProfileQuestionDto);
        });
        return list;
    }
}