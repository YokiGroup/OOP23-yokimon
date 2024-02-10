package io.github.yokigroup.battle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.*;
class YokimonImplTest {


    @Test
    void YokimonImpl(){
        YokimonImpl sasso = null;
        assertThrows(NullPointerException.class, () -> new YokimonImpl(sasso));

    }
    @Test
    void getAllStats() {
    }

    @Test
    void getAttacks() {
    }

    @Test
    void setExpNext() {
    }
}