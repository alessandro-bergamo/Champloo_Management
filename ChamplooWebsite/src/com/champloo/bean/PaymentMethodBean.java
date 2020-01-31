package com.champloo.bean;

import java.time.LocalDate;

public class PaymentMethodBean
{

    public PaymentMethodBean(int card_cvc, String card_number, String card_bank, String card_owner, int Registred_User, LocalDate expiry_date, LocalDate registration_method_date)
    {
        this.card_cvc = card_cvc;
        this.card_number = card_number;
        this.card_bank = card_bank;
        this.card_owner = card_owner;
        this.Registred_User = Registred_User;
        this.expiry_date = expiry_date;
        this.registration_method_date = registration_method_date;
    }

    public PaymentMethodBean() { /*EMPTY*/ }

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

    public LocalDate getExpiry_date() { return expiry_date; }

    public void setExpiry_date(LocalDate expiry_date) { this.expiry_date = expiry_date; }

    public LocalDate getRegistration_method_date() { return registration_method_date; }

    public void setRegistration_method_date(LocalDate registration_method_date) { this.registration_method_date = registration_method_date; }

    public int getRegistred_User() { return Registred_User; }

    public void setRegistred_User(int Registred_User) { this.Registred_User = Registred_User; }

    public boolean isEmpty()
    {
        return id_method == 0 && card_cvc == 0 && card_number == null && card_bank == null && card_owner == null && Registred_User == 0;
    }

    private int id_method, Registred_User, card_cvc;
    private String card_number, card_bank, card_owner;
    private LocalDate expiry_date, registration_method_date;

}
