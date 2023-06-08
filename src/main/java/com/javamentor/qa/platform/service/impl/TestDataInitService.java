package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.models.entity.BookMarks;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.CommentType;
import com.javamentor.qa.platform.models.entity.chat.*;
import com.javamentor.qa.platform.models.entity.GroupBookmark;
import com.javamentor.qa.platform.models.entity.question.*;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;
import com.javamentor.qa.platform.service.abstracts.model.*;
import com.javamentor.qa.platform.service.abstracts.model.tag.IgnoredTagService;
import com.javamentor.qa.platform.service.abstracts.model.tag.RelatedTagService;
import com.javamentor.qa.platform.service.abstracts.model.tag.TagService;
import com.javamentor.qa.platform.service.abstracts.model.tag.TrackedTagService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TestDataInitService {
    public static Role ROLE_ADMIN = new Role("ROLE_ADMIN");
    public static Role ROLE_USER = new Role("ROLE_USER");
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    private final RoleService roleService;
    private final UserService userService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final TagService tagService;
    private final RelatedTagService relatedTagService;
    private final IgnoredTagService ignoredTagService;
    private final TrackedTagService trackedTagService;
    private final QuestionViewedService questionViewedService;
    private final VoteQuestionService voteQuestionService;
    private final VoteAnswerService voteAnswerService;
    private final CommentQuestionService commentQuestionService;
    private final CommentAnswerService commentAnswerService;
    private final ReputationService reputationService;
    private final GroupChatService groupChatService;
    private final SingleChatService singleChatService;
    private final MessageService messageService;
    private final BookmarkService bookMarkService;
    private final GroupBookmarkService groupBookmarkService;

    @Autowired
    public TestDataInitService(RoleService roleService, UserService userService, QuestionService questionService,
                               AnswerService answerService, TagService tagService, RelatedTagService relatedTagService, IgnoredTagService ignoredTagService,
                               TrackedTagService trackedTagService, QuestionViewedService questionViewedService,
                               VoteQuestionService voteQuestionService, VoteAnswerService voteAnswerService,
                               CommentQuestionService commentQuestionService, CommentAnswerService commentAnswerService,
                               ReputationService reputationService, BookmarkService bookMarkService, GroupChatService groupChatService, SingleChatService singleChatService, MessageService messageService, GroupBookmarkService groupBookmarkService) {
        this.roleService = roleService;
        this.userService = userService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.tagService = tagService;
        this.relatedTagService = relatedTagService;
        this.ignoredTagService = ignoredTagService;
        this.trackedTagService = trackedTagService;
        this.questionViewedService = questionViewedService;
        this.voteQuestionService = voteQuestionService;
        this.voteAnswerService = voteAnswerService;
        this.commentQuestionService = commentQuestionService;
        this.commentAnswerService = commentAnswerService;
        this.reputationService = reputationService;
        this.bookMarkService = bookMarkService;
        this.groupBookmarkService = groupBookmarkService;
        this.groupChatService = groupChatService;
        this.singleChatService = singleChatService;
        this.messageService = messageService;
    }

    public void createSuperUser(int count) {
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setEmail("super" + i + "@gmail.com");
            user.setPassword(passwordEncoder.encode(i + "pwd"));
            user.setFullName("superfullname" + i);
            user.setIsEnabled(true);
            user.setIsDeleted(false);
            user.setCity("city" + i);
            user.setLinkSite(i + ".com");
            user.setLinkGitHub("https://github.com/" + i);
            user.setLinkVk("https://vk.com/" + i);
            user.setAbout("about" + i);
            user.setImageLink("https://cdn-icons-png.flaticon.com/512/1144/1144709.png");
            user.setNickname("supernickname" + i);
            user.setRole(ROLE_ADMIN);
            userService.persist(user);
        }
    }

    public void createUser(int count) {
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setEmail(i + "@gmail.com");
            user.setPassword(passwordEncoder.encode(i + "pwd"));
            user.setFullName("fullname" + i);
            user.setIsEnabled(true);
            user.setIsDeleted(false);
            user.setCity("city" + i);
            user.setLinkSite(i + ".com");
            user.setLinkGitHub("https://github.com/" + i);
            user.setLinkVk("https://vk.com/" + i);
            user.setAbout("about" + i);
            user.setImageLink("https://cdn-icons-png.flaticon.com/512/1144/1144760.png");
            user.setNickname("nickname" + i);
            user.setRole(ROLE_USER);
            userService.persist(user);
        }
    }

    public void createQuestion(int count) {
        for (int i = 0; i < count; i++) {
            Question question = new Question();
            question.setUser(userService.getAll().get(rand(0, 49)));
            question.setTitle(question.getUser().getNickname() + "'s question");
            question.setDescription("Asked by " + question.getUser().getFullName());
            List<Tag> tagsForQ = tagService.getAll();
            Collections.shuffle(tagsForQ);
            question.setTags(tagsForQ.subList(0, rand(1, 4)));
            questionService.persist(question);
        }
    }

    public void createAnswer(int count) {
        for (int i = 0; i < count; i++) {
            Answer answer = new Answer();
            List<Question> questionsForA = questionService.getAll();
            Collections.shuffle(questionsForA);
            answer.setQuestion(questionsForA.get(0));
            answer.setUser(userService.getAll().get(rand(0, 49)));
            answer.setHtmlBody("Answer to question about " + questionsForA.get(0).getTitle());
            answer.setIsHelpful(false);
            answer.setIsDeleted(false);
            answer.setIsDeletedByModerator(false);
            answerService.persist(answer);
        }
    }

    public void createTags() {
        Tag javaTag = new Tag();
        javaTag.setName("Java");
        javaTag.setDescription("Java — строго типизированный объектно-ориентированный язык программирования общего назначения");
        tagService.persist(javaTag);
        Tag jsTag = new Tag();
        jsTag.setName("JavaScript");
        jsTag.setDescription("JavaScript — мультипарадигменный язык программирования. Поддерживает объектно-ориентированный, императивный и функциональный стили");
        tagService.persist(jsTag);
        Tag csharpTag = new Tag();
        csharpTag.setName("C#");
        csharpTag.setDescription("C# — объектно-ориентированный язык программирования общего назначения");
        tagService.persist(csharpTag);
        Tag htmlTag = new Tag();
        htmlTag.setName("HTML");
        htmlTag.setDescription("HTML — стандартизированный язык гипертекстовой разметки документов для просмотра веб-страниц в браузере");
        tagService.persist(htmlTag);
        for (int i = 0; i < 30; i++) {
            Tag tag = new Tag();
            tag.setName("tag" + i);
            tag.setDescription("Tag" + i + " - is a test tag for dev stage of our application and must be removed on prod stage");
            tagService.persist(tag);
        }
    }

    public void createTagRelations() {
        Long id = 0L;
        for (int i = 5; i < 35; i++) {
            id = (long) i;
            RelatedTag relatedTag = new RelatedTag();
            relatedTag.setChildTag(tagService.getById(id).orElse(new Tag()));
            if (i <= 10) {
                id = 1L;
            } else if (i > 10 && i <= 20) {
                id = 2L;
            } else if (i > 20 && i <= 25) {
                id = 3L;
            }
            else {
                id = 4L;
            }
            relatedTag.setMainTag(tagService.getById(id).orElse(new Tag()));
            relatedTagService.persist(relatedTag);
        }
    }

    public void createIgnoredAndTrackedTags(int ignoredUser) {
        List<User> userList = userService.getAll();
        List<Tag> tagList = tagService.getAll();

        for (User user : userList) {
            if (user.getId() == ignoredUser) {
                continue;
            }
            Set<Integer> uniqueIgnoredTags = IntStream
                    .range(0, tagList.size()).boxed()
                    .sorted((o1, o2) -> Integer.compare(o1, o2))
                    .limit(rand(0, 4)).collect(Collectors.toSet());

//            Set<Integer> uniqueIgnoredTags = IntStream
//                    .range(0, tagList.size()).boxed()
//                    .sorted((o1, o2) -> o1.equals(o2) ? 0 : (ThreadLocalRandom.current().nextBoolean() ? -1 : 1))
//                    .limit(rand(0, 4)).collect(Collectors.toSet());
            Set<Integer> uniqueTrackedTags = IntStream
                    .range(0, tagList.size()).boxed()
                    .sorted((o1, o2) -> Integer.compare(o1, o2))
//                    .sorted((o1, o2) -> o1.equals(o2) ? 0 : (ThreadLocalRandom.current().nextBoolean() ? -1 : 1))
                    .limit(rand(0, 4)).collect(Collectors.toSet());
            uniqueIgnoredTags.removeAll(uniqueTrackedTags);
            uniqueTrackedTags.removeAll(uniqueIgnoredTags);

            for (Integer uniqueIgnoredTag : uniqueIgnoredTags) {
                IgnoredTag ignoredTag = new IgnoredTag();
                ignoredTag.setIgnoredTag(tagList.get(uniqueIgnoredTag));
                ignoredTag.setUser(user);
                ignoredTagService.persist(ignoredTag);
            }

            for (Integer uniqueTrackedTag : uniqueTrackedTags) {
                TrackedTag trackedTag = new TrackedTag();
                trackedTag.setTrackedTag(tagList.get(uniqueTrackedTag));
                trackedTag.setUser(user);
                trackedTagService.persist(trackedTag);
            }
        }
    }

    public int rand(int leftLimit, int rightLimit) {
        return leftLimit + (int) (Math.random() * rightLimit);
    }

    public void createQuestionViewed(int count) {
        List<Question> questionList = questionService.getAll();
        List<User> userList = userService.getAll();
        for (int i = 0; i < count; i++) {
            QuestionViewed questionViewed = new QuestionViewed();
            Collections.shuffle(questionList);
            questionViewed.setQuestion(questionList.get(0));
            Collections.shuffle(userList);
            questionViewed.setUser(userList.get(0));
            questionViewedService.persist(questionViewed);
        }
    }

    public void createVoteQuestion(int count) {
        List<Question> questionList = questionService.getAll();
        List<User> userList = userService.getAll();
        for (int i = 0; i < count; i++) {
            VoteQuestion voteQuestion = new VoteQuestion();
            Collections.shuffle(userList);
            voteQuestion.setUser(userList.get(0));
            Collections.shuffle(questionList);
            voteQuestion.setQuestion(questionList.get(0));
            if (rand(0, 2) == 0) {
                voteQuestion.setVote(VoteType.DOWN_VOTE);
            } else {
                voteQuestion.setVote(VoteType.UP_VOTE);
            }
            voteQuestionService.persist(voteQuestion);
        }
    }

    public void createVoteAnswer(int count) {
        List<Answer> answerList = answerService.getAll();
        List<User> userList = userService.getAll();
        for (int i = 0; i < count; i++) {
            VoteAnswer voteAnswer = new VoteAnswer();
            Collections.shuffle(userList);
            voteAnswer.setUser(userList.get(0));
            Collections.shuffle(answerList);
            voteAnswer.setAnswer(answerList.get(0));
            if (rand(0, 2) == 0) {
                voteAnswer.setVote(VoteType.DOWN_VOTE);
            } else {
                voteAnswer.setVote(VoteType.UP_VOTE);
            }
            voteAnswerService.persist(voteAnswer);
        }
    }

    public void createCommentQuestion(int count) {
        List<Question> questionList = questionService.getAll();
        List<User> userList = userService.getAll();
        for (int i = 0; i < count; i++) {
            Comment comment = new Comment(CommentType.QUESTION);
            Collections.shuffle(userList);
            comment.setText("Comment on the question nickname \"" + userList.get(0).getNickname() + "\"");
            comment.setUser(userList.get(0));

            CommentQuestion commentQuestion = new CommentQuestion();
            commentQuestion.setComment(comment);
            Collections.shuffle(questionList);
            commentQuestion.setQuestion(questionList.get(0));

            commentQuestionService.persist(commentQuestion);
        }
    }

    public void createCommentAnswer(int count) {
        List<Answer> answerList = answerService.getAll();
        List<User> userList = userService.getAll();
        for (int i = 0; i < count; i++) {
            Comment comment = new Comment(CommentType.ANSWER);
            Collections.shuffle(userList);
            comment.setText("Comment on the answer nickname \"" + userList.get(0).getNickname() + "\"");
            comment.setUser(userList.get(0));

            CommentAnswer commentAnswer = new CommentAnswer();
            commentAnswer.setComment(comment);
            Collections.shuffle(answerList);
            commentAnswer.setAnswer(answerList.get(0));

            commentAnswerService.persist(commentAnswer);
        }
    }

    public void createReputation() {
        List<Question> questionList = questionService.getAll();
        for (Question question : questionList) {
            Reputation reputation = new Reputation();
            reputation.setPersistDate(question.getPersistDateTime());
            reputation.setAuthor(question.getUser());
            reputation.setSender(question.getUser());
            reputation.setCount(1);
            reputation.setType(ReputationType.Question);
            reputation.setQuestion(question);
            reputation.setAnswer(null);
            reputationService.persist(reputation);
        }
        List<VoteQuestion> voteQuestionList = voteQuestionService.getAll();
        for (VoteQuestion voteQuestion : voteQuestionList) {
            Reputation reputation = new Reputation();
            reputation.setPersistDate(voteQuestion.getLocalDateTime());
            reputation.setAuthor(voteQuestion.getUser());
            reputation.setSender(voteQuestion.getUser());
            reputation.setCount(1);
            reputation.setType(ReputationType.VoteQuestion);
            reputation.setQuestion(voteQuestion.getQuestion());
            reputation.setAnswer(null);
            reputationService.persist(reputation);
        }
        List<Answer> answerList = answerService.getAll();
        for (Answer answer : answerList) {
            Reputation reputation = new Reputation();
            reputation.setPersistDate(answer.getPersistDateTime());
            reputation.setAuthor(answer.getUser());
            reputation.setSender(answer.getUser());
            reputation.setCount(1);
            reputation.setType(ReputationType.Answer);
            reputation.setQuestion(null);
            reputation.setAnswer(answer);
            reputationService.persist(reputation);
        }
        List<VoteAnswer> voteAnswerList = voteAnswerService.getAll();
        for (VoteAnswer voteAnswer : voteAnswerList) {
            Reputation reputation = new Reputation();
            reputation.setPersistDate(voteAnswer.getPersistDateTime());
            reputation.setAuthor(voteAnswer.getUser());
            reputation.setSender(voteAnswer.getUser());
            reputation.setCount(1);
            reputation.setType(ReputationType.VoteAnswer);
            reputation.setQuestion(null);
            reputation.setAnswer(voteAnswer.getAnswer());
            reputationService.persist(reputation);
        }
    }

    public void createBookMarks(int count) {

        for (int i = 0; i < count; i++) {
            BookMarks bookMarks = new BookMarks();
            bookMarks.setUser(userService.getAll().get(i));
            bookMarks.setQuestion(questionService.getAll().get(i));
            bookMarkService.persist(bookMarks);
        }
    }

    public void createGroupBookmarks(int count) {
        List<User> userList = userService.getAll();
        for (int i = 0; i < count; i++) {
            GroupBookmark groupBookmark = new GroupBookmark();
            groupBookmark.setTitle("title number " + i);
            groupBookmark.setUser(userList.get(i));
            groupBookmark.setBookMarks(null);
            groupBookmarkService.persist(groupBookmark);
        }
    }

    public void createSingleChat(int count) {
        List<User> userList = userService.getAll();
        for (int i = 0; i < count; i++) {
            Chat chat = new Chat(ChatType.SINGLE);
            chat.setTitle("SingleChat" + i);
            SingleChat singleChat = new SingleChat();
            singleChat.setChat(chat);
            singleChat.setUserOne(userList.get(i));
            singleChat.setUseTwo(userList.get(rand(1, userList.size() - 1)));
            if (singleChat.getUserOne() == singleChat.getUseTwo()) {
                singleChat.setUseTwo(userList.get(rand(1, userList.size() - 1)));
            }
            singleChatService.persist(singleChat);
        }
    }

    public void createGroupChat() {
        Set<User> userSetJava = new HashSet<>(userService.getAll());
        Chat chatJava = new Chat(ChatType.GROUP);
        chatJava.setTitle("Java");
        GroupChat groupChatJava = new GroupChat();
        groupChatJava.setChat(chatJava);
        groupChatJava.setUsers(userSetJava);
        groupChatService.persist(groupChatJava);
    }

    public void createMessage() {
        List<SingleChat> chatList = singleChatService.getAll();
        for (int i = 0; i < 7; i++) {
            Message message = new Message();
            message.setChat(chatList.get(rand(0, chatList.size() - 1)).getChat());
            message.setMessage("Hello " + i);
            message.setUserSender(chatList.get(i).getUserOne());
            messageService.persist(message);
        }
        for (int i = 0; i < 10; i++) {
            Message message1 = new Message();
            message1.setChat(groupChatService.getAll().get(0).getChat());
            message1.setUserSender(userService.getAll().get(rand(1, userService.getAll().size() - 1)));
            message1.setMessage("Group Java Hello " + message1.getUserSender().getNickname());
            messageService.persist(message1);
        }
    }


    public void init() {
        roleService.persist(ROLE_ADMIN);
        roleService.persist(ROLE_USER);
        createSuperUser(5);
        createUser(45);
        createTags();
        createTagRelations();
        createQuestion(10);
        createAnswer(50);
        createIgnoredAndTrackedTags(1);
        createQuestionViewed(50);
        createVoteQuestion(25);
        createVoteAnswer(25);
        createCommentQuestion(20);
        createCommentAnswer(20);
        createReputation();
        createBookMarks(10);
        createGroupBookmarks(5);
        createGroupChat();
        createSingleChat(20);
        createMessage();
    }
}
