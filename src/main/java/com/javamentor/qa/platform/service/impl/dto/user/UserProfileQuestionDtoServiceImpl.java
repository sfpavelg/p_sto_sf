package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.user.UserDtoDao;
import com.javamentor.qa.platform.models.dto.BookmarksDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.models.dto.user.UserProfileQuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileQuestionDtoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        List <UserProfileQuestionDto> userProfileQuestionDtos = userDtoDao.getAllUserProfileQuestionDtoByUserId(userId);
        List<Long> questionIdList = userProfileQuestionDtos.stream()
                .map(UserProfileQuestionDto::getQuestionId)
                .collect(Collectors.toList());
        Map<Long, List<TagDto>> tags = tagDtoDao.getMapTagDtoAndQuestionId(questionIdList);
        userProfileQuestionDtos.forEach(b -> b.setTags(tags.get(b.getQuestionId())));
        userProfileQuestionDtos.stream().filter(s->s.getTags()==null).forEach(s->s.setTags(new ArrayList<>()));
        return userProfileQuestionDtos;
    }


    @Override
    public List<UserProfileQuestionDto> getAllUserRemovedQuestion(Long id) {
        List <UserProfileQuestionDto> userProfileQuestionDtos = userDtoDao.getAllUserRemovedQuestion(id);
        List<Long> questionIdList = userProfileQuestionDtos.stream()
                .map(UserProfileQuestionDto::getQuestionId)
                .collect(Collectors.toList());
        Map<Long, List<TagDto>> tags = tagDtoDao.getMapTagDtoAndQuestionId(questionIdList);
        userProfileQuestionDtos.forEach(b -> b.setTags(tags.get(b.getQuestionId())));
        userProfileQuestionDtos.stream().filter(s->s.getTags()==null).forEach(s->s.setTags(new ArrayList<>()));
        return userProfileQuestionDtos;
    }
}