package com.epam.mentoring.springmvc.service;

import com.epam.mentoring.springmvc.entity.User;
import com.epam.mentoring.springmvc.exception.PersistenceException;
import com.epam.mentoring.springmvc.exception.UsernameAlreadyExistException;
import com.epam.mentoring.springmvc.vo.UserVO;

/**
 * @author Kaikenov Adilhan
**/
public interface UserService {

    void signUp(User user) throws UsernameAlreadyExistException, PersistenceException;

    UserVO findVoByUsername(String username) throws PersistenceException;

    void updateProfilePhoto(String username, String photo) throws PersistenceException;

}
