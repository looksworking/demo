package com.example.demo;


import org.junit.Test;

import java.util.UUID;

public class Tests {

    @Test
    public void test(){
        String test = "e52f407ae4b84b6697d6eb8abf2dee4f";
        System.out.println(UUID.nameUUIDFromBytes(test.getBytes()));


    }
}
