package ru.naumen.sd40.log.parser.DataSets;

import org.springframework.stereotype.Service;
import ru.naumen.sd40.log.parser.Interfaces.IDataSet;

@Service
public class DataSetFactory {

    public IDataSet createDataSet(String mode) {
        switch (mode){
            case "sdng":
                return new SDNGDataSet();
            case "top":
                return new TOPDataSet();
            case "gc":
                return new GCDataSet();
            default:
               return new SDNGDataSet();
        }
    }
}
