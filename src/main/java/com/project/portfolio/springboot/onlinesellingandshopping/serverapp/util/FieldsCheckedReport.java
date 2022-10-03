package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.util;

public class FieldsCheckedReport {

      public String status;
      public String invalidFieldsMessage;

    public FieldsCheckedReport(String status, String invalidFieldsMessage) {
        this.status = status;
        this.invalidFieldsMessage = invalidFieldsMessage;
    }

    public String getStatus() {
        return status;
    }

    public String getInvalidFieldsMessage() {
        return invalidFieldsMessage;
    }
}
