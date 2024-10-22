package com.champloo.model;

import com.champloo.bean.PaymentMethodBean;
import java.sql.SQLException;
import java.util.ArrayList;


public interface PaymentMethodModel
{
    boolean insertPMethod(PaymentMethodBean newPMethod) throws SQLException;

    boolean deletePMethod(int id_method) throws SQLException;

    PaymentMethodBean retrieveByCNumber(String card_number) throws SQLException;

    ArrayList<PaymentMethodBean> retrieveByUserID(int id_user) throws SQLException;
}
