package com.champloo.model;

import com.champloo.bean.AddressBean;

import java.util.HashSet;

public class AddressDAO implements AddressModel
{

    /**
     * Insert a new Shipping Address
     * @param newAddress
     * @return address_added
     */
    @Override
    public boolean insertAddress(AddressBean newAddress)
    {
        return false;
    }


    /**
     * Deletes a Shipping Address
     * @param address
     * @return address_deleted
     */
    @Override
    public boolean deleteAddress(AddressBean address)
    {
        return false;
    }


    /**
     * Retrieve a Shipping Address by his ID
     * @param id_address
     * @return address
     */
    @Override
    public AddressBean retrieveByID(int id_address)
    {
        return null;
    }


    /**
     * Retrieve all the Shipping Addresses of an user
     * @param username
     * @return addresses
     */
    @Override
    public HashSet<AddressBean> retrieveByUsername(String username)
    {
        return null;
    }

}