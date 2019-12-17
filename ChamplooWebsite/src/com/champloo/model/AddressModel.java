package com.champloo.model;

import com.champloo.bean.AddressBean;

import java.util.HashSet;

public interface AddressModel
{

    boolean insertAddress(AddressBean newAddress);

    boolean deleteAddress(AddressBean address);

    AddressBean retrieveByID(int id_address);

    HashSet<AddressBean> retrieveByUsername(String username);

}
