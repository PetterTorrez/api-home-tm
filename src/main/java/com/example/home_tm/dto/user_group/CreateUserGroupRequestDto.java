package com.example.home_tm.dto.user_group;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CreateUserGroupRequestDto {

    @Size(
        min = 5,
        max = 100,
        message = "Name must be between 5 and 100 characters"
    )
    @NotBlank(message = "Name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
