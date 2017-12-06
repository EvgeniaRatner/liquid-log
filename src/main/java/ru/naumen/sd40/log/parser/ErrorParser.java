package ru.naumen.sd40.log.parser;

import ru.naumen.sd40.log.parser.Interfaces.IDataParser;
import ru.naumen.sd40.log.parser.Storage.ErrorStorage;

import java.util.regex.Pattern;

/**
 * Created by doki on 22.10.16.
 */
public class ErrorParser implements IDataParser<ErrorStorage>
{
    long warnCount;
    long errorCount;
    long fatalCount;

    Pattern warnRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) WARN");
    Pattern errorRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) ERROR");
    Pattern fatalRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) FATAL");

    public void parseLine(String line, ErrorStorage storage)
    {
        if (warnRegEx.matcher(line).find())
        {
            storage.warnCount++;
        }
        if (errorRegEx.matcher(line).find())
        {
            storage.errorCount++;
        }
        if (fatalRegEx.matcher(line).find())
        {
            storage.fatalCount++;
        }
    }

    public long getWarnCount()
    {
        return warnCount;
    }

    public long getErrorCount()
    {
        return errorCount;
    }

    public long getFatalCount()
    {
        return fatalCount;
    }
}