package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserProfileTagDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileTagDto;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileTagDtoService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserProfileTagDtoServiceImpl implements UserProfileTagDtoService {

    private final UserProfileTagDtoDao userProfileTagDtoDao;

    public UserProfileTagDtoServiceImpl(UserProfileTagDtoDao userProfileTagDtoDao) {
        this.userProfileTagDtoDao = userProfileTagDtoDao;
    }

    @Override
    public List<UserProfileTagDto> getUserProfileTagDto(Long userId) {

        Map<String, Long> tags = userProfileTagDtoDao.getUserProfileTagDtoWithoutVotesByUserId(userId);
        Map<String, Long> votes = userProfileTagDtoDao.getTagVotesByList(tags);


        List<UserProfileTagDto> resultList = new ArrayList<>();
        for (Map.Entry<String, Long> entry : tags.entrySet()) {
            resultList.add(new UserProfileTagDto(entry.getKey(), votes.containsKey(entry.getKey()) ? votes.get(entry.getKey()) : 0, entry.getValue()));
        }


        return resultList;
    }
}
