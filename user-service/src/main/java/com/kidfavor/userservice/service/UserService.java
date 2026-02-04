package com.kidfavor.userservice.service;

import com.kidfavor.userservice.dto.request.user.UserUpdateRequest;
import com.kidfavor.userservice.dto.response.UserResponse;
import com.kidfavor.userservice.entity.enums.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(int id);
    List<UserResponse> getUsersByStatus(Boolean status);
    List<UserResponse> getUsersByRole(Role role);
    UserResponse updateUser(int id, UserUpdateRequest request);
    void changeUserStatus(int id);
    UserResponse changeUserRole(int id, Role role);
    void deleteUser(int id);

}
