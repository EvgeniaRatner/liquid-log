package ru.naumen.sd40.log.parser.Parser;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.springframework.stereotype.Service;
import ru.naumen.perfhouse.influx.InfluxDAO;
import ru.naumen.sd40.log.parser.DataParsers.GCDataParser;
import ru.naumen.sd40.log.parser.DataParsers.SDNGDataParser;
import ru.naumen.sd40.log.parser.DataParsers.TOPDataParser;
import ru.naumen.sd40.log.parser.DataSets.GCDataSet;
import ru.naumen.sd40.log.parser.DataSets.SDNGDataSet;
import ru.naumen.sd40.log.parser.DataSets.TOPDataSet;
import ru.naumen.sd40.log.parser.Interfaces.IStorage;
import ru.naumen.sd40.log.parser.Storage.*;
import ru.naumen.sd40.log.parser.TimeParsers.GCTimeParser;
import ru.naumen.sd40.log.parser.TimeParsers.SDNGTimeParser;
import ru.naumen.sd40.log.parser.TimeParsers.TOPTimeParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

@Service
public class Settings {

    public Processor initProcessor(String timezone){
        Processor processor = new Processor();
        processor.registerParsingMode("sdng", SDNGDataParser.class, SDNGTimeParser.class,
                SDNGStorage.class, "storeActionsFromLog", SDNGDataSet.class);
        processor.registerParsingMode("top", TOPDataParser.class, TOPTimeParser.class,
                TOPStorage.class, "storeTop", TOPDataSet.class);
        processor.registerParsingMode("gc", GCDataParser.class, GCTimeParser.class,
                GCStorage.class, "storeGc", GCDataSet.class);
        return processor;
    }

    public Properties loadProperties() throws IOException {
        FileInputStream fis;
        Properties property = new Properties();
        fis = new FileInputStream("src/main/resources/application.properties");
        property.load(fis);
        return property;
    }

    public InfluxDAO initDb(Properties property, String influxDb){
        influxDb.replaceAll("-", "_");
        InfluxDAO storage = new InfluxDAO(property.getProperty("influx.host"), property.getProperty("influx.user"),
                property.getProperty("influx.password"));
        storage.init();
        storage.connectToDB(influxDb);
        return storage;
    }
}

