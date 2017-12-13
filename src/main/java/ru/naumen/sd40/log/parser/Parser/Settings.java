package ru.naumen.sd40.log.parser.Parser;

import org.springframework.stereotype.Service;
import ru.naumen.perfhouse.influx.InfluxDAO;
import ru.naumen.sd40.log.parser.DataParsers.GCDataParser;
import ru.naumen.sd40.log.parser.DataParsers.SDNGDataParser;
import ru.naumen.sd40.log.parser.DataParsers.TOPDataParser;
import ru.naumen.sd40.log.parser.Storages.*;
import ru.naumen.sd40.log.parser.TimeParsers.GCTimeParser;
import ru.naumen.sd40.log.parser.TimeParsers.SDNGTimeParser;
import ru.naumen.sd40.log.parser.TimeParsers.TOPTimeParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class Settings {

    public Properties loadProperties() throws IOException {
        FileInputStream fis;
        Properties property = new Properties();
        fis = new FileInputStream("src/main/resources/application.properties");
        property.load(fis);
        return property;
    }

    public InfluxDAO initDb(Properties property, String db){
        db = db.replaceAll("-", "_");
        InfluxDAO storage = null;
        storage = new InfluxDAO(property.getProperty("influx.host"), property.getProperty("influx.user"),
                property.getProperty("influx.password"));
        storage.init();
        storage.connectToDB(db);
        return storage;
    }

    public void register(Registrator registrator, String timezone, String filename){
        registrator.registerParsingMode("sdng",
                new SDNGDataParser(),
                new SDNGTimeParser(timezone),
                new SDNGStorage(new ActionDoneStorage(), new ErrorStorage()),
                "storeActionsFromLog");

        registrator.registerParsingMode("gc",
                new GCDataParser(),
                new GCTimeParser(timezone),
                new GCStorage(),
                "storeGc");

        registrator.registerParsingMode("top",
                new TOPDataParser(),
                new TOPTimeParser(filename, timezone),
                new TOPStorage(),
                "storeTop");
    }
}
