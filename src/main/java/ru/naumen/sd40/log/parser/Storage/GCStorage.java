package ru.naumen.sd40.log.parser.Storage;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import ru.naumen.sd40.log.parser.Interfaces.IStorage;

public class GCStorage implements IStorage {

    public DescriptiveStatistics ds = new DescriptiveStatistics();

    public void addValue(double value)
    {
        ds.addValue(value);
    }


}
