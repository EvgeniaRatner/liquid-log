package ru.naumen.sd40.log.parser.DataParsers;

import org.springframework.stereotype.Service;
import ru.naumen.sd40.log.parser.Interfaces.IDataParser;
import ru.naumen.sd40.log.parser.Storages.SDNGStorage;

@Service
public class SDNGDataParser implements IDataParser<SDNGStorage>{

    private ActionDoneParser actionDoneParser = new ActionDoneParser();
    private ErrorParser errorParser = new ErrorParser();

    public void parseLine(SDNGStorage storage, String line) {
        errorParser.parseLine(storage.error, line);
        actionDoneParser.parseLine(storage.actionDone,line);
    }
}
