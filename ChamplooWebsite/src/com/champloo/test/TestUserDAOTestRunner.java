package com.champloo.test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
public class TestUserDAOTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(UserDAOTest.class);

        System.out.println(result.wasSuccessful());
    }
}