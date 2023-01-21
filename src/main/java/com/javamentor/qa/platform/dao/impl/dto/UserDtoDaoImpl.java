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

//        Query query = manager.createNativeQuery(
//        "SELECT id, email, full_name, image_link, city, " +
//        "(SELECT SUM(count) as reputation FROM reputation WHERE author_id = :id)" +
//        " FROM user_entity WHERE id = :id ;")
//                .setParameter("id", id);
        Query query = manager.createQuery("SELECT new " + UserDto.class.getName() +
                        " (u.id, u.email, u.fullName, u.imageLink, u.city) " +
                        " from User u where id = :id")
                .setParameter("id", id);
//                        " FROM User u, Reputation r WHERE id = :id")


        List<UserDto> list = query.getResultList();
//        UserDto name = new UserDto();
//        for(Object[] q1 : list){
//            name.setId((Long) ((BigInteger) q1[0]).longValue());
//            name.setEmail((String) q1[1]);
//            name.setFullName((String) q1[2]);
//            name.setImageLink((String) q1[3]);
//            name.setCity((String) q1[4]);
//            name.setReputation((int) q1[5]);
//        }
        System.out.println(list.get(0));

        return Optional.of(list.get(0));
    }
}


//manager.createNativeQuery(
//        "SELECT id, email, full_name, image_link, city, " +
//        "(SELECT SUM(count) as reputation FROM reputation WHERE author_id = 1)" +
//        " FROM user_entity WHERE id = 1;").getResultList()