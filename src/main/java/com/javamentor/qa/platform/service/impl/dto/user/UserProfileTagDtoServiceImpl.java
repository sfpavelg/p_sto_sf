package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserProfileTagDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileTagDto;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileTagDtoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileTagDtoServiceImpl implements UserProfileTagDtoService {

    private final UserProfileTagDtoDao userProfileTagDtoDao;

    public UserProfileTagDtoServiceImpl(UserProfileTagDtoDao userProfileTagDtoDao) {
        this.userProfileTagDtoDao = userProfileTagDtoDao;
    }

    @Override
    public List<UserProfileTagDto> getUserProfileTagDto(Long userId) {
        List<UserProfileTagDto> tagDtoList = userProfileTagDtoDao.getUserProfileTagDtoWithoutVotesByUserId(userId);
        List<String> tagList = new ArrayList<>();
        tagDtoList.stream().forEach(dto -> tagList.add(dto.getTagName()));
        List<Object[]> votesByList = userProfileTagDtoDao.getTagVotesByList(tagList);

        for (int i = 0; i < tagDtoList.size(); i++) {
            tagDtoList.get(i).getTagName();
            for (Object[] o : votesByList) {
                if (tagDtoList.get(i).getTagName().equals(o[0])) {
                    tagDtoList.get(i).setCountVoteTag(Long.valueOf(o[1].toString()));
                }
            }
        }
        return tagDtoList;
    }
}
