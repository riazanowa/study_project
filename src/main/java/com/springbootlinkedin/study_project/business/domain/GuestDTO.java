package com.springbootlinkedin.study_project.business.domain;

import lombok.Data;

import javax.persistence.Column;
@Data
public class GuestDTO {
    private Long guestId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
