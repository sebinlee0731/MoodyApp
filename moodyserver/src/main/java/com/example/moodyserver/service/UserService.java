package com.example.moodyserver.service;

import com.example.moodyserver.dto.SignupRequestDTO;
import com.example.moodyserver.entity.User;
import com.example.moodyserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User signup(SignupRequestDTO dto) {
        User user = User.builder()
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .address(dto.getAddress())
                .build();
        return userRepo.save(user);
    }

    public User login(String userName, String password) {
        return userRepo.findByUserName(userName)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findByUserName(String userName) {
        return userRepo.findByUserName(userName).orElse(null);
    }

    public Optional<User> findByUserNameOpt(String userName) {
        return userRepo.findByUserName(userName);
    }

    /** 비밀번호·주소만 업데이트 */
    @Transactional
    public User updateInfo(String userName, String newPassword, String newAddress) {
        User u = findByUserNameOpt(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        u.setPassword(newPassword);
        u.setAddress(newAddress);
        return userRepo.save(u);
    }

}