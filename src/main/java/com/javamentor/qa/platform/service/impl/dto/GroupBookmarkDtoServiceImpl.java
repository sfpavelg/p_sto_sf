package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.GroupBookmarkDtoDao;
import com.javamentor.qa.platform.models.dto.GroupBookmarkDto;
import com.javamentor.qa.platform.service.abstracts.dto.GroupBookmarkDtoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupBookmarkDtoServiceImpl implements GroupBookmarkDtoService {
    private final GroupBookmarkDtoDao groupBookmarkDtoDao;

    @Override
    public List<GroupBookmarkDto> getGroupBookmark(Long userId) {
        return groupBookmarkDtoDao.getGroupBookmark(userId);
    }

    public Optional<GroupBookmarkDto> getGroupBookmarkById(Long id) {
        return groupBookmarkDtoDao.getGroupBookmarkById(id);
    }
}
