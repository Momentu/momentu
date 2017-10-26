package com.momentu.momentuandroid;


import android.app.Activity;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertNotNull;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    //This is an auto generated test using record espresso test.
    @Test
    public void loginActivityTest() {

        ViewInteraction editText5 = onView(
                withId(R.id.username));
        editText5.perform(scrollTo(), replaceText("fahad"), closeSoftKeyboard());

        ViewInteraction editText6 = onView(
                withId(R.id.password));
        editText6.perform(scrollTo(), replaceText("123456"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.login_button), withText("Login"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form))))));
        button2.perform(scrollTo(), click());

    }

    //This is an UI Test that these the existence of all the activity widgets
    @Test
    public void termAndPolicyActivityUITest(){

        Activity activity = loginActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.tWelcomeUser));
        assertNotNull(activity.findViewById(R.id.username));
        assertNotNull(activity.findViewById(R.id.password));
        assertNotNull(activity.findViewById(R.id.login_button));

    }

    /*
    This method will be refactor after implementing the authentication.
    @Test
    public void clickLoginButton_WithNoUsernameAndPassowrd() {
        //locate and click on the term and policy button
        onView(withId(R.id.login_button)).perform(click());

        //check if the the term and policy button screen is displayed by asserting that the term and policy text (TextView) is displayed
        onView(withId(R.id.username)).perform(hasFocus().matches())
    }
    */
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
