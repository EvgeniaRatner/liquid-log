package ru.naumen.sd40.log.parser.Storage;

import ru.naumen.sd40.log.parser.Interfaces.IStorage;

public class SDNGStorage implements IStorage {

    public ErrorStorage errorStorage = new ErrorStorage();
    public ActionDoneStorage actionDoneStorage = new ActionDoneStorage();

}
