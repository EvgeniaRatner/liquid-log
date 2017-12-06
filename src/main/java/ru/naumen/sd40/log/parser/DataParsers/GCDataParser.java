package ru.naumen.sd40.log.parser.DataParsers;

import org.springframework.stereotype.Service;
import ru.naumen.sd40.log.parser.Interfaces.IDataParser;
import ru.naumen.sd40.log.parser.Storage.GCStorage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GCDataParser implements IDataParser<GCStorage> {

    public static final Pattern gcExecutionTime = Pattern.compile(".*real=(.*)secs.*");

    public void parseLine(String line, GCStorage storage)
    {
        Matcher matcher = gcExecutionTime.matcher(line);
        if (matcher.find())
        {
            storage.addValue(Double.parseDouble(matcher.group(1).trim().replace(',', '.')));
        }
    }
}
