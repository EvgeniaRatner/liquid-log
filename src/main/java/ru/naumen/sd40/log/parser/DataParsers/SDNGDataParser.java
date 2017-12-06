package ru.naumen.sd40.log.parser.DataParsers;

import org.springframework.stereotype.Service;
import ru.naumen.sd40.log.parser.ActionDoneParser;
import ru.naumen.sd40.log.parser.ErrorParser;
import ru.naumen.sd40.log.parser.Interfaces.IDataParser;
import ru.naumen.sd40.log.parser.Interfaces.IStorage;
import ru.naumen.sd40.log.parser.Storage.SDNGStorage;

@Service
public class SDNGDataParser implements IDataParser<SDNGStorage> {

    private ErrorParser errorParser = new ErrorParser();
    private ActionDoneParser actionDoneParser = new ActionDoneParser();

    public void parseLine(String line, SDNGStorage storage)
    {
        errorParser.parseLine(line, storage.errorStorage);
        actionDoneParser.parseLine( line, storage.actionDoneStorage);
    }

}

