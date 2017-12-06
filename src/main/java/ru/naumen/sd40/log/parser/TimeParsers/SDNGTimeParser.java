package ru.naumen.sd40.log.parser.TimeParsers;

import ru.naumen.sd40.log.parser.DataParsers.SDNGDataParser;
import ru.naumen.sd40.log.parser.Interfaces.ITimeParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SDNGTimeParser implements ITimeParser {

    private static final Pattern TIME_PATTERN = Pattern
            .compile("^\\d+ \\[.*?\\] \\((\\d{2} .{3} \\d{4} \\d{2}:\\d{2}:\\d{2},\\d{3})\\)");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy HH:mm:ss,SSS",
            new Locale("ru", "RU"));

    public void setTimezone(String timeZone){
        if (timeZone == "")
        {
            DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
            return;
        }
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timeZone));
    }

    public long parseTime(String line) throws ParseException
    {
        Matcher matcher = TIME_PATTERN.matcher(line);

        if (matcher.find())
        {
            String timeString = matcher.group(1);
            Date recDate = DATE_FORMAT.parse(timeString);
            return recDate.getTime();
        }
        return 0L;
    }
}
