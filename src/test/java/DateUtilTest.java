import com.cycle_saver.utils.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class DateUtilTest {

    @Test
    public void shouldConvertDateTimeFromString()  {
        String startDateLocal = "2018-11-07T20:16:26Z";
        LocalDateTime expected = LocalDateTime.of(2018, 11, 07,20, 16, 26);

        LocalDateTime startDateTime = DateUtil.extractStartDateTime(startDateLocal);

        Assert.assertEquals(expected, startDateTime);
    }
}
