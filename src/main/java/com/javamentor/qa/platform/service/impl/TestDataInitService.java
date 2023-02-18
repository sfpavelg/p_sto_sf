package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.models.entity.question.IgnoredTag;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.question.TrackedTag;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.RoleService;
import com.javamentor.qa.platform.service.abstracts.model.IgnoredTagService;
import com.javamentor.qa.platform.service.abstracts.model.TrackedTagService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import com.javamentor.qa.platform.service.abstracts.model.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
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
    private final IgnoredTagService ignoredTagService;
    private final  TrackedTagService trackedTagService;

    @Autowired
    public TestDataInitService(RoleService roleService, UserService userService, QuestionService questionService, AnswerService answerService,
                               TagService tagService, IgnoredTagService ignoredTagService, TrackedTagService trackedTagService) {
        this.roleService = roleService;
        this.userService = userService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.tagService = tagService;
        this.ignoredTagService = ignoredTagService;
        this.trackedTagService = trackedTagService;
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
            user.setImageLink("https://img.com/" + i);
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
            user.setImageLink("https://img.com/" + i);
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
    }

    public void createIgnoredTags(int ignoredUser) {
        List<User> userList = userService.getAll();
        List<Tag> tagList = tagService.getAll();

        for (User user: userList) {
            if (user.getId() == ignoredUser) {
                continue;
            }

            List<Integer> uniqueTags = IntStream.range(0, tagList.size()).boxed().collect(Collectors.toList());
            Collections.shuffle(uniqueTags);

            for (int ignoredTagId = 0; ignoredTagId < rand(0, 4); ignoredTagId++) {
                IgnoredTag ignoredTag = new IgnoredTag();
                ignoredTag.setIgnoredTag(tagList.get(uniqueTags.get(ignoredTagId)));
                ignoredTag.setUser(user);
                ignoredTagService.persist(ignoredTag);
            }
        }
    }

    public void createTrackedTags(int ignoredUser) {
        List<User> userList = userService.getAll();
        List<Tag> tagList = tagService.getAll();

        for (User user: userList) {
            if (user.getId() == ignoredUser) {
                continue;
            }

            List<Integer> uniqueTags = IntStream.range(0, tagList.size()).boxed().collect(Collectors.toList());
            Collections.shuffle(uniqueTags);

            for (int trackedTagId = 0; trackedTagId < rand(0, 4); trackedTagId++) {
                TrackedTag trackedTag = new TrackedTag();
                trackedTag.setTrackedTag(tagList.get(uniqueTags.get(trackedTagId)));
                trackedTag.setUser(user);
                trackedTagService.persist(trackedTag);
            }
        }
    }

    public int rand(int leftLimit, int rightLimit) {
        return leftLimit + (int) (Math.random() * rightLimit);
    }

    public void init() {
        roleService.persist(ROLE_ADMIN);
        roleService.persist(ROLE_USER);
        createSuperUser(5);
        createUser(45);
        createTags();
        createQuestion(10);
        createAnswer(50);
        createIgnoredTags(1);
        createTrackedTags(1);
    }
}
