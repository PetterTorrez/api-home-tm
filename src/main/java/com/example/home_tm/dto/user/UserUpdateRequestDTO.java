package com.example.home_tm.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDTO {
    @Pattern(regexp = ".*\\S.*", message = "Name cannot be only spaces")
    @Size(min = 5, max = 120, message = "Name must be between 5 and 120 characters")
    private String name;

    @Pattern(regexp = ".*\\S.*", message = "Email cannot be only spaces")
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = ".*\\S.*", message = "Password cannot be only spaces")
    @Size(min = 6, max = 60, message = "Password must be between 6 and 60 characters")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
}
