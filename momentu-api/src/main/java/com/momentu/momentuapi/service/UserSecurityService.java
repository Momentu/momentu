package com.momentu.momentuapi.service;

import com.momentu.momentuapi.entities.User;

public interface UserSecurityService {
    boolean validPasswordResetToken(String resetToken, String currentPassword);
    String hashToken(String resetToken);
}
