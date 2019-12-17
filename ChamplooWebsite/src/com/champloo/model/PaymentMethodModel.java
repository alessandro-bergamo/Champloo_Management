package com.champloo.model;

import com.champloo.bean.PaymentMethodBean;

import java.util.HashSet;

public interface PaymentMethodModel
{

    boolean insertPMethod(PaymentMethodBean newPMethod);

    boolean deletePMethod(PaymentMethodBean PMethod);

    PaymentMethodBean retrieveByID(int id_pmethod);

    HashSet<PaymentMethodBean> retrieveByUsername(String username);

}
