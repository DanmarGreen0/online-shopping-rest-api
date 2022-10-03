package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistrationInputValidation {

    public boolean usernameValidator(String username){
        //source: https://support.google.com/mail/answer/9211434?hl=en#:~:text=Usernames%20can%20contain%20 letters%20a%2Dz,in%20a%20 row.
        boolean isValid = false;
        int usernameMinLen =  6;
        int usernameMaxLen =  30;
        Pattern illegalSymbols = Pattern.compile("[&=_'-+,<>]");
        Pattern illegalSymbols1 = Pattern.compile("[.]{2,}");

        if(username.length() >= usernameMinLen &&
                username.length() <= usernameMaxLen){ //check length
            if (Character.compare(username.charAt(0),'.') != 0 && !username.endsWith(".")) //check for . at the beginning and end
                if(!illegalSymbols.matcher(username).find() &&
                        !illegalSymbols1.matcher(username).find()) //check for other illegal symbols
                    isValid = true;
        }

        return isValid;
    }

    public boolean firstNameAndLastNameValidator(String name){

        boolean isValid = false;
        int minNameLen = 0;
        int maxNameLen = 24;

        if(name.length() > minNameLen && name.length() <= maxNameLen)
            isValid = true;

        return isValid;
    }

    public boolean passwordValidator(String password){

        boolean isValid = false;
        int validPasswordMinLen = 8;
        int validPasswordMaxLen = 24;

        if(password.length() >= validPasswordMinLen &&
                password.length() <= validPasswordMaxLen)
            isValid = true;

        return isValid;
    }

    public boolean emailValidator(String email){
        /*link: https://help.xmatters.com/ondemand/trial/valid_email_format.htm#:~:text=A%20valid%20email%20address%20consists,com%22%20is%20the%20email%20domain.*/
        boolean isValid = false;
        String[] splitEmail1 = email.split("@");

        Pattern illegalEmailDomain = Pattern.compile("(^[a-zA-Z0-9]{2,})([.][a-zA-Z]{2,}){1,2}$");
        Pattern illegalEmailPrefixRegex = Pattern.compile("(([a-z-A-Z0-9]+[._-]{2,}[a-z-A-Z0-9]+)" +
                "|([a-z-A-Z0-9]*[._-]+$)|" +
                "(^[._-]+[a-z-A-Z0-9]*))|" +
                "(([\\\\!#$%&'*+/=?^`{}|\\\"(),:;<>@\\[\\]]+[a-zA-Z0-9]*)|" +
                "([a-zA-Z0-9]*[\\\\!#$%&'*+/=?^`{}|\\\"(),:;<>@\\[\\]]+)|" +
                "([a-zA-Z0-9]*[\\\\!#$%&'*+/=?^`{}|\\\"(),:;<>@\\[\\]]+[a-zA-Z0-9]*)|" +
                "([\\\\!#$%&'*+/=?^`{}|\\\"(),:;<>@\\[\\]]+[a-zA-Z0-9]*[\\\\!#$%&'*+/=?^`{}|\\\"(),:;<>@\\[\\]]+))");


        if(splitEmail1.length == 2){

            String emailPrefix = splitEmail1[0];
            String emailDomain = splitEmail1[1];

            Matcher isMatcherEP = illegalEmailPrefixRegex.matcher(emailPrefix);
            Matcher isMatcherD = illegalEmailDomain.matcher(emailDomain);
            if(!isMatcherEP.find() && isMatcherD.find()) {

                isValid = true;

            }
        }

        return isValid;
    }
}
