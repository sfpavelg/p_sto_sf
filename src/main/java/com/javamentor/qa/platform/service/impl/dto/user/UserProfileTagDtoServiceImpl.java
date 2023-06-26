package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserProfileTagDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileTagDto;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileTagDtoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserProfileTagDtoServiceImpl implements UserProfileTagDtoService {

    private final UserProfileTagDtoDao userProfileTagDtoDao;

    public UserProfileTagDtoServiceImpl(UserProfileTagDtoDao userProfileTagDtoDao) {
        this.userProfileTagDtoDao = userProfileTagDtoDao;
    }

    @Override
    public List<UserProfileTagDto> getUserProfileTagDto(Long userId) {
        List<UserProfileTagDto> tags = userProfileTagDtoDao.getUserProfileTagDtoWithoutVotesByUserId(userId);
        List<String> tagNames = tags.stream().map(userProfileTagDto -> userProfileTagDto.getTagName())
                .collect(Collectors.toList());
        Map<String, Long> votes = userProfileTagDtoDao.getTagVotesFromQuestionsByList(tagNames);
        Map<String, Long> answerVotes = userProfileTagDtoDao.getTagVotesFromAnswersByList(tagNames);

        for (UserProfileTagDto tag : tags) {
            var countVote = Long.sum(votes.getOrDefault(tag.getTagName(), 0L)
                    , answerVotes.getOrDefault(tag.getTagName(), 0L));
            tags.get(tags.lastIndexOf(tag)).setCountVoteTag(countVote);
        }
        return tags;
    }
}
