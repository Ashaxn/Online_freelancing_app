package com.example.onlinefreelaceapp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReviewOrderUnitTesting {

    private ReviewOrder reviewOrder;

    @Before
    public void setUp() throws Exception {

        reviewOrder = new ReviewOrder();

    }

    @Test
    public void testTotalPriceReviewOrder() {

        int result = reviewOrder.getTotalPrice(2,3);
        Assert.assertEquals(5,result,1);

    }

    @After
    public void tearDown() throws Exception {

    }
}
