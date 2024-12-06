package com.parking.parkinlot3.ejb;

import com.parking.parkinlot3.common.UserDto;
import com.parking.parkinlot3.entites.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> findAllUsers(){
        LOG.info("findAllUsers");
        try {
            TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }


    private List<UserDto> copyUsersToDto(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(
                        user.getUsername(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }
}