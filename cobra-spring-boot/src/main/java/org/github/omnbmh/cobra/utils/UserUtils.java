package org.github.omnbmh.cobra.utils;

import org.github.omnbmh.cobra.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
