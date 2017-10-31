package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.AssertionFailedError;

import org.hamcrest.core.IsNot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by AliT on 10/29/17.
 */

@RunWith(AndroidJUnit4.class)
public class JokeTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testJokes() {

        onView(withId(R.id.tellJokeButton)).perform(click());

        try {
            onView(withId(R.id.jokeTextView)).check(matches(isDisplayed()));
        }
        catch (AssertionFailedError e) {
            pressBack();
        }

        onView(withId(R.id.jokeTextView)).check(matches(IsNot.not(withText(""))));

    }

}
