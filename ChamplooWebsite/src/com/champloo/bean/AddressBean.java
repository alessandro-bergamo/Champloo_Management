package com.champloo.bean;

public class AddressBean
{
    public AddressBean(int civic_number, int CAP, int registred_User, String address, String city, String province) {
        this.civic_number = civic_number;
        this.CAP = CAP;
        this.Registred_User = registred_User;
        this.address = address;
        this.city = city;
        this.province = province;
    }

    public AddressBean() {

    }
    public int getId_address() { return id_address; }

    public void setId_address(int id_address) { this.id_address = id_address; }

    public int getCivic_number() { return civic_number; }

    public void setCivic_number(int civic_number) { this.civic_number = civic_number; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getProvince() { return province; }

    public void setProvince(String province) { this.province = province; }

    public int getCAP() { return CAP; }

    public void setCAP(int CAP) { this.CAP = CAP; }

    public int getRegistred_User() { return Registred_User; }

    public void setRegistred_User(int registred_User) { Registred_User = registred_User; }



    private int id_address, civic_number, CAP, Registred_User;
    private String address, city, province;

    public boolean isEmpty() {
        return id_address == 0 && civic_number == 0 && CAP == 0 && Registred_User == 0 && address == null && city == null && province == null;
    }

    public String toString() {
        return "id address: " + getId_address() + "\n" + "civic number: " + getCivic_number() + "\n" + "CAP: " + getCAP() + "\n" + "Registred user id" + getRegistred_User() + "\n" + "Address: " + getAddress() + "\n" + "City: " + getCity() + "\n" + "Province: " + getProvince() + "\n";
    }

}
