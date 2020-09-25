package com.epam.mentoring.springmvc.service.impl;

import com.epam.mentoring.springmvc.entity.User;
import com.epam.mentoring.springmvc.exception.PersistenceException;
import com.epam.mentoring.springmvc.exception.UsernameAlreadyExistException;
import com.epam.mentoring.springmvc.repository.UserRepository;
import com.epam.mentoring.springmvc.service.UserService;
import com.epam.mentoring.springmvc.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author Kaikenov Adilhan
**/
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = PersistenceException.class)
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void signUp(User user) throws UsernameAlreadyExistException, PersistenceException {
        Assert.notNull(user, "User must not be null!");

        final User exist = userRepository.findByUsername(user.getUsername());
        if (exist != null) {
            log.error("Username {} already exist", exist.getUsername());
            throw new UsernameAlreadyExistException();
        }

        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PersistenceException("Failed to save user.", e);
        }
    }

    @Override
    public UserVO findVoByUsername(final String username) throws PersistenceException {
        try {
            return userRepository.findVoByUsername(username);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PersistenceException(e);
        }
    }

    @Override
    public void updateProfilePhoto(String username, String photo) throws PersistenceException {
        try {
            userRepository.updatePhoto(username, photo);
        } catch (Exception e) {
            log.error("Failed to update user = {}, photo = {}.", username, photo, e);
            throw new PersistenceException("Failed to update user photo.", e);
        }
    }
}
