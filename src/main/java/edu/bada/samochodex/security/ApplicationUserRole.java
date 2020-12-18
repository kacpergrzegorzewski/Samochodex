package edu.bada.samochodex.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static edu.bada.samochodex.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(POCZTY_READ, POCZTY_WRITE)),
    MODERATOR(Sets.newHashSet()),
    USER(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
