package com.infitry.laboratory.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TryCatchTest {

    @Test
    @DisplayName("Exception 과 Error 는 다르다.")
    public void tryCatchError() {
        Exception exception = new Exception("default");
        Error error = new Error("default error");
        try {
            throw new Error("error");
        } catch (Exception e) {
            exception = e;
        } catch (Error er) {
            error = er;
        }
        assertEquals("default", exception.getMessage());
        assertEquals("error", error.getMessage());
    }

    @Test
    @Disabled
    @DisplayName("Disabled test.")
    public void disabledTest() {
        System.out.println("TEST!!");
    }
}