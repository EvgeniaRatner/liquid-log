package ru.naumen.sd40.log.parser.Parser;
import org.springframework.stereotype.Service;
import ru.naumen.sd40.log.parser.Interfaces.IDataParser;
import ru.naumen.sd40.log.parser.Interfaces.IDataSet;
import ru.naumen.sd40.log.parser.Interfaces.IStorage;
import ru.naumen.sd40.log.parser.Interfaces.ITimeParser;

import java.util.HashMap;


@Service
public class Registrator {

    public HashMap<String, IDataParser> dataParsers;
    public HashMap<String, ITimeParser> timeParsers;
    public HashMap<String, IStorage> storages;
    public HashMap<String, String> saveMethods;

    public Registrator(){
        dataParsers = new HashMap<>();
        timeParsers = new HashMap<>();
        storages = new HashMap<>();
    }

    public void registerParsingMode(String mode,
                                    IDataParser dataParser,
                                    ITimeParser timeParser,
                                    IStorage storage,
                                    String saveMethod){
        dataParsers.put(mode, dataParser);
        timeParsers.put(mode, timeParser);
        storages.put(mode, storage);
        saveMethods.put(mode, saveMethod);
    }

    public IDataParser getDataParser(String mode){
        return dataParsers.get(mode);
    }

    public ITimeParser getTimeParser(String mode){
        return timeParsers.get(mode);
    }

    public IStorage getStorage(String mode){
        return storages.get(mode);
    }

    public String getSaveMethod(String mode){return saveMethods.get(mode);}

}
