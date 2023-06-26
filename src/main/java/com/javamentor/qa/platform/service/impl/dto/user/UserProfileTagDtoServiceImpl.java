package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserProfileTagDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileTagDto;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileTagDtoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<String> tagNames = tags.stream().map(userProfileTagDto -> userProfileTagDto.getTagName()).collect(Collectors.toList());
        Map<String, Long> votes = userProfileTagDtoDao.getTagVotesFromQuestionsByList(tagNames);
        Map<String, Long> answerVotes = userProfileTagDtoDao.getTagVotesFromAnswersByList(tagNames);

        for (Map.Entry<String, Long> entry : answerVotes.entrySet()) {
            votes.put(entry.getKey(), Long.sum(votes.containsKey(entry.getKey()) ? votes.get(entry.getKey()) : 0, entry.getValue()));
        }
        List<UserProfileTagDto> resultList = new ArrayList<>();

        for (int i = 0; i < tags.size(); i++) {
//            if (votes.containsKey(tags.get(i).getTagName())) {
//                tags.get(i).setCountVoteTag(votes.get(tags.get(i).getTagName()));
//                resultList.add(tags.get(i));
//            } else {
//                tags.get(i).setCountAnswerQuestion(0L);
//                resultList.add(tags.get(i));
//            }

        }
        for (Map.Entry<String, Long>  tag : votes.entrySet() ) {
//            var countVote = tag.getKey();
//            var countAnswerQuestion = tag.getValue();
//            tags.

        }

//        var countVote = votes.get(tag.getTagName());
//        var countAnswerQuestion =  answerVotes.get(tag.getTagName())
//        tag.setCountVoteTag(countVote == null? 0 : countVote)
//        tag.setCountAnswerQuestion(...)



        return resultList;
    }
}
