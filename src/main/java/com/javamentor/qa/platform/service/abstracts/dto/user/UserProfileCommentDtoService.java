package com.javamentor.qa.platform.service.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.user.UserProfileCommentDto;
import java.util.HashMap;


public interface UserProfileCommentDtoService {

     PageDto <UserProfileCommentDto> getUserProfileCommentDto(HashMap<String, Object> params) ;
}
