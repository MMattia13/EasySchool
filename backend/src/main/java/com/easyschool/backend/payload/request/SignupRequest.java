package com.easyschool.backend.payload.request;

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

  private String role_code;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
}
