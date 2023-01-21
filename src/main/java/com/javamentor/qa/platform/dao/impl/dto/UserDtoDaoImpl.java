package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDao;
import com.javamentor.qa.platform.models.dto.UserDto;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDtoDaoImpl implements UserDtoDao {

    @PersistenceContext
    private final EntityManager manager;

    public UserDtoDaoImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {


        Query query = manager.createQuery("SELECT new " + UserDto.class.getName() +
                        " (u.id, u.email, u.fullName, u.imageLink, u.city, sum(r.count)) " +
                        " from User u, Reputation r where u.id = :id and r.author.id = :id group by u.id")
                .setParameter("id", id);


        List<UserDto> list = query.getResultList();
        return Optional.of(list.get(0));
    }
}
