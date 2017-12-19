package ru.naumen.sd40.log.parser.DataSets;

import ru.naumen.sd40.log.parser.Interfaces.IDataSet;
import ru.naumen.sd40.log.parser.Storages.ActionDoneStorage;
import ru.naumen.sd40.log.parser.Storages.ErrorStorage;
import ru.naumen.sd40.log.parser.Storages.SDNGStorage;

public class SDNGDataSet implements IDataSet<SDNGStorage> {

    SDNGStorage storage;

    public SDNGDataSet(){
        this.storage = new SDNGStorage(new ActionDoneStorage(), new ErrorStorage());
    }

    public SDNGStorage getStorage() {
        return storage;
    }

}
