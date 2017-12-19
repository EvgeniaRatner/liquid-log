package ru.naumen.sd40.log.parser.DataParsers;

import org.springframework.stereotype.Service;
import ru.naumen.sd40.log.parser.Interfaces.IDataParser;
import ru.naumen.sd40.log.parser.Storages.GCStorage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class GCDataParser implements IDataParser<GCStorage>{

    private static final Pattern gcExecutionTime = Pattern.compile(".*real=(.*)secs.*");

    public void parseLine(GCStorage storage, String line) {
        Matcher matcher = gcExecutionTime.matcher(line);
        if (matcher.find())
        {
            storage.ds.addValue(Double.parseDouble(matcher.group(1).trim().replace(',', '.')));
        }
    }
}
