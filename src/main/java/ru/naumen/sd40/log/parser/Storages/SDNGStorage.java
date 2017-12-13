package ru.naumen.sd40.log.parser.Storages;


import ru.naumen.sd40.log.parser.Interfaces.IStorage;

public class SDNGStorage implements IStorage {

    public ActionDoneStorage actionDone;
    public ErrorStorage error;

    public SDNGStorage(ActionDoneStorage actionDoneStorage, ErrorStorage errorStorage) {
        this.actionDone = actionDoneStorage;
        this.error =  errorStorage;
    }
}
