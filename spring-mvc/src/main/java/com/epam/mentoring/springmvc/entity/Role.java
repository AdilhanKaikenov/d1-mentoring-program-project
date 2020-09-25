package com.epam.mentoring.springmvc.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Kaikenov Adilhan
**/
public enum Role {

    USER, ADMIN;

    private static final String ROLE_PREFIX = "ROLE_";

    /**
     * @return true if the current role is an admin role (is equal to {@code Role.ADMIN})
     */
    public boolean isAdminRole() {
        return Role.ADMIN.equals(this);
    }

    public String getRoleName() {
        return ROLE_PREFIX + this.name();
    }

    public static Role forRoleName(final String roleName) {
        final String roleWithoutPrefix = StringUtils.removeStartIgnoreCase(roleName, ROLE_PREFIX);
        for (final Role aRole : values()) {
            if (StringUtils.equalsIgnoreCase(roleWithoutPrefix, aRole.name())) {
                return aRole;
            }
        }
        return null;
    }
}
