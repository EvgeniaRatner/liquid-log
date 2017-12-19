package ru.naumen.sd40.log.parser.Parser;

import org.influxdb.dto.BatchPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumen.perfhouse.influx.InfluxDAO;
import ru.naumen.sd40.log.parser.DataSets.DataSetFactory;
import ru.naumen.sd40.log.parser.DataSets.SDNGDataSet;
import ru.naumen.sd40.log.parser.Interfaces.IDataSet;
import ru.naumen.sd40.log.parser.Parsers.ParserFactory;
import ru.naumen.sd40.log.parser.Interfaces.IDataParser;
import ru.naumen.sd40.log.parser.Interfaces.IStorage;
import ru.naumen.sd40.log.parser.Interfaces.ITimeParser;
import ru.naumen.sd40.log.parser.Storages.ActionDoneStorage;
import ru.naumen.sd40.log.parser.Storages.ErrorStorage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;


@Service
public class LogParser {

    Properties property;
    InfluxDAO db;
    BatchPoints points;
    IDataParser dataParser;
    ITimeParser timeParser;
    IStorage storage;
    DataSetFactory dataSetFactory;
    ParserFactory parserFactory;
    HashMap<Long, IDataSet> data;
    InfluxDAO finalStorage;
    String finalInfluxDb;
    Settings settings;

    @Autowired
    public LogParser(Settings settings, DataSetFactory dataSetFactory, ParserFactory parserFactory){
        this.settings = settings;
        this.dataSetFactory = dataSetFactory;
        this.parserFactory = parserFactory;
    };


    public void parseLog(String filePath, String influxDb, String mode, boolean trace, String timeZone )
            throws Exception {

        if (influxDb != null) {
            if (timeZone == null) timeZone = "";
            property = settings.loadProperties();
            db = settings.initDb(property, influxDb);
            data = new HashMap<>();
            finalStorage = db;
            finalInfluxDb = influxDb;
            points = null;
            parserFactory.setFileName(filePath);
            parserFactory.setTimeZone(timeZone);
            parserFactory.setMode(mode);
            parserFactory.create();
            dataParser = parserFactory.getDataParser();
            timeParser = parserFactory.getTimeParser();

        } else throw new Exception("Не указана база данных");

        if (db != null) {
            points = db.startBatchPoints(influxDb);
        }
        BatchPoints finalPoints = points;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                long time = timeParser.parseTime(line);

                if (time == 0) {
                    continue;
                }

                int min5 = 5 * 60 * 1000;
                long count = time / min5;
                long key = count * min5;

                data.computeIfAbsent(key, k -> dataSetFactory.createDataSet(mode));
                dataParser.parseLine(storage, line);
            }
        }

        if (trace) {
            System.out.print("Timestamp;Actions;Min;Mean;Stddev;50%%;95%%;99%%;99.9%%;Max;Errors\n");
        }
        data.forEach((k, set) ->
        {
            if (mode == "sdng") {
                SDNGDataSet ds = (SDNGDataSet)set;
                ActionDoneStorage dones = ds.getStorage().actionDone;
                dones.calculate();
                ErrorStorage erros = ds.getStorage().error;
                if (trace) {
                    System.out.print("Timestamp;Actions;Min;Mean;Stddev;50%%;95%%;99%%;99.9%%;Max;Errors\n");
                    System.out.print(String.format("%d;%d;%f;%f;%f;%f;%f;%f;%f;%f;%d\n", k, dones.count,
                            dones.min, dones.mean, dones.stddev, dones.percent50, dones.percent95,
                            dones.percent99, dones.percent999, dones.max, erros.errorCount));
                }
            }
           parserFactory.store(finalStorage, finalPoints, finalInfluxDb, k, storage);
        });
        db.writeBatch(points);
    }
}

