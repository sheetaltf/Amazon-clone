package com.example.AmazonC;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class uitest {

    @Rule
    public ActivityScenarioRule<SplashScreen> mActivityScenarioRule =
            new ActivityScenarioRule<>(SplashScreen.class);

    @Test
    public void uitest() {
        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.signInTextView), withText("Already a customer? Sign in"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                3),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction materialTextView2 = onView(
                allOf(withId(R.id.signInTextView), withText("Already a customer? Sign in"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                3),
                        isDisplayed()));
        materialTextView2.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.loginEmail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                3),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("sheetal@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.loginPassword),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                4),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("147"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.loginPassword), withText("147"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                4),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("1472583"));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.loginPassword), withText("1472583"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                4),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.signInButton), withText("Sign-In"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                5),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction cardView = onView(
                allOf(withId(R.id.shoes1),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.HorizontalScrollView")),
                                        0),
                                0)));
        cardView.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.order), withText("Add to Cart"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.core.widget.NestedScrollView")),
                                        0),
                                7),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.bottom_addprod), withText("Add Product"),
                        childAtPosition(
                                allOf(withId(R.id.radioGroup1),
                                        childAtPosition(
                                                withId(R.id.bottomNavBar),
                                                0)),
                                1),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.addProductImg),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatImageView.perform(scrollTo(), click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.addProductName),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText5.perform(scrollTo(), replaceText("sh"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.addProductPrice),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                0)));
        appCompatEditText6.perform(scrollTo(), replaceText("3000"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.addProductName), withText("sh"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText7.perform(scrollTo(), replaceText("shirt "));

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.addProductName), withText("shirt "),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText8.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.addProductDesc),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText9.perform(scrollTo(), click());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.addProductDesc),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText10.perform(scrollTo(), replaceText("smooth and fabric"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.addProductCategory),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        appCompatEditText11.perform(scrollTo(), replaceText("shrirt"), closeSoftKeyboard());

        ViewInteraction materialTextView3 = onView(
                allOf(withId(R.id.confirmAddProd), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                9)));
        materialTextView3.perform(scrollTo(), click());

        ViewInteraction materialRadioButton2 = onView(
                allOf(withId(R.id.bottom_search), withText("Search"),
                        childAtPosition(
                                allOf(withId(R.id.radioGroup1),
                                        childAtPosition(
                                                withId(R.id.bottomNavBar),
                                                0)),
                                2),
                        isDisplayed()));
        materialRadioButton2.perform(click());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.searchEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.viewtoolbar),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText12.perform(replaceText("Redmi "), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.searchBtn), withText("SEARCH"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.viewtoolbar),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.searchEditText), withText("Redmi "),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.viewtoolbar),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText13.perform(replaceText("Redm"));

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.searchEditText), withText("Redm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.viewtoolbar),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText14.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.searchEditText), withText("Redm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.viewtoolbar),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText15.perform(replaceText(""));

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.searchEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.viewtoolbar),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText16.perform(closeSoftKeyboard());
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
