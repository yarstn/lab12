package com.example.users.Controller;

import com.example.users.Model.User;
import com.example.users.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController //json
public class AuthController {
    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(authService.getUsers());
    }
@PostMapping("/add")
    public ResponseEntity register(@Valid @RequestBody User user) {
        authService.Register(user);
        return ResponseEntity.status(200).body("user registered successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id,@Valid @RequestBody User user) {
        authService.updateAuth(id,user);
        return ResponseEntity.status(200).body("user updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        authService.deleteAuth(id);
        return ResponseEntity.status(200).body("user deleted successfully");
    }
}
