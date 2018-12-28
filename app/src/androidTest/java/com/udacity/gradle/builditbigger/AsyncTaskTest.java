package com.udacity.gradle.builditbigger;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import androidx.test.rule.ActivityTestRule;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

public class AsyncTaskTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testAsyncTask() throws InterruptedException, ExecutionException {
        // on the MainActivity execute the AsyncTask
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(Assert::assertNotNull);
        endpointsAsyncTask.execute(activityTestRule.getActivity());

        // the String returned in the onPostExecute is being retrieved
        String randomJoke = endpointsAsyncTask.get();
        assertFalse(randomJoke.isEmpty());
        // If the string is not null, then we got a value, aka a joke
        assertNotNull(randomJoke);
    }
}
