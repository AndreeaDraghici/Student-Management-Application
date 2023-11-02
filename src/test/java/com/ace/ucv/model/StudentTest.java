package com.ace.ucv.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Created by Andreea Draghici on 11/2/2023
 * Name of project: StudentManagement
 */
class StudentTest {

    // Returns true for a valid phone number in the format +4072xxxxxx
    @Test
    void test_validPhoneNumberFormat() {
        Student student = new Student(1);
        String validPhoneNumber = "+40721234567";
        Assertions.assertTrue(student.isValidPhoneNumber(validPhoneNumber));
    }

    // Returns false for an empty phone number
    @Test
    void test_emptyPhoneNumber_returnsFalse() {
        Student student = new Student(1);
        Assertions.assertFalse(student.isValidPhoneNumber(""));
    }

    // Returns false for a null phone number
    @Test
    void test_nullPhoneNumber_returnsFalse() {
        Student student = new Student(1);
        Assertions.assertFalse(student.isValidPhoneNumber(""));
    }

    // Returns false for a phone number with invalid format
    @Test
    void test_invalidPhoneNumberFormat() {
        // Arrange
        Student student = new Student(1);

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> student.setPhone("1234567890"));
    }

    // Returns false for a phone number with invalid characters
    @Test
    void test_invalidPhoneNumberCharacters() {
        Student student = new Student(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> student.setPhone("1234567890"));
    }

    // Returns false for a phone number with invalid prefix
    @Test
    void test_invalid_prefix() {
        Student student = new Student(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> student.setPhone("1234567890"));
    }

    // Returns false for a phone number with more than 13 digits
    @Test
    void test_phoneNumberWithMoreThan13Digits() {
        Student student = new Student(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> student.setPhone("1234567890123456"));
    }

    // Returns false for a phone number with less than 10 digits
    @Test
    void test_phoneNumberLessThan10Digits() {
        Student student = new Student(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> student.setPhone("123456789"));
    }
}