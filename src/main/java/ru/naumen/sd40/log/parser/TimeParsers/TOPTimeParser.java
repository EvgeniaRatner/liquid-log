package ru.naumen.sd40.log.parser.TimeParsers;

import ru.naumen.sd40.log.parser.Interfaces.ITimeParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TOPTimeParser implements ITimeParser{

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHH:mm");
    private static final Pattern timeRegex = Pattern.compile("^_+ (\\S+)");
    private String dataDate;
    private long currentTime;

    public TOPTimeParser(String fileName, String timeZone)
    {
        dataDate = parseDate(fileName);
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timeZone));
    }

    public long parseTime(String line) throws ParseException {
        Matcher matcher = timeRegex.matcher(line);
        if (matcher.find())
        {
            currentTime = DATE_FORMAT.parse(dataDate + matcher.group(1)).getTime();
            return currentTime;
        }
        return currentTime;
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
