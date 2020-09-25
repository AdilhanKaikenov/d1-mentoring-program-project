package com.epam.mentoring.springmvc.security;

import com.epam.mentoring.springmvc.vo.UserVO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Objects;

/**
 * @author Kaikenov Adilhan
**/
@Component
@RequestScope
public class LoggedInUserHolder {

    private UserVO userVO;

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(final UserVO userVO) {
        this.userVO = userVO;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LoggedInUserHolder that = (LoggedInUserHolder) o;
        return Objects.equals(getUserVO(), that.getUserVO());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserVO());
    }

    @Override
    public String toString() {
        return "LoggedInUserHolder{" +
                "userVO=" + userVO +
                '}';
    }
}
