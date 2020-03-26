package com.bny.esg.controllers;

public class NotFoundException extends Exception {

    public NotFoundException(String id) {
        super(id);
    }
}
