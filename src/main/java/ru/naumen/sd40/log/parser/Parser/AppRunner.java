package ru.naumen.sd40.log.parser.Parser;


import org.influxdb.dto.BatchPoints;
import org.springframework.stereotype.Service;
import ru.naumen.perfhouse.influx.InfluxDAO;
import ru.naumen.sd40.log.parser.Interfaces.IDataParser;
import ru.naumen.sd40.log.parser.Interfaces.IStorage;
import ru.naumen.sd40.log.parser.Interfaces.ITimeParser;


import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

@Service
public class AppRunner {

    Properties property;
    Registrator registrator;
    InfluxDAO dao;
    InfluxDAO finalStorage;
    String finalInfluxDb;
    BatchPoints points;
    IDataParser dataParser;
    ITimeParser timeParser;
    IStorage storage;
    Settings settings;
    long time;
    HashMap<Long, IStorage> data = new HashMap<>();

    public AppRunner(Registrator registrator, Settings settings) {
        this.registrator = registrator;
        this.settings = settings;
    }


    public void run(String filePath, String influxDb, String mode, boolean trace, String timeZone)
            throws Exception {

        if (influxDb != null) {
            Settings settings = new Settings();
            if (timeZone == null) timeZone = "";
            property = settings.loadProperties();
            dao = settings.initDb(property, influxDb);
            settings.register(registrator, timeZone, filePath);
            dataParser = registrator.getDataParser(mode);
            timeParser = registrator.getTimeParser(mode);
            finalStorage = dao;
            finalInfluxDb = influxDb;

            if (dao != null)
            {
                points = dao.startBatchPoints(influxDb);
            }
        } else throw new Exception("Не указана база данных");


        try (BufferedReader br = new BufferedReader(new FileReader(filePath), 32 * 1024 * 1024))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                time = timeParser.parseTime(line);

                if (time == 0)
                {
                    continue;
                }

                int min5 = 5 * 60 * 1000;
                long count = time / min5;
                long key = count * min5;

                storage = data.computeIfAbsent(key, k -> registrator.getStorage(mode));
                dataParser.parseLine(storage, line);
            }
        }

        if (trace)
        {
            System.out.print("Timestamp;Actions;Min;Mean;Stddev;50%%;95%%;99%%;99.9%%;Max;Errors\n");
        }
        BatchPoints finalPoints = points;
        data.forEach((k, set) ->
        {
            try {
                Method save = InfluxDAO.class.getMethod(registrator.getSaveMethod(mode));
                save.invoke(finalPoints, finalInfluxDb, time, storage );
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        dao.writeBatch(points);
    }
}
