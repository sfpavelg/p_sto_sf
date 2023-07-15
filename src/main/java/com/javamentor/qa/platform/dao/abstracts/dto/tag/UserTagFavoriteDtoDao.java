package com.javamentor.qa.platform.dao.abstracts.dto.tag;

import com.javamentor.qa.platform.models.dto.tag.UserTagFavoriteDto;
import java.util.List;

public interface UserTagFavoriteDtoDao {
    List<UserTagFavoriteDto> getByUserId(Long id);
}
