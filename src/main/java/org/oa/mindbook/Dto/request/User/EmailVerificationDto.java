package org.oa.mindbook.Dto.request.User;

import lombok.Getter;

@Getter
public class EmailVerificationDto {
    String email;
    String verification;
}