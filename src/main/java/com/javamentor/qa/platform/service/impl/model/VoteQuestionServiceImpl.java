package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.dao.abstracts.model.VoteQuestionDao;
import com.javamentor.qa.platform.exception.VoteException;
import com.javamentor.qa.platform.models.dto.question.VoteQuestionDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.VoteQuestion;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.answer.VoteQuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.model.ReputationService;
import com.javamentor.qa.platform.service.abstracts.model.VoteQuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class VoteQuestionServiceImpl extends ReadWriteServiceImpl<VoteQuestion, Long> implements VoteQuestionService {

    private final VoteQuestionDtoService voteQuestionDtoService;
    private final VoteQuestionDao voteQuestionDao;
    private final ReputationService reputationService;


    public VoteQuestionServiceImpl(ReadWriteDao<VoteQuestion, Long> readWriteDao, VoteQuestionDtoService voteQuestionDtoService, VoteQuestionDao voteQuestionDao, ReputationService reputationService) {
        super(readWriteDao);
        this.voteQuestionDtoService = voteQuestionDtoService;
        this.voteQuestionDao = voteQuestionDao;
        this.reputationService = reputationService;
    }

    @Override
    public boolean isUserAlreadyVoted(Question question, User user) {
        List<VoteQuestion> list = question.getVoteQuestions(); // не понимаю тогда как проверить голосовал ли пользователь
        for (VoteQuestion voteQuestion : list) {
            if (voteQuestion.getUser().getId().equals(user.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public VoteQuestion voteUpQuestion(Question question, User user) {
        if (isUserAlreadyVoted(question, user)) {
            Optional<VoteQuestionDto> optionalVoteQuestion = voteQuestionDtoService.getVoteByQuestionIdAndUserId(question.getId(), user.getId());
            if (optionalVoteQuestion.isPresent()) {
                VoteQuestion voteQuestion = new VoteQuestion(user, question, VoteType.UP_VOTE);
                if (voteQuestion.getVote()== VoteType.DOWN_VOTE) {
                    voteQuestionDao.deleteById(optionalVoteQuestion.get().getId());
                    voteQuestionDao.persist(voteQuestion);
                    reputationService.deleteReputation(question.getId(), user.getId());
                    reputationService.increaseReputationByQuestionVoteUp(question, user);

                }
                return voteQuestion;

            }
            throw new VoteException("Can't change vote");
        }

        VoteQuestion voteQuestion = new VoteQuestion(user, question, VoteType.UP_VOTE);
        voteQuestionDao.persist(voteQuestion);
        reputationService.increaseReputationByQuestionVoteUp(question, user);

        return voteQuestion;
    }




    @Override
    @Transactional
    public VoteQuestion voteDownQuestion(Question question, User user) {

        if (isUserAlreadyVoted(question, user)) {
            Optional<VoteQuestionDto> optionalVoteQuestion = voteQuestionDtoService.getVoteByQuestionIdAndUserId(question.getId(), user.getId());
            if (optionalVoteQuestion.isPresent()) {
                VoteQuestion voteQuestion = new VoteQuestion(user, question, VoteType.DOWN_VOTE);
                if (voteQuestion.getVote() == VoteType.UP_VOTE) {
                    voteQuestionDao.deleteById(optionalVoteQuestion.get().getId());
                    voteQuestionDao.persist(voteQuestion);
                    reputationService.deleteReputation(question.getId(), user.getId());
                    reputationService.decreaseReputationByQuestionVoteDown(question, user);
                }
                return voteQuestion;
            }
            throw new VoteException("Can't change vote");
        }

        VoteQuestion voteQuestion = new VoteQuestion(user, question, VoteType.DOWN_VOTE);
        voteQuestionDao.persist(voteQuestion);
        reputationService.decreaseReputationByQuestionVoteDown(question, user);

        return voteQuestion;
    }
}




