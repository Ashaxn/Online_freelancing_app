package com.example.onlinefreelaceapp;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ReviewOrderTest {

    @Rule
    public ActivityTestRule<ReviewOrder> reviewOrderActivityTestRule = new ActivityTestRule<>(ReviewOrder.class);

    private String email = "abc";
    private String url = "https://stackoverflow.com/questions/48723474/how-to-check-if-an-activity-receives-an-intent-sent-from-another-activity";

    private String massage = "abc abc abc abc abc abc abc abc abc";


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testUserInputScenario() {

        Espresso.onView(withId(R.id.linkOrderResource)).perform(typeText(url));
        Espresso.onView(withId(R.id.txtWorkingEmailOrder)).perform(typeText(email));
        Espresso.onView(withId(R.id.txtOrderRequirement)).perform(typeText("abc abc abc"));
        Espresso.closeSoftKeyboard();


    }

    @Test
    public void test_isReviewOrderActivityInView() {
        Espresso.onView(withId(R.id.post_request_back_button)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.gigtitleorderreview)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.gigimageorder)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.gigdescriptionorder)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.orderBySeller)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.txtWorkingEmailOrder)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.txtOrderRequirement)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.linkOrderResource)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.hrordersummery)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.txtOrderSubTotal)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.txtOrderServiceCharge)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.txtOrderTotal)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.btnAddOrder)).check(matches(isDisplayed()));


    }

    @After
    public void tearDown() throws Exception {

        email = null;
        url = null;
        massage = null;

    }
}
