package ru.naumen.sd40.log.parser.DataParsers;

import org.springframework.stereotype.Service;
import ru.naumen.sd40.log.parser.Interfaces.IDataParser;
import ru.naumen.sd40.log.parser.Storages.TOPStorage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class TOPDataParser implements IDataParser<TOPStorage> {

    private static final Pattern cpuAndMemPattren = Pattern
            .compile("^ *\\d+ \\S+ +\\S+ +\\S+ +\\S+ +\\S+ +\\S+ +\\S+ \\S+ +(\\S+) +(\\S+) +\\S+ java");

    public void parseLine(TOPStorage dataSet, String line)
    {
        Matcher la = Pattern.compile(".*load average:(.*)").matcher(line);
        if (la.find())
        {
            dataSet.addLa(Double.parseDouble(la.group(1).split(",")[0].trim()));
            return;
        }

        Matcher cpuAndMemMatcher = cpuAndMemPattren.matcher(line);
        if (cpuAndMemMatcher.find())
        {
            dataSet.addCpu(Double.valueOf(cpuAndMemMatcher.group(1)));
            dataSet.addMem(Double.valueOf(cpuAndMemMatcher.group(2)));
        }
    }
}
