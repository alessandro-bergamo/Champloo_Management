package com.champloo.model;

import com.champloo.bean.PaymentMethodBean;

import java.sql.SQLException;
import java.util.HashSet;

public interface PaymentMethodModel
{

    boolean insertPMethod(PaymentMethodBean newPMethod) throws SQLException;

    boolean deletePMethod(PaymentMethodBean PMethod) throws SQLException;

    PaymentMethodBean retrieveByID(String card_number) throws SQLException;

    HashSet<PaymentMethodBean> retrieveByUsername(String username) throws SQLException;

}
