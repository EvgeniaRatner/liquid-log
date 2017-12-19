package ru.naumen.sd40.log.parser.Parser;

import org.springframework.stereotype.Service;
import ru.naumen.perfhouse.influx.InfluxDAO;

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
}
