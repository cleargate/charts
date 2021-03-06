package com.vaadin.addon.charts.demoandtestapp.pie;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class PieWithStartAndEndAngle extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Pie with start angle 45 and end angle 180";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Browser market shares at a specific website, 2010");

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setStartAngle(45);
        plotOptions.setEndAngle(180);
        plotOptions.setCursor(Cursor.POINTER);
        Labels dataLabels = new Labels(true);
        dataLabels
                .setFormatter("'<b>'+ this.point.name +'</b>: '+ this.percentage +' %'");
        plotOptions.setDataLabels(dataLabels);
        conf.setPlotOptions(plotOptions);

        conf.setSeries(getBrowserMarketShareSeries());

        chart.drawChart();
        return chart;
    }

    private DataSeries getBrowserMarketShareSeries() {
        DataSeriesItem firefox = new DataSeriesItem("Firefox", 45.0);

        DataSeriesItem ie = new DataSeriesItem("IE", 26.8);

        DataSeriesItem chrome = new DataSeriesItem("Chrome", 12.8);
        chrome.setSliced(true);
        chrome.setSelected(true);

        DataSeriesItem safari = new DataSeriesItem("Safari", 8.5);

        DataSeriesItem opera = new DataSeriesItem("Opera", 6.2);

        DataSeriesItem others = new DataSeriesItem("Others", 0.7);

        return new DataSeries(firefox, ie, chrome, safari, opera, others);
    }

}
