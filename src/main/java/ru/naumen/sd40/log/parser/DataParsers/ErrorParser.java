package ru.naumen.sd40.log.parser.DataParsers;

import org.springframework.stereotype.Service;
import ru.naumen.sd40.log.parser.Interfaces.IDataParser;
import ru.naumen.sd40.log.parser.Storages.ErrorStorage;

import java.util.regex.Pattern;


@Service
public class ErrorParser implements IDataParser<ErrorStorage>
{
    Pattern warnRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) WARN");
    Pattern errorRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) ERROR");
    Pattern fatalRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) FATAL");

    public void parseLine(ErrorStorage storage, String line)
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
}
