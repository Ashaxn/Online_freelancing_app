package com.example.onlinefreelaceapp;

import com.example.onlinefreelaceapp.adapter.GigAdapter;
import com.example.onlinefreelaceapp.adapter.GigHolder;
import com.example.onlinefreelaceapp.ui.main.GigViewActivity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


/**
public class ExampleUnitTest {

    private GigHolder gigviewact;

    @Before
    public void setUp(){
        gigviewact=new GigHolder;
    }


    @Test
    public void amount_isCorrect() {
        double advanceAmount = 1000;
        double secondAmount = 2000;
        double txt_amount= (float) GigHolder.parseDouble(advanceAmount) + Double.parseDouble(secondAmount);
        assertEquals(5000,txt_amount,0.001);
    }



}
 **/