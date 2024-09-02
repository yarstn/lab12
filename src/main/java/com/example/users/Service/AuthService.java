package com.example.users.Service;

import com.example.users.Api.ApiException;
import com.example.users.Model.User;
import com.example.users.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

//get
    public List<User> getUsers() {
        return authRepository.findAll();
    }
//add
    public void Register(User user) {
        user.setRole("USER");
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepository.save(user);
    }

    //update
    public void updateAuth(Integer id,User user) {
        User user1 = authRepository.findUserById(id);
        if (user1 == null){
            throw new ApiException("User not found");
        }
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user1.setPassword(hash);
        user1.setUsername(user.getUsername());
        authRepository.save(user1);
    }
    //delete
    public void deleteAuth(Integer id) {
        User user = authRepository.findUserById(id);
        if (user == null){
            throw new ApiException("User not found");
        }
        authRepository.deleteById(id);
    }
    }
