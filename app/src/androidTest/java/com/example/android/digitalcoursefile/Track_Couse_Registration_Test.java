package com.example.android.digitalcoursefile;

import android.view.Gravity;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

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
@RunWith(AndroidJUnit4.class)
public class Track_Couse_Registration_Test {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnYourNavigationItem_Files() throws InterruptedException {
        onView(withId(R.id.editText)).perform(typeText("Vijay"));
        onView(withId(R.id.editText2)).perform(typeText("Sai18"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
        //onView(withText("Login Successful")).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        TimeUnit.MILLISECONDS.sleep(3000);

        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        TimeUnit.MILLISECONDS.sleep(1500);

        // Start the screen of your activity.
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_courses));

        TimeUnit.MILLISECONDS.sleep(1500);

        onView(withId(R.id.button18)).perform(click());
        TimeUnit.MILLISECONDS.sleep(1500);

        // Check that you Activity was opened.
        //onView(withId(R.layout.activity_registered_course)).check(matches(isDisplayed()));
    }
}
