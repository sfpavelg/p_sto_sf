package com.javamentor.qa.platform.service.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserDtoService {
    Optional<UserDto> getById(Long id);
}
