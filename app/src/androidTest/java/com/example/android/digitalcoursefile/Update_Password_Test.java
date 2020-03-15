package com.example.android.digitalcoursefile;

import android.view.Gravity;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class Update_Password_Test {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnYourNavigationItem_Files() throws InterruptedException {
        onView(withId(R.id.editText)).perform(typeText("test"));
        onView(withId(R.id.editText2)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());

        Thread.sleep(3000);

        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        Thread.sleep(1500);

        // Start the screen of your activity.
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_personal));

        Thread.sleep(1500);

        onView(withId(R.id.button8)).perform(click());
        Thread.sleep(1500);

        onView(withId(R.id.editText13)).perform(typeText("123456"));
        onView(withId(R.id.editText14)).perform(typeText("123456"), closeSoftKeyboard());
        Thread.sleep(500);
        onView(withId(R.id.button26)).perform(click());
        Thread.sleep(500);
        onView(withText("Password Updated")).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
