package com.example.userservice.enums;

import javax.management.relation.Role;

public enum RoleType {
    ADMIN(1L),
    USER(2L);
    final private Long value;

    RoleType(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return this.value;
    }

    public static RoleType fromOrdinal(Long ordinal) {
        for (RoleType role : RoleType.values()) {
            if (role.ordinal() == ordinal) {
                return role;
            }
        }
        throw new IllegalArgumentException("No RoleType found with ordinal: " + ordinal);
    }
}
