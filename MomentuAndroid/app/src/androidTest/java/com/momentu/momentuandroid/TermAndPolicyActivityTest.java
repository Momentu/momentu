package com.momentu.momentuandroid;


import android.app.Activity;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.text.Html;
import android.text.Spanned;
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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TermAndPolicyActivityTest {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Rule
    public ActivityTestRule<TermAndPolicyActivity> termAndPolicyActivityTestRule = new ActivityTestRule<>(TermAndPolicyActivity.class);

    //This is an auto generated test using record espresso test.
    @Test
    public void termAndPolicyActivityTest() {
        ViewInteraction button = onView(
                allOf(withId(R.id.termAndPolicyButton), withText("By using Momentu, you agree to Momentu's"+"\n"+"Term of Use and Privacy Policy"), isDisplayed()));
        button.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.term_and_policy_text_view), withText("Terms of Service"+"\n"+"\n"+"Welcome!"+"\n"+"\n"+"We’ve drafted these Terms of Service (which we call the “Terms”) so you’ll know the rules that govern our relationship with you. Although we have tried our best to strip the legalese from the Terms, there are places where these Terms may still read like a traditional contract. There’s a good reason for that: These Terms do indeed form a legally binding contract between you and Momentu Inc. So please read them carefully."+"\n"+"\n"+"By using Momentu or any of our other products or services that link to these Terms (we refer to these simply as the “Services”), you agree to the Terms. Of course, if you don’t agree with them, then don’t use the Services."+"\n"+"\n"+"ARBITRATION NOTICE: THESE TERMS CONTAIN AN ARBITRATION CLAUSE A LITTLE LATER ON. EXCEPT FOR CERTAIN TYPES OF DISPUTES MENTIONED IN THAT ARBITRATION CLAUSE, YOU AND MOMENTU INC. AGREE THAT DISPUTES BETWEEN US WILL BE RESOLVED BY MANDATORY BINDING ARBITRATION, AND YOU AND MOMENTU INC. WAIVE ANY RIGHT TO PARTICIPATE IN A CLASS-ACTION LAWSUIT OR CLASS-WIDE ARBITRATION."+"\n"+"\n"+"1. Who Can Use the Services"+"\n"+"\n"+"No one under 13 is allowed to create an account or use the Services. We may offer additional Services with additional terms that may require you to be even older to use them. So please read all terms carefully."+"\n"+"\n"+"By using the Services, you state that:"+"\n"+"\n"+"You can form a binding contract with Momentu Inc."+"\n"+"\n"+"You are not a person who is barred from receiving the Services under the laws of the United States or any other applicable jurisdiction—meaning that you do not appear on the U.S. Treasury Department’s list of Specially Designated Nationals or face any other similar prohibition."+"\n"+"\n"+"You will comply with these Terms and all applicable local, state, national, and international laws, rules, and regulations."+"\n"+"\n"+"If you are using the Services on behalf of a business or some other entity, you state that you are authorized to grant all licenses set forth in these Terms and to agree to these Terms on behalf of the business or entity. If you are using the Services on behalf of an entity of the U.S. Government, you agree to the Amendment to Momentu Inc. Terms of Service for U.S. Government Users."+"\n"+"\n"+"Contact us"+"\n"+"\n"+"We welcome comments, questions, concerns, or suggestions."+"\n"+"\n"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.term_and_policy_text_view), withText("Terms of Service"+"\n"+"\n"+"Welcome!"+"\n"+"\n"+"We’ve drafted these Terms of Service (which we call the “Terms”) so you’ll know the rules that govern our relationship with you. Although we have tried our best to strip the legalese from the Terms, there are places where these Terms may still read like a traditional contract. There’s a good reason for that: These Terms do indeed form a legally binding contract between you and Momentu Inc. So please read them carefully."+"\n"+"\n"+"By using Momentu or any of our other products or services that link to these Terms (we refer to these simply as the “Services”), you agree to the Terms. Of course, if you don’t agree with them, then don’t use the Services."+"\n"+"\n"+"ARBITRATION NOTICE: THESE TERMS CONTAIN AN ARBITRATION CLAUSE A LITTLE LATER ON. EXCEPT FOR CERTAIN TYPES OF DISPUTES MENTIONED IN THAT ARBITRATION CLAUSE, YOU AND MOMENTU INC. AGREE THAT DISPUTES BETWEEN US WILL BE RESOLVED BY MANDATORY BINDING ARBITRATION, AND YOU AND MOMENTU INC. WAIVE ANY RIGHT TO PARTICIPATE IN A CLASS-ACTION LAWSUIT OR CLASS-WIDE ARBITRATION."+"\n"+"\n"+"1. Who Can Use the Services"+"\n"+"\n"+"No one under 13 is allowed to create an account or use the Services. We may offer additional Services with additional terms that may require you to be even older to use them. So please read all terms carefully."+"\n"+"\n"+"By using the Services, you state that:"+"\n"+"\n"+"You can form a binding contract with Momentu Inc."+"\n"+"\n"+"You are not a person who is barred from receiving the Services under the laws of the United States or any other applicable jurisdiction—meaning that you do not appear on the U.S. Treasury Department’s list of Specially Designated Nationals or face any other similar prohibition."+"\n"+"\n"+"You will comply with these Terms and all applicable local, state, national, and international laws, rules, and regulations."+"\n"+"\n"+"If you are using the Services on behalf of a business or some other entity, you state that you are authorized to grant all licenses set forth in these Terms and to agree to these Terms on behalf of the business or entity. If you are using the Services on behalf of an entity of the U.S. Government, you agree to the Amendment to Momentu Inc. Terms of Service for U.S. Government Users."+"\n"+"\n"+"Contact us"+"\n"+"\n"+"We welcome comments, questions, concerns, or suggestions."+"\n"+"\n"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Terms of Service"+"\n"+"\n"+"Welcome!"+"\n"+"\n"+"We’ve drafted these Terms of Service (which we call the “Terms”) so you’ll know the rules that govern our relationship with you. Although we have tried our best to strip the legalese from the Terms, there are places where these Terms may still read like a traditional contract. There’s a good reason for that: These Terms do indeed form a legally binding contract between you and Momentu Inc. So please read them carefully."+"\n"+"\n"+"By using Momentu or any of our other products or services that link to these Terms (we refer to these simply as the “Services”), you agree to the Terms. Of course, if you don’t agree with them, then don’t use the Services."+"\n"+"\n"+"ARBITRATION NOTICE: THESE TERMS CONTAIN AN ARBITRATION CLAUSE A LITTLE LATER ON. EXCEPT FOR CERTAIN TYPES OF DISPUTES MENTIONED IN THAT ARBITRATION CLAUSE, YOU AND MOMENTU INC. AGREE THAT DISPUTES BETWEEN US WILL BE RESOLVED BY MANDATORY BINDING ARBITRATION, AND YOU AND MOMENTU INC. WAIVE ANY RIGHT TO PARTICIPATE IN A CLASS-ACTION LAWSUIT OR CLASS-WIDE ARBITRATION."+"\n"+"\n"+"1. Who Can Use the Services"+"\n"+"\n"+"No one under 13 is allowed to create an account or use the Services. We may offer additional Services with additional terms that may require you to be even older to use them. So please read all terms carefully."+"\n"+"\n"+"By using the Services, you state that:"+"\n"+"\n"+"You can form a binding contract with Momentu Inc."+"\n"+"\n"+"You are not a person who is barred from receiving the Services under the laws of the United States or any other applicable jurisdiction—meaning that you do not appear on the U.S. Treasury Department’s list of Specially Designated Nationals or face any other similar prohibition."+"\n"+"\n"+"You will comply with these Terms and all applicable local, state, national, and international laws, rules, and regulations."+"\n"+"\n"+"If you are using the Services on behalf of a business or some other entity, you state that you are authorized to grant all licenses set forth in these Terms and to agree to these Terms on behalf of the business or entity. If you are using the Services on behalf of an entity of the U.S. Government, you agree to the Amendment to Momentu Inc. Terms of Service for U.S. Government Users."+"\n"+"\n"+"Contact us"+"\n"+"\n"+"We welcome comments, questions, concerns, or suggestions."+"\n"+"\n")));

        ViewInteraction scrollView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(android.R.id.content),
                                0),
                        0),
                        isDisplayed()));
        scrollView.check(matches(isDisplayed()));

    }

    //This is an UI Test that these the existence of all the activity widgets
    @Test
    public void termAndPolicyActivityUITest(){

       Activity activity = termAndPolicyActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.term_and_policy_text_view));
        TextView termAndPolicyText = (TextView) activity.findViewById(R.id.term_and_policy_text_view);
        assertTrue(termAndPolicyText.isShown());

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
