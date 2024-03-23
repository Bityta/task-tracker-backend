package ru.app.restapiservice.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RoleEnumTest {

    @Test
    public void testEnumValues() {
        RoleEnum[] expectedValues = {RoleEnum.USER, RoleEnum.ADMIN};
        RoleEnum[] actualValues = RoleEnum.values();
        assertArrayEquals(expectedValues, actualValues);
    }

}