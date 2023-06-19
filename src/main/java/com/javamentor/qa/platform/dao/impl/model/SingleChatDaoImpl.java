package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.SingleChatDao;
import com.javamentor.qa.platform.models.entity.chat.SingleChat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SingleChatDaoImpl extends ReadWriteDaoImpl<SingleChat, Long> implements SingleChatDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SingleChat> getSingleChatByUserId(Long Id, String value) {
        Query query = entityManager.createQuery("SELECT sc FROM SingleChat sc JOIN FETCH sc.userOne AS scu1 " +
                "JOIN FETCH sc.useTwo AS scu2 JOIN FETCH sc.chat JOIN FETCH scu2.role JOIN FETCH scu1.role " +
                        "WHERE scu1.id = :userId AND scu2.nickname LIKE :searchValue " +
                        "OR scu2.id = :userId AND scu1.nickname LIKE :searchValue")
                .setParameter("userId", Id)
                .setParameter("searchValue", "%" + value + "%");
        return (List<SingleChat>) query.getResultList();
    }
}
