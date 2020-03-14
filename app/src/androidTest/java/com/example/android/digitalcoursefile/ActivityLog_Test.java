package com.example.android.digitalcoursefile;

import android.view.Gravity;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;

public class ActivityLog_Test {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnYourNavigationItem_Files() throws InterruptedException {
        onView(withId(R.id.editText)).perform(typeText("test"));
        onView(withId(R.id.editText2)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
        //onView(withText("Login Successful")).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        Thread.sleep(3000);

        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        Thread.sleep(1500);

        // Start the screen of your activity.
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_courses));

        Thread.sleep(1500);

        onView(withId(R.id.button34)).perform(click());
        Thread.sleep(1500);

        onView(withId(R.id.spinner5)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("cse111"))).perform(click());
        onView(withId(R.id.spinner5)).check(matches(withSpinnerText(containsString("cse111"))));
        Thread.sleep(1500);
        onView(withId(R.id.editText15)).perform(typeText("Pretty good!"), closeSoftKeyboard());
        Thread.sleep(1500);
        onView(withId(R.id.button35)).perform(click());
        Thread.sleep(1000);
    }
}
