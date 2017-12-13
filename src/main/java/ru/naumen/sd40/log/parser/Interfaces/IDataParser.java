package ru.naumen.sd40.log.parser.Interfaces;

public interface IDataParser<T extends IStorage> {
    public void parseLine(T storage, String line );
}
