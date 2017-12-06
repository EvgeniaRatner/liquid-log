package ru.naumen.sd40.log.parser.Storage;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import ru.naumen.sd40.log.parser.Interfaces.IStorage;

public class TOPStorage implements IStorage {

    public DescriptiveStatistics laStat = new DescriptiveStatistics();
    public DescriptiveStatistics cpuStat = new DescriptiveStatistics();
    public DescriptiveStatistics memStat = new DescriptiveStatistics();

    public void addLa(double la)
    {
        this.laStat.addValue(la);
    }

    public void addCpu(double cpu)
    {
        this.cpuStat.addValue(cpu);
    }

    public void addMem(double mem)
    {
        this.memStat.addValue(mem);
    }

}
