package ru.naumen.sd40.log.parser.DataSets;

import ru.naumen.sd40.log.parser.Interfaces.IDataSet;
import ru.naumen.sd40.log.parser.Storage.ActionDoneStorage;
import ru.naumen.sd40.log.parser.Storage.ErrorStorage;
import ru.naumen.sd40.log.parser.Storage.GCStorage;

public class GCDataSet implements IDataSet<GCStorage>{


    GCStorage storage;
    ActionDoneStorage actionDoneStorage;
    ErrorStorage errorStorage;

    public GCDataSet(){
        this.storage = new GCStorage();
        this.actionDoneStorage = new ActionDoneStorage();
        this.errorStorage = new ErrorStorage();
    }

    @Override
    public GCStorage getStorage() {
        return storage;
    }

    @Override
    public ErrorStorage getErrorStorage() {
        return errorStorage;
    }

    @Override
    public ActionDoneStorage getActionDoneStorage() {
        return actionDoneStorage;
    }
}
