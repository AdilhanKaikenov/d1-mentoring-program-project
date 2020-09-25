package com.epam.mentoring.springmvc.repository;

import com.epam.mentoring.springmvc.entity.User;
import com.epam.mentoring.springmvc.vo.UserVO;
import com.epam.mentoring.springmvc.vo.authentication.UserDetailsVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Kaikenov Adilhan
**/
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds the security details for a username (returns only registered users)
     *
     * @param username
     *            The username of the registered user
     * @return the security details for a username (returns only registered users)
     */
    @Query(name = "User.findSecurityDetailsByUsername")
    UserDetailsVO findSecurityDetailsByUsername(@Param("username") String username);

    User findByUsername(String username);

    @Query(name = "User.findVoByUsername")
    UserVO findVoByUsername(@Param("username") String username);

    @Modifying
    @Query(name = "User.updatePhoto")
    void updatePhoto(@Param("username") String username, @Param("photo") String photo);

}
