package ru.naumen.sd40.log.parser.Interfaces;

public interface IDataParser<T extends IStorage> {
    void parseLine(String line, T storage);
}
