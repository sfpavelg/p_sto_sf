package com.javamentor.qa.platform.dao.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserDtoDaoImpl implements UserDtoDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<UserDto> getUserDtoById(Long id) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery(
                "SELECT new com.javamentor.qa.platform.models.dto.user.UserDto (u.id, " +
                        "u.email, " +
                        "u.fullName, " +
                        "u.persistDateTime, " +
                        "u.isEnabled, " +
                        "u.isDeleted, " +
                        "u.city, " +
                        "u.linkSite, " +
                        "u.linkGitHub, " +
                        "u.linkVk, " +
                        "u.about, " +
                        "u.imageLink, " +
                        "u.lastUpdateDateTime, " +
                        "u.nickname, " +
                        "u.role.name) " +
                        "FROM User u JOIN u.role " +
                        "WHERE u.id = :id"
                , UserDto.class).setParameter("id", id));
    }
}