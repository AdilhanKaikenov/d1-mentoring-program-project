package com.epam.mentoring.springmvc.vo.authentication;

import com.epam.mentoring.springmvc.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Kaikenov Adilhan
 **/
public class UserDetailsVO extends User implements Serializable {

    private Long userId;

    public UserDetailsVO(final Long userId, final String username, final String password, final Role role) {
        this(userId, username, password, true, true, true, true, Arrays.asList(new SimpleGrantedAuthority(role.getRoleName())));
    }

    public UserDetailsVO(final Long userId, final String username, final String password, final Collection<? extends GrantedAuthority> authorities) {
        this(userId, username, password, true, true, true, true, authorities);
    }

    public UserDetailsVO(final Long userId, final String username, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired,
            final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }
}
