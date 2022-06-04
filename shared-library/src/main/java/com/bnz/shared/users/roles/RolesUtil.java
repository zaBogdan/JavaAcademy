package com.bnz.shared.users.roles;

public class RolesUtil {
    public static Roles getFromString(String str) {
        str = str.toLowerCase();
        switch(str) {
            case "teacher":
                return Roles.TEACHER;
            case "student":
                return Roles.STUDENT;
            case "administrator":
            case "admin":
                return Roles.ADMINISTRATOR;
            default:
                return Roles.DEFAULT;
        }
    }

    /**
     * Check if the requested user has at leas
     * @param userRole
     * @param requiredRole
     * @return true if the user has minimum required role, false otherwise
     */
    public static boolean hasMinimumRequiredRole(int userRole,Roles requiredRole) {
        if((userRole & Roles.ADMINISTRATOR.getValue()) != 0) return true;

        return ((userRole & requiredRole.getValue()) != 0);
    }
}
