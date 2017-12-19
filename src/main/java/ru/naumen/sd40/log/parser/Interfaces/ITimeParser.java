package ru.naumen.sd40.log.parser.Interfaces;

import java.text.ParseException;

public interface ITimeParser {

    public long parseTime(String line) throws ParseException;
}
