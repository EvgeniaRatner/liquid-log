package ru.naumen.sd40.log.parser.Parser;

import org.influxdb.dto.BatchPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumen.perfhouse.influx.InfluxDAO;
import ru.naumen.sd40.log.parser.DataSets.DataSetFactory;
import ru.naumen.sd40.log.parser.Interfaces.IDataSet;
import ru.naumen.sd40.log.parser.Storage.*;
import ru.naumen.sd40.log.parser.Interfaces.IDataParser;
import ru.naumen.sd40.log.parser.Interfaces.IStorage;
import ru.naumen.sd40.log.parser.Interfaces.ITimeParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;


@Service
public class LogParser {

    Properties property;
    Processor processor;
    InfluxDAO db;
    BatchPoints points;
    IDataParser dataParser;
    ITimeParser timeParser;
    IStorage storage;
    HashMap<Long, IDataSet> data;
    InfluxDAO finalStorage;
    String finalInfluxDb;
    Settings settings;
    DataSetFactory dataSetFactory;

    @Autowired
    public LogParser(Settings settings, DataSetFactory dataSetFactory){
        this.settings = settings;
        this.dataSetFactory = dataSetFactory;
    };


    public void parseLog(String filePath, String influxDb, String mode, boolean trace, String timeZone )
            throws Exception {

        if (influxDb != null) {
            if (timeZone == null) timeZone = "";
            processor = settings.initProcessor(timeZone);
            property = settings.loadProperties();
            dataParser = (IDataParser) processor.dataParsers.get(mode).newInstance();
            timeParser = (ITimeParser) processor.timeParsers.get(mode).newInstance();
            storage = (IStorage) processor.storages.get(mode).newInstance();
            data = new HashMap<>();
            finalStorage = db;
            finalInfluxDb = influxDb;
            points = null;
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
                dataParser.parseLine(line, storage);
            }
        }

        if (trace) {
            System.out.print("Timestamp;Actions;Min;Mean;Stddev;50%%;95%%;99%%;99.9%%;Max;Errors\n");
        }
        data.forEach((k, set) ->
        {
            ActionDoneStorage dones = set.getActionDoneStorage();
            dones.calculate();
            ErrorStorage erros = set.getErrorStorage();
            if (trace) {
                System.out.print("Timestamp;Actions;Min;Mean;Stddev;50%%;95%%;99%%;99.9%%;Max;Errors\n");
                System.out.print(String.format("%d;%d;%f;%f;%f;%f;%f;%f;%f;%f;%d\n", k, dones.count,
                        dones.min, dones.mean, dones.stddev, dones.percent50, dones.percent95,
                        dones.percent99, dones.percent999, dones.max, erros.errorCount));
            }

            switch (mode) {
                case ("sdng"):
                    finalStorage.storeActionsFromLog(finalPoints, finalInfluxDb, k, dones, erros);
                    break;
                case ("gc"):
                    finalStorage.storeGc(finalPoints, finalInfluxDb, k, (GCStorage) storage);
                    break;
                case ("top"):
                    finalStorage.storeTop(finalPoints, finalInfluxDb, k, (TOPStorage) storage);
                    break;
            }
        });
            db.writeBatch(points);
    }
}

