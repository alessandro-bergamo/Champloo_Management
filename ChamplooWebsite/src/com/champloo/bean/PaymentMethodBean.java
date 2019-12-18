package com.champloo.bean;

import java.util.Date;

public class PaymentMethodBean
{

    public int getId_method() { return id_method; }

    public void setId_method(int id_method) { this.id_method = id_method; }

    public int getCard_cvc() { return card_cvc; }

    public void setCard_cvc(int card_cvc) { this.card_cvc = card_cvc; }

    public String getCard_number() { return card_number; }

    public void setCard_number(String card_number) { this.card_number = card_number; }

    public String getCard_bank() { return card_bank; }

    public void setCard_bank(String card_bank) { this.card_bank = card_bank; }

    public String getCard_owner() { return card_owner; }

    public void setCard_owner(String card_owner) { this.card_owner = card_owner; }

    public Date getExpiry_date() { return expiry_date; }

    public void setExpiry_date(Date expiry_date) { this.expiry_date = expiry_date; }

    public Date getRegistration_method_date() { return registration_method_date; }

    public void setRegistration_method_date(Date registration_method_date) { this.registration_method_date = registration_method_date; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }



    private int id_method, card_cvc;
    private String card_number, card_bank, card_owner, username;
    private Date expiry_date, registration_method_date;

}
