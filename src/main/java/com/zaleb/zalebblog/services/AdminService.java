package com.zaleb.zalebblog.services;

import com.zaleb.zalebblog.dtos.RoleUpdateDto;
import com.zaleb.zalebblog.dtos.UserResponseDto;

public interface AdminService {
    UserResponseDto updateUserRole(Long userId, RoleUpdateDto role);
}
