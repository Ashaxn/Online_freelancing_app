package com.example.onlinefreelaceapp.Common;

import androidx.test.rule.ActivityTestRule;

import com.example.onlinefreelaceapp.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class PostRequestPageTwoTest {

    @Rule
    public ActivityTestRule<PostRequestPageTwo> activityTestRule = new ActivityTestRule<>(PostRequestPageTwo.class);

    @Test
    public void testIsActivityView(){
        onView(withId(R.id.btnSubmitPostRequest)).check(matches(isDisplayed()));
        onView(withId(R.id.txtPostBudget)).check(matches(isDisplayed()));
        onView(withId(R.id.txtPostDesc)).check(matches(isDisplayed()));
    }

}