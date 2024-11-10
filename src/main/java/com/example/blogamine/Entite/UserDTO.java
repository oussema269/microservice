package com.example.blogamine.Entite;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String id;

    private String username;


    private String email;


    private String password;


    private Set<RoleDTO> roles = new HashSet<>();

    private String verificationCode;

    private String resetPasswordToken;
    private boolean enabled;
    private boolean active;
//
//    @DBRef
//    private List<Cour> courList =new ArrayList<>() ;

    private FaculteDTO faculte ;


}
