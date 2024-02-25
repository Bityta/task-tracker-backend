package ru.app.restapiservice.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRoleTest {

    @Test
    public void testConstructorAndGetters() {
        UserRole userRole = new UserRole();
        userRole.setId(1);
        userRole.setRole(RoleEnum.USER);
        User owner = new User();
        userRole.setOwner(owner);

        assertEquals(1, userRole.getId());
        assertEquals(RoleEnum.USER, userRole.getRole());
        assertEquals(owner, userRole.getOwner());
    }

    @Test
    public void testToString() {
        UserRole userRole = new UserRole();
        userRole.setId(1);
        userRole.setRole(RoleEnum.USER);
        User owner = new User();
        userRole.setOwner(owner);

        assertEquals("UserRole(id=1, role=USER, owner=" + owner + ")", userRole.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserRole userRole1 = new UserRole(1, RoleEnum.USER, new User());
        UserRole userRole2 = new UserRole(1, RoleEnum.USER, new User());

        assertEquals(userRole1, userRole2);
        assertEquals(userRole1.hashCode(), userRole2.hashCode());
    }

    @Test
    public void testBuilder() {
        UserRole userRole = UserRole.builder()
                .id(1)
                .role(RoleEnum.USER)
                .owner(new User())
                .build();

        assertEquals(1, userRole.getId());
        assertEquals(RoleEnum.USER, userRole.getRole());
    }
}
