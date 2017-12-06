package ru.naumen.sd40.log.parser.DataSets;

import ru.naumen.sd40.log.parser.Interfaces.IDataSet;
import ru.naumen.sd40.log.parser.Storage.ActionDoneStorage;
import ru.naumen.sd40.log.parser.Storage.ErrorStorage;
import ru.naumen.sd40.log.parser.Storage.SDNGStorage;

public class SDNGDataSet implements IDataSet<SDNGStorage> {

    SDNGStorage storage;
    ActionDoneStorage actionDoneStorage;
    ErrorStorage errorStorage;

    public SDNGDataSet(){
        this.storage = new SDNGStorage();
        this.actionDoneStorage = new ActionDoneStorage();
        this.errorStorage = new ErrorStorage();
    }

    @Override
    public SDNGStorage getStorage() {
        return storage;
    }

    @Override
    public ActionDoneStorage getActionDoneStorage() {
        return actionDoneStorage;
    }

    @Override
    public ErrorStorage getErrorStorage() {
        return errorStorage;
    }
}
