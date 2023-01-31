package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.UserDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserDaoImpl extends ReadWriteDaoImpl<User, Long> implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    public Optional<User> getByEmail(String email) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery(
                "SELECT u FROM User u JOIN FETCH u.role WHERE u.email = :email"
                , User.class).setParameter("email", email));
    }
}
