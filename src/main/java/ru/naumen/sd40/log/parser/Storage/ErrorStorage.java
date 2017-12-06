package ru.naumen.sd40.log.parser.Storage;

import ru.naumen.sd40.log.parser.Interfaces.IStorage;

public class ErrorStorage implements IStorage{

    public long warnCount;
    public long errorCount;
    public long fatalCount;
}
