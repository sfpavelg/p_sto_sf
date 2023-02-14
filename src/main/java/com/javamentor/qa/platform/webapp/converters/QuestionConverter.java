package com.javamentor.qa.platform.webapp.converters;

import com.javamentor.qa.platform.models.dto.question.QuestionCreateDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Mapper(componentModel = "spring")
public abstract class QuestionConverter {
    @Mapping(target = "user", source = "user", qualifiedByName = "setUser")
    public abstract Question questionCreateDtoToQuestion(QuestionCreateDto questionCreateDto, User user);

    @Named("setUser")
    public User setUser(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }


}
