package ru.naumen.sd40.log.parser.Storages;

import org.springframework.stereotype.Service;
import ru.naumen.sd40.log.parser.Interfaces.IStorage;

@Service
public class ErrorStorage implements IStorage {

    public long warnCount;
    public long errorCount;
    public long fatalCount;
}
