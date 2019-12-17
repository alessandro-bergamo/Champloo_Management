package com.champloo.model;

import com.champloo.bean.PaymentMethodBean;

import java.util.HashSet;

public class PaymentMethodDAO implements PaymentMethodModel
{

    /**
     * Insert a new Payment Method
     * @param newPMethod
     * @return method_added
     */
    @Override
    public boolean insertPMethod(PaymentMethodBean newPMethod)
    {
        return false;
    }


    /**
     * Deletes a Payment Method
     * @param PMethod
     * @return method_deleted
     */
    @Override
    public boolean deletePMethod(PaymentMethodBean PMethod)
    {
        return false;
    }


    /**
     * Retrieve a Payment Method by his ID
     * @param id_pmethod
     * @return payment_method
     */
    @Override
    public PaymentMethodBean retrieveByID(int id_pmethod)
    {
        return null;
    }


    /**
     * Retrieve all the Payment Methods of an user
     * @param username
     * @return payment_methods
     */
    @Override
    public HashSet<PaymentMethodBean> retrieveByUsername(String username)
    {
        return null;
    }

}
