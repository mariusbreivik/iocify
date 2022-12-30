package com.example.service;

import no.breaks.iocify.annotation.Component;

@Component
public class SomeService {

    public int addNumbers(int one, int two){
        return one + two;
    }
}
