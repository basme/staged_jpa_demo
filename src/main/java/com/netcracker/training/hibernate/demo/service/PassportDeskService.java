package com.netcracker.training.hibernate.demo.service;

import org.springframework.stereotype.Service;

@Service
public class PassportDeskService {

    public String getPassportDeskNameByCode(int code) {
        return "The best desk ever";
    }

    public int getPassportDeskCodeByName(String name) {
        return 92;
    }

}
