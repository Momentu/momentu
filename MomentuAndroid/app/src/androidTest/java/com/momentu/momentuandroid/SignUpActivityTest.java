package com.momentu.momentuandroid;


import android.app.Activity;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SignUpActivityTest {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Rule
    public ActivityTestRule<SignUpActivity> signUpActivityTestRule = new ActivityTestRule<>(SignUpActivity.class);


    //This is an auto generated test using record espresso test.
    @Test
    public void signUpActivityTest() {

        ViewInteraction button = onView(
                allOf(withId(R.id.signUpButton), withText("Sign Up"), isDisplayed()));
        button.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.createAnAccountText), withText("Create an Account"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Create an Account")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.signUpText), withText("Sign up to see photos and videos"+"\n"+"in a certain location"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Sign up to see photos and videos"+"\n"+"in a certain location")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.username), withText("Username:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("Username:")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.password), withText("Password:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("Password:")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.firstName), withText("First Name:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                        2),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("First Name:")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.lastName), withText("Last Name:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                        3),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("Last Name:")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.gender), withText("Gender"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                        4),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("Gender")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.email), withText("Email:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                        5),
                                0),
                        isDisplayed()));
        textView8.check(matches(withText("Email:")));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.signUpButton),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                0),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.usernameInput),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText2.perform(replaceText("falharbi88"));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.passwordInput),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        editText3.perform(replaceText("123456"));


        ViewInteraction editText4 = onView(
                allOf(withId(R.id.firstNameInput),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                        2),
                                1),
                        isDisplayed()));
        editText4.perform(replaceText("fahad"));

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.lastNameInput),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class),
                                        3),
                                1),
                        isDisplayed()));
        editText5.perform(replaceText("alharbi"));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.signUpButton), withText("Sign Up"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.usernameInput), isDisplayed()));
        appCompatEditText.perform(replaceText("falharbi88"), closeSoftKeyboard());

        pressBack();

    }


    //This is an UI Test that these the existence of all the activity widgets
    @Test
    public void termAndPolicyActivityUITest(){

        Activity activity = signUpActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.signUpText));
        assertNotNull(activity.findViewById(R.id.createAnAccountText));
        assertNotNull(activity.findViewById(R.id.usernameInput));
        assertNotNull(activity.findViewById(R.id.username));
        assertNotNull(activity.findViewById(R.id.passwordInput));
        assertNotNull(activity.findViewById(R.id.password));
        assertNotNull(activity.findViewById(R.id.firstNameInput));
        assertNotNull(activity.findViewById(R.id.firstName));
        assertNotNull(activity.findViewById(R.id.lastNameInput));
        assertNotNull(activity.findViewById(R.id.lastName));
        assertNotNull(activity.findViewById(R.id.gender));
        assertNotNull(activity.findViewById(R.id.maleRadioButton));
        assertNotNull(activity.findViewById(R.id.femaleRadioButton));
        assertNotNull(activity.findViewById(R.id.email));
        assertNotNull(activity.findViewById(R.id.emailInput));
        assertNotNull(activity.findViewById(R.id.signUpButton));

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
