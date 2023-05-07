package com.hydroyura.prodms.archive.data.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBPartChangeTest {


    @Test
    void test1() {
        DBPartChange change = new DBPartChange();
        change.setFieldValue("MY_VALUE");

        assertEquals("MY_VALUE", change.getFieldValue());
    }

}