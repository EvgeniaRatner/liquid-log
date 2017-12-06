package ru.naumen.sd40.log.parser.Interfaces;

import ru.naumen.sd40.log.parser.Storage.ActionDoneStorage;
import ru.naumen.sd40.log.parser.Storage.ErrorStorage;

public interface IDataSet<T extends IStorage> {

    public T getStorage();
    public ActionDoneStorage getActionDoneStorage();
    public ErrorStorage getErrorStorage();
}
