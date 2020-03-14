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

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;
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
                .perform(NavigationViewActions.navigateTo(R.id.nav_courses));

        Thread.sleep(1500);

        onView(withId(R.id.button16)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.spinner3)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("cse300"))).perform(click());
        onView(withId(R.id.spinner3)).check(matches(withSpinnerText(containsString("cse300"))));
        Thread.sleep(1500);
        onView(withId(R.id.button24)).perform(click());
//        onView(withId(R.id.editText)).perform(typeText("test"));
//        onView(withId(R.id.editText2)).perform(typeText("123456"), closeSoftKeyboard());
//        onView(withId(R.id.button)).perform(click());
        Thread.sleep(1500);
        onView(withText("Course Registered Wait for Approval... ")).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        // Check that you Activity was opened.
        //onView(withId(R.id.button16)).check(matches(isDisplayed()));
    }

    //    @Test
//    public void testNavigateToScrollingActivity() throws Exception {
//        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
//        onView(withId(R.id.drawer_layout)).check(matches(isOpen()));
//        //Here's the difference
//        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_scroller));
//    }
}
