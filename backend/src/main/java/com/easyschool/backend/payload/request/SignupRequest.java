package com.easyschool.backend.payload.request;

import java.util.Set;

import com.easyschool.backend.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String name;

  @NotBlank
  @Size(min = 3, max = 20)
  private String surname;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<Role> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
}
