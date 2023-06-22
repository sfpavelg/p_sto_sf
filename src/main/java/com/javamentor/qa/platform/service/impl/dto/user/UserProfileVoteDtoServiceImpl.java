package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileVoteDto;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileVoteDtoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserProfileVoteDtoServiceImpl implements UserProfileVoteDtoService {
    private final UserDtoDao userDtoDao;

    @Override
    public UserProfileVoteDto getUserProfileVoteDtoByUserId(Long userId) {
        return userDtoDao.getUserProfileVoteDtoByUserId(userId);
    }
}
