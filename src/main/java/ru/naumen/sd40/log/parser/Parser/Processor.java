package ru.naumen.sd40.log.parser.Parser;

import ru.naumen.sd40.log.parser.Interfaces.IDataParser;
import ru.naumen.sd40.log.parser.Interfaces.IStorage;
import ru.naumen.sd40.log.parser.Interfaces.ITimeParser;

import java.util.ArrayList;
import java.util.HashMap;

public class Processor {

    public HashMap<String, Class> dataParsers;
    public HashMap<String, Class> timeParsers;
    public HashMap<String, Class> storages;
    public HashMap<String, String> saveMethodNames;
    public HashMap<String, Class> dataSets;

    public String timezone;


    public Processor(){
        dataParsers = new HashMap<>();
        timeParsers = new HashMap<>();
        storages = new HashMap<>();
        saveMethodNames = new HashMap<>();
        dataSets = new HashMap<>();
    }

    public void registerParsingMode(String mode,
                                    Class dataParser,
                                    Class timeParser,
                                    Class storage,
                                    String methodName,
                                    Class dataSet){
        dataParsers.put(mode, dataParser);
        timeParsers.put(mode, timeParser);
        storages.put(mode, storage);
        saveMethodNames.put(mode, methodName);
        dataSets.put(mode, dataSet);
    }

}
