package com.champloo.model;

import com.champloo.bean.AddressBean;

import java.sql.SQLException;
import java.util.HashSet;

public interface AddressModel
{

    boolean insertAddress(AddressBean newAddress) throws SQLException;

    boolean deleteAddress(AddressBean address) throws SQLException;

    AddressBean retrieveByID(int id_address) throws SQLException;

    HashSet<AddressBean> retrieveByUsername(int id_user) throws SQLException;

}
