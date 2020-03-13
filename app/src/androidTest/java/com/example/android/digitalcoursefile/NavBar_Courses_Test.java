package com.example.android.digitalcoursefile;
import android.view.Gravity;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class NavBar_Courses_Test {
    @Rule
    public ActivityTestRule<Dashboard> activityTestRule
            = new ActivityTestRule<>(Dashboard.class);

    @Before
    public void yourSetUPFragment() {
        activityTestRule.getActivity()
                .getFragmentManager().beginTransaction();
    }

    @Test
    public void clickOnYourNavigationItem_Files() throws InterruptedException {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        Thread.sleep(1500);

        // Start the screen of your activity.
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_files));

        Thread.sleep(1500);

        // Check that you Activity was opened.
        onView(withId(R.id.button16)).check(matches(isDisplayed()));
    }

    //    @Test
//    public void testNavigateToScrollingActivity() throws Exception {
//        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
//        onView(withId(R.id.drawer_layout)).check(matches(isOpen()));
//        //Here's the difference
//        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_scroller));
//    }
}
