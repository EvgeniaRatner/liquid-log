package ru.naumen.sd40.log.parser.TimeParsers;

import ru.naumen.sd40.log.parser.Interfaces.ITimeParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TOPTimeParser implements ITimeParser {//дописать, внутренний цикл вынести во внешнюю обработку

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH:mm");

    private static final Pattern TIME_PATTERN = Pattern
            .compile("^\\d+ \\[.*?] \\((\\d{2} .{3} \\d{4} \\d{2}:\\d{2}:\\d{2},\\d{3})\\)");

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy HH:mm:ss,SSS",
            new Locale("ru", "RU"));

    public void setTimezone(String timeZone)
    {
        if (timeZone != "")
            sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
    }

    public long parseTime(String line) throws ParseException {
        Matcher matcher = TIME_PATTERN.matcher(line);

        if (matcher.find())
        {
            String timeString = matcher.group(1);
            return DATE_FORMAT.parse(timeString).getTime();
        }
        return 0L;
    }

    private String parseDate(String fileName)
    {
        Matcher matcher = Pattern.compile("\\d{8}|\\d{4}-\\d{2}-\\d{2}").matcher(fileName);
        if (!matcher.find())
        {
            throw new IllegalArgumentException();
        }
        return matcher.group(0).replaceAll("-", "");

    }
}
