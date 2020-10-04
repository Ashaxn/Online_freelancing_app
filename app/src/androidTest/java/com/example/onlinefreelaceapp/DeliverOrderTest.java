package com.example.onlinefreelaceapp;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DeliverOrderTest {

    @Rule
    public ActivityTestRule<DeliverOrder> deliverOrderActivityTestRule = new ActivityTestRule<>(DeliverOrder.class);

    private String massage = "Welcome Back";

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testUserInputDeliverOrder() {

        Espresso.onView(withId(R.id.txtDeliverMsg)).perform(typeText(massage));

        Espresso.closeSoftKeyboard();

    }

    @After
    public void tearDown() throws Exception {

    }
}
