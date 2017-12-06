package ru.naumen.sd40.log.parser.DataSets;

import org.springframework.stereotype.Service;
import ru.naumen.sd40.log.parser.Interfaces.IDataSet;
import ru.naumen.sd40.log.parser.Storage.ActionDoneStorage;
import ru.naumen.sd40.log.parser.Storage.ErrorStorage;
import ru.naumen.sd40.log.parser.Storage.TOPStorage;

@Service
public class TOPDataSet implements IDataSet<TOPStorage> {

    TOPStorage storage;
    ActionDoneStorage actionDoneStorage;
    ErrorStorage errorStorage;

    public TOPDataSet(){
        this.storage = new TOPStorage();
        this.actionDoneStorage = new ActionDoneStorage();
        this.errorStorage = new ErrorStorage();
    }

    @Override
    public TOPStorage getStorage() {
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
