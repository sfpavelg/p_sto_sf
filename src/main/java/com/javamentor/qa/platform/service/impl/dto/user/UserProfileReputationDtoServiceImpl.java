package com.javamentor.qa.platform.service.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.user.reputation.UserProfileReputationDto;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileReputationDtoService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserProfileReputationDtoServiceImpl extends PageDtoService<UserProfileReputationDto> implements UserProfileReputationDtoService {

    public UserProfileReputationDtoServiceImpl(Map<String, PageDtoDao<UserProfileReputationDto>> beansMap) {
        super(beansMap);
    }

    @Override
    public PageDto<UserProfileReputationDto> getAllReputationByCurrentPage(Map<String, Object> param) {
        param.put("daoDtoImpl", "userProfileReputationDtoDaoImpl");
        return pageDto(param);
    }
}
