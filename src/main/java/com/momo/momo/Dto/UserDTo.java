package com.momo.momo.Dto;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTo {
    private Integer id;
    private String email;
    private String firstname;
    private String lastname;
    private Date dateNaissance;
    private Boolean enable;

}
