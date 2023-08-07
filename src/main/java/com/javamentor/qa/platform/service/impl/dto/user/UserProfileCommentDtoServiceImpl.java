package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.user.UserProfileCommentDto;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileCommentDtoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserProfileCommentDtoServiceImpl extends PageDtoService<UserProfileCommentDto> implements UserProfileCommentDtoService {

    public UserProfileCommentDtoServiceImpl(Map<String, PageDtoDao<UserProfileCommentDto>> beansMap) {
        super(beansMap);
    }
    @Override
    public PageDto<UserProfileCommentDto> getUserProfileCommentDto(HashMap<String, Object> params) {
        params.put("daoDtoImpl", "userProfileCommentDtoDaoImpl");
        return pageDto(params);
    }
}
