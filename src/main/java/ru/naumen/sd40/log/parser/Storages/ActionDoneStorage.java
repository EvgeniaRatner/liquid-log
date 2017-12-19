package ru.naumen.sd40.log.parser.Storages;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Service;
import ru.naumen.sd40.log.parser.Interfaces.IStorage;

import java.util.ArrayList;
import java.util.Set;

public class ActionDoneStorage implements IStorage {

    public Set<String> EXCLUDED_ACTIONS;
    public ArrayList<Integer> times = new ArrayList<>();
    public double min;
    public double mean;
    public double stddev;
    public double percent50;
    public double percent95;
    public double percent99;
    public double percent999;
    public double max;
    public long count;

    public int addObjectActions = 0;
    public int editObjectsActions = 0;
    public int getCatalogsAction = 0;
    public int getListActions = 0;
    public int commentActions = 0;
    public int getFormActions = 0;
    public int getDtObjectActions = 0;
    public int searchActions = 0;

    public void calculate()
    {
        DescriptiveStatistics ds = new DescriptiveStatistics();
        times.forEach(ds::addValue);
        min = ds.getMin();
        mean = ds.getMean();
        stddev = ds.getStandardDeviation();
        percent50 = ds.getPercentile(50.0);
        percent95 = ds.getPercentile(95.0);
        percent99 = ds.getPercentile(99.0);
        percent999 = ds.getPercentile(99.9);
        max = ds.getMax();
        count = ds.getN();
    }
}
