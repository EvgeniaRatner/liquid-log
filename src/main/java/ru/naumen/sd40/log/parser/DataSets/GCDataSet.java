package ru.naumen.sd40.log.parser.DataSets;

import ru.naumen.sd40.log.parser.Interfaces.IDataSet;
import ru.naumen.sd40.log.parser.Storages.ActionDoneStorage;
import ru.naumen.sd40.log.parser.Storages.ErrorStorage;
import ru.naumen.sd40.log.parser.Storages.GCStorage;

public class GCDataSet implements IDataSet<GCStorage>{


    GCStorage storage;

    public GCDataSet(){
        this.storage = new GCStorage();
    }

    public GCStorage getStorage() {
        return storage;
    }
}
