package com.champloo.bean;

public class AddressBean
{

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

    public String getCAP() { return CAP; }

    public void setCAP(String CAP) { this.CAP = CAP; }

    public UserBean getUser() { return user; }

    public void setUser(UserBean user) { this.user = user; }



    private int id_address, civic_number;
    private String address, city, province, CAP;
    private UserBean user;

}
