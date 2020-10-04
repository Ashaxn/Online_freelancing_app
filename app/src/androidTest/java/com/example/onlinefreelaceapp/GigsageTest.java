package com.example.onlinefreelaceapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class GigsageTest {

    @Rule
    public ActivityTestRule<Create_A_Gig> displaygigpage = new ActivityTestRule<Create_A_Gig>(Create_A_Gig.class);
    
    @Test
    public void testLaunch(){

        onView(withId(R.id.txt_title)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_category)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_description)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_delivery_info)).check(matches(isDisplayed()));

    }


}
