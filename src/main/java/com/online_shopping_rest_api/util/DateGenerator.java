package com.online_shopping_rest_api.util;

import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class DateGenerator {

    public Date getSQLDate(){

        long millis=System.currentTimeMillis();
        Date date=new java.sql.Date(millis);

        return date;
    }

}
