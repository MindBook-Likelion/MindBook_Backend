package org.oa.mindbook.Domain.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    WITHDRAWAL(0),
    ACTIVE(1),
    PAUSED(2),
    ;

    int status;
}