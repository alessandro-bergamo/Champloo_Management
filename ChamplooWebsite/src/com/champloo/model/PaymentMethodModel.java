package com.champloo.model;

import com.champloo.bean.PaymentMethodBean;

import java.sql.SQLException;
import java.util.HashSet;

public interface PaymentMethodModel
{

    boolean insertPMethod(PaymentMethodBean newPMethod) throws SQLException;

    boolean deletePMethod(int id_method) throws SQLException;

    PaymentMethodBean retrieveByCardNumber(String card_number) throws SQLException;

    HashSet<PaymentMethodBean> retrieveByUserID(int user_id) throws SQLException;

}
