package com.example.onlinefreelaceapp.Common;

import androidx.test.rule.ActivityTestRule;

import com.example.onlinefreelaceapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class PostRequestTest {

    @Rule
    public ActivityTestRule<PostRequest> activityTestRule = new ActivityTestRule<>(PostRequest.class);

    @Test
    public void testIsActivityView(){
        onView(withId(R.id.txtPostTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.txtPostMobile)).check(matches(isDisplayed()));
        onView(withId(R.id.dropdown_menu_autocomplete)).check(matches(isDisplayed()));
        onView(withId(R.id.txtPostDOB)).check(matches(isDisplayed()));
        onView(withId(R.id.btnNextPostRequest)).check(matches(isDisplayed()));
    }
}