package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDao;
import com.javamentor.qa.platform.models.dto.UserDto;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDtoDaoImpl implements UserDtoDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public UserDtoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        List<UserDto> list = entityManager.createQuery("""
                        SELECT new com.javamentor.qa.platform.models.dto.UserDto
                        (u.id, u.email, u.fullName, u.city, u.imageLink, sum(cast(r.count as int)))
                        from User u, Reputation r where u.id = :id and r.author.id = :id group by u.id
                        """)
                .setParameter("id", id)
                .getResultList();

        return Optional.of(list.get(0));
    }
}
