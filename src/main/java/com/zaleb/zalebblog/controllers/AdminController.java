package com.zaleb.zalebblog.controllers;


import com.zaleb.zalebblog.dtos.RoleUpdateDto;
import com.zaleb.zalebblog.dtos.UserResponseDto;
import com.zaleb.zalebblog.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class AdminController {

    private final AdminService adminService;

    @PatchMapping("/users/{userId}/role")
    public UserResponseDto updateUserRole(@PathVariable Long userId, @RequestBody RoleUpdateDto role) {
        return adminService.updateUserRole(userId, role);
    }
}
