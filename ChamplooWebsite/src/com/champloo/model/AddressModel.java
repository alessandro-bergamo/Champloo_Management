package com.champloo.model;

import com.champloo.bean.AddressBean;
import java.sql.SQLException;
import java.util.ArrayList;


public interface AddressModel
{
    boolean insertAddress(AddressBean newAddress) throws SQLException;

    boolean deleteAddress(int id_address) throws SQLException;

    AddressBean retrieveByID(int id_address) throws SQLException;

    ArrayList<AddressBean> retrieveByUserID(int id_user) throws SQLException;
}
