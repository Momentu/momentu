package com.momentu.momentuandroid;


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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WelcomeActivityTest {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Test
    public void welcomeActivityTest() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.mementuText), withText("Momentu"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Momentu")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.depaulUniversity), withText("DePaul University"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("DePaul University")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textView8), withText("Already have a Momentu account?"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("Already have a Momentu account?")));

        ViewInteraction button = onView(
                allOf(withId(R.id.termAndPolicyButton),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.loginButton),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.signUpButton),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

    }

    @Test
    public void clickSignUpButton_opensSignUpActivity() {
        //locate and click on the sign up button
        onView(withId(R.id.signUpButton)).perform(click());

        //check if the sign up screen is displayed by asserting that the sign up text (TextView) is displayed
        onView(withId(R.id.signUpText)).check(matches(isDisplayed()));
    }

    @Test
    public void clickLoginButton_opensLoginActivity() {
        //locate and click on the login button
        onView(withId(R.id.loginButton)).perform(click());

        //check if the login button screen is displayed by asserting that the welcome user text (TextView) is displayed
        onView(withId(R.id.tWelcomeUser)).check(matches(isDisplayed()));
    }

    @Test
    public void clickTermAndPolicyButton_opensTermAndPolicyActivity() {
        //locate and click on the term and policy button
        onView(withId(R.id.termAndPolicyButton)).perform(click());

        //check if the the term and policy button screen is displayed by asserting that the term and policy text (TextView) is displayed
        onView(withId(R.id.term_and_policy_text_view)).check(matches(isDisplayed()));
    }


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
