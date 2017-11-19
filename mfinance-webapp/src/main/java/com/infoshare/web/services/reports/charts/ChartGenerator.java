package com.infoshare.web.services.reports.charts;

import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshare.mfinance.core.analyzer.trend.QuotationSeries;
import com.infoshare.mfinance.core.models.analyses.criteria.QuotationSeriesCriteria;
import com.infoshare.mfinance.core.models.analyses.results.QuotationSeriesResult;
import com.infoshare.mfinance.core.models.bossa.Quotation;
import com.infoshare.web.services.bossa.IDataContainerService;
import org.jfree.chart.ChartFactory;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class ChartGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChartGenerator.class);

    private String chartTitle;
    private IDataContainerService mainContainer;
    private QuotationSeriesCriteria criteria;

    public ChartGenerator(IDataContainerService mainContainer, QuotationSeriesCriteria criteria) {
        this.mainContainer = mainContainer;
        this.criteria = criteria;
    }

    public JFreeChart renderChart() throws NoDataForCriteria {

        final XYDataset dataset = createDataset();
        return createXYChart(dataset, chartTitle);
    }

    private XYDataset createDataset() throws NoDataForCriteria {

        QuotationSeries quotationSeries = new QuotationSeries(mainContainer.getDataContainer(), criteria);
        QuotationSeriesResult quotationSeriesResult = (QuotationSeriesResult) quotationSeries.getResult();
        List<Quotation> quotations = quotationSeriesResult.getQuotationList();

        this.chartTitle = getChartTitle(criteria);
        BigDecimal value;
        final TimeSeries series = new TimeSeries("InvestmentQuotations");

        for (Quotation item : quotations) {
            try {
                value = item.getClose();
                Day current = new Day(item.getDate().getDayOfMonth(),
                        item.getDate().getMonthValue(),
                        item.getDate().getYear());
                series.add(current, value);

                LOGGER.info("adding current date to series {}-{}-{} {}", current.getDayOfMonth()
                        , current.getMonth()
                        , current.getYear(), value);

            } catch (SeriesException e) {
                LOGGER.error("Failed to generate series");
            }
        }
        return new TimeSeriesCollection(series);
    }

    private String getChartTitle(QuotationSeriesCriteria criteria){
        StringBuilder sb = new StringBuilder();
        sb.append(criteria.getInvestmentName());
        sb.append(" ");
        sb.append(criteria.getStartDate());
        sb.append(" - ");
        sb.append(criteria.getEndDate());
        return sb.toString();
    }

    private JFreeChart createXYChart(final XYDataset dataset, String title){
        return ChartFactory.createTimeSeriesChart(
                this.chartTitle, "", "PLN",
                dataset, false, false, false);
    }
}


