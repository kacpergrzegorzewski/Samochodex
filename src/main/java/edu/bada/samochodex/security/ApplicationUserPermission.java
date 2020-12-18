package edu.bada.samochodex.security;

public enum ApplicationUserPermission {
    POCZTY_READ("poczty:read"),
    POCZTY_WRITE("poczty:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
