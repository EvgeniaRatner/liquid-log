package ru.naumen.sd40.log.parser.Parsers;

import org.springframework.stereotype.Service;
import ru.naumen.sd40.log.parser.DataParsers.TOPDataParser;
import ru.naumen.sd40.log.parser.TimeParsers.TOPTimeParser;


@Service
public class TOPParser {

    private TOPDataParser dataParser;
    private TOPTimeParser timeParser;

    public TOPParser(TOPDataParser topDataParser, TOPTimeParser topTimeParser){
        dataParser = topDataParser;
        timeParser = topTimeParser;
    }

    public TOPDataParser getDataParser() {
        return dataParser;
    }

    public TOPTimeParser getTimeParser() {
        return timeParser;
    }
}
