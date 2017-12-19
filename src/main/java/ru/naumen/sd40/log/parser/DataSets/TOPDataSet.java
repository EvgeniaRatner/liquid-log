package ru.naumen.sd40.log.parser.DataSets;

import org.springframework.stereotype.Service;
import ru.naumen.sd40.log.parser.Interfaces.IDataSet;
import ru.naumen.sd40.log.parser.Storages.TOPStorage;

@Service
public class TOPDataSet implements IDataSet<TOPStorage> {

    TOPStorage storage;

    public TOPDataSet(){
        this.storage = new TOPStorage();
    }

    public TOPStorage getStorage() {
        return storage;
    }
}
