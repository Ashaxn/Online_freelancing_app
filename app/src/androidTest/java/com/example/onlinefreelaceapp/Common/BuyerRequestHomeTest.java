package com.example.onlinefreelaceapp.Common;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.example.onlinefreelaceapp.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class BuyerRequestHomeTest {

    @Rule
    public ActivityTestRule<BuyerRequestHome> activityTestRule = new ActivityTestRule<>(BuyerRequestHome.class);

    @Test
    public void testLaunch(){

        onView(withId(R.id.requestlist)).perform(click());
        Intent i = new Intent();
        activityTestRule.launchActivity(i);

    }

    @Test
    public void testIsActivityView(){
        onView(withId(R.id.buyer_request_back_button)).check(matches(isDisplayed()));
        onView(withId(R.id.requestlist)).check(matches(isDisplayed()));
    }

}