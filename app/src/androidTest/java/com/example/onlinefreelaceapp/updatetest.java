package com.example.onlinefreelaceapp;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class updatetest {

    @Rule
    public ActivityTestRule<Display_Gigs_page> activityTestRule = new ActivityTestRule<Display_Gigs_page>(Display_Gigs_page.class);



    @Test
    public  void gigpageLaunch(){

        onView(withId(R.id.addgigbutton)).perform(click());
        Intent i = new Intent();
        activityTestRule.launchActivity(i);

    }
}
