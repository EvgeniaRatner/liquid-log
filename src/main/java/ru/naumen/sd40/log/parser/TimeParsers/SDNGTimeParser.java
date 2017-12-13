package ru.naumen.sd40.log.parser.TimeParsers;

import ru.naumen.sd40.log.parser.Interfaces.ITimeParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SDNGTimeParser implements ITimeParser {

    private static final Pattern PATTERN = Pattern
            .compile("^\\d+ \\[.*?] \\((\\d{2} .{3} \\d{4} \\d{2}:\\d{2}:\\d{2},\\d{3})\\)");

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy HH:mm:ss,SSS",
            new Locale("ru", "RU"));


    public SDNGTimeParser(String timeZone)
    {
        if(timeZone == null)
            timeZone = "GMT";
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timeZone));
    }

    public long parseTime(String line) throws ParseException {
        Matcher matcher = PATTERN.matcher(line);

        if (matcher.find())
        {
            Date parse = DATE_FORMAT.parse(matcher.group(1));
            return parse.getTime();
        }
        return 0L;
    }
}
