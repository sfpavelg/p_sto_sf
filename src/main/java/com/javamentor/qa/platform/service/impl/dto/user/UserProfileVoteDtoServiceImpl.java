package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.model.VoteAnswerDao;
import com.javamentor.qa.platform.dao.abstracts.model.VoteQuestionDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileVoteDto;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileVoteDtoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserProfileVoteDtoServiceImpl implements UserProfileVoteDtoService {
    private VoteAnswerDao voteAnswerDao;
    private VoteQuestionDao voteQuestionDao;

    @Override
    public Long getQuestionsCountVoteUpOrDown(Long userId, Boolean upTrueDownFalse) {
        if (upTrueDownFalse) {
            return (long) voteQuestionDao.getAllVotesUpOrDownByUserId(userId, VoteType.UP_VOTE).size();
        } else return (long) voteQuestionDao.getAllVotesUpOrDownByUserId(userId, VoteType.DOWN_VOTE).size();
    }
    @Override
    public Long getAnswersCountVoteUpOrDown(Long userId, Boolean upTrueDownFalse) {
        if (upTrueDownFalse) {
            return (long) voteAnswerDao.getAllVotesUpOrDownByUserId(userId, VoteType.UP_VOTE).size();
        } else return (long) voteAnswerDao.getAllVotesUpOrDownByUserId(userId, VoteType.DOWN_VOTE).size();
    }

    @Override
    public UserProfileVoteDto getUserProfileVoteDtoByUserId(Long userId) {
        return new UserProfileVoteDto(
                getAnswersCountVoteUpOrDown(userId, true) + getQuestionsCountVoteUpOrDown(userId, true),
                getAnswersCountVoteUpOrDown(userId, false) + getQuestionsCountVoteUpOrDown(userId, false),
                getQuestionsCountVoteUpOrDown(userId, false) + getQuestionsCountVoteUpOrDown(userId, true),
                getAnswersCountVoteUpOrDown(userId, true) + getAnswersCountVoteUpOrDown(userId, false),
                voteAnswerDao.getCountVotesLastMonth(userId) + voteQuestionDao.getAllVotesLastMonth(userId)
        );
    }
}
