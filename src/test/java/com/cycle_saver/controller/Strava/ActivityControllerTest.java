package com.cycle_saver.controller.Strava;

import com.cycle_saver.model.Strava.Activity;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class ActivityControllerTest {

    @Test
    public void shouldConvertDateTimeFromString()  {
        String startDateLocal = "2018-11-07T20:16:26Z";
        LocalDateTime expected = LocalDateTime.of(2018, 11, 07,20, 16, 26);
        ActivityController activityController = new ActivityController();
        Activity activityMock = mock(Activity.class);

        when(activityMock.getStartDateLocal()).thenReturn(startDateLocal);

        LocalDateTime startDateTime = activityController.extractStartDateTime(activityMock);

        Assert.assertEquals(expected, startDateTime);
    }
}