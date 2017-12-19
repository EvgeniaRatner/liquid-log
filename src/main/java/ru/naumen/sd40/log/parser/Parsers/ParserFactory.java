package ru.naumen.sd40.log.parser.Parsers;

import org.influxdb.dto.BatchPoints;
import org.springframework.stereotype.Service;
import ru.naumen.perfhouse.influx.InfluxDAO;
import ru.naumen.sd40.log.parser.DataParsers.GCDataParser;
import ru.naumen.sd40.log.parser.DataParsers.SDNGDataParser;
import ru.naumen.sd40.log.parser.DataParsers.TOPDataParser;
import ru.naumen.sd40.log.parser.Interfaces.IDataParser;
import ru.naumen.sd40.log.parser.Interfaces.IStorage;
import ru.naumen.sd40.log.parser.Interfaces.ITimeParser;
import ru.naumen.sd40.log.parser.Storages.GCStorage;
import ru.naumen.sd40.log.parser.Storages.SDNGStorage;
import ru.naumen.sd40.log.parser.Storages.TOPStorage;
import ru.naumen.sd40.log.parser.TimeParsers.GCTimeParser;
import ru.naumen.sd40.log.parser.TimeParsers.SDNGTimeParser;
import ru.naumen.sd40.log.parser.TimeParsers.TOPTimeParser;
import java.util.HashMap;


@Service
public class ParserFactory{

    private IDataParser dataParser;
    private ITimeParser timeParser;
    private String mode;
    private String fileName;
    private String timeZone;
    private HashMap<String, IDataParser> dataParsers;
    private HashMap<String, ITimeParser> timeParsers;


    public ParserFactory(SDNGDataParser sdngDataParser, SDNGTimeParser sdngTimeParser,
                         GCDataParser gcDataParser, GCTimeParser gcTimeParser,
                         TOPDataParser topDataParser, TOPTimeParser topTimeParser)
    {
        dataParsers = new HashMap<>();
        timeParsers = new HashMap<>();

        dataParsers.put("sdng", sdngDataParser);
        dataParsers.put("gc", gcDataParser);
        dataParsers.put("top", topDataParser);

        timeParsers.put("sdng", sdngTimeParser);
        timeParsers.put("gc", gcTimeParser);
        timeParsers.put("top", topTimeParser);
    }

    public void store(InfluxDAO finalStorage, BatchPoints points, String finalInfluxDb, long date, IStorage storage){
        switch (mode)
        {
            case "sdng":
                finalStorage.storeActionsFromLog(points, finalInfluxDb, date, (SDNGStorage) storage);
                break;
            case "gc":
                finalStorage.storeGc(points, finalInfluxDb, date, (GCStorage) storage);
                break;
            case "top":
                finalStorage.storeTop(points, finalInfluxDb, date, (TOPStorage) storage);
                break;
        }
    }

    public void create()
    {
        dataParser = dataParsers.get(mode);
        timeParser = timeParsers.get(mode);
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public void setTimeZone(String timeZone){
        this.timeZone = timeZone;
    }

    public void setMode(String mode){
        this.mode = mode;
    }

    public IDataParser getDataParser(){
        return dataParser;
    }

    public ITimeParser getTimeParser() {
        return timeParser;
    }
}
