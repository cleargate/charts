package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Element;

public class HighchartConfig extends JavaScriptObject {

    protected HighchartConfig() {
    };

    public static HighchartConfig createFromServerSideString(String jsonstr) {
        HighchartConfig conf = (HighchartConfig) JSONParser
                .parseLenient(jsonstr).isObject().getJavaScriptObject();
        conf.prepare();
        return conf;
    }

    /**
     * Searches all fields with _fn_ prefix and generates new field without
     * prefix with eval around the string code.
     */
    private final native void searchAndEvalFields()
    /*-{
        var re = /( |\+)(Highcharts\.)/g;
        var re_fn_prop = /^_fn_/;
        (function recursiveFunction(obj) {
            for (var prop in obj) {
                if (obj.hasOwnProperty(prop)) {
                    var childobj = obj[prop];
                    if (prop.indexOf("_fn_") == 0) {
                        try {
                            var script = childobj;
                            var actualPropName = prop.replace(re_fn_prop, '');
                            script = script.replace(re, "$1\$wnd.$2");
                            if(script.indexOf("function()") != 0) {
                                if(script.indexOf("return") != 0) {
                                    script = "return " + script;
                                }
                                script = "function() {" + script + "}";
                            }
                            obj[actualPropName] = eval('(' + script + ')');
                            obj[prop] = null;
                        } catch (e) {
                            // TODO report on VConsole before release
                            alert("Failed to evaluate formatter");
                            alert(e);
                        }
                    } else {
                        if(typeof childobj == 'object' && childobj != null) {
                            recursiveFunction(childobj);
                        }
                    }
                }
            }
        })(this);
    }-*/;

    private void prepare() {
        searchAndEvalFields();
    }

    public final native void setClickHandler(ChartClickHandler handler)
    /*-{
        if(!this.chart) this.chart = {};
        if(!this.chart.events) this.chart.events = {};
        this.chart.events.click = function(e) {
            $entry(handler.@com.vaadin.addon.charts.client.ui.ChartClickHandler::onClick(Lcom/vaadin/addon/charts/client/ui/ChartClickEvent;)(e));
        };
    }-*/;

    public final native void setSeriesPointClickHandler(
            PointClickHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.series.point.events");
        this.plotOptions.series.point.events.click = function(e) {
            $entry(handler.@com.vaadin.addon.charts.client.ui.PointClickHandler::onClick(Lcom/vaadin/addon/charts/client/ui/PointClickEvent;)(e));
        };
    }-*/;

    public final native void setColumnClickHandler(PointClickHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.column.point.events");
        this.plotOptions.series.point.events.click = function(e) {
            $entry(handler.@com.vaadin.addon.charts.client.ui.PointClickHandler::onClick(Lcom/vaadin/addon/charts/client/ui/PointClickEvent;)(e));
        };
    }-*/;

    public final native void setChartSelectionHandler(
            ChartSelectionHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"chart.events");
        this.chart.events.selection = function(e) {
            $entry(handler.@com.vaadin.addon.charts.client.ui.ChartSelectionHandler::onSelection(Lcom/vaadin/addon/charts/client/ui/ChartSelectionEvent;)(e));
        };
    }-*/;

    public static final native void ensureObjectStructure(JavaScriptObject obj,
            String path)
    /*-{
     var parts = path.split(".");
        for (var i=0; i<parts.length; i++) {
            if (!obj[parts[i]]) obj[parts[i]] = {};
            obj = obj[parts[i]];
        }
    }-*/;

    public native final HighchartJsOverlay renderTo(Element element)
    /*-{
        if(!this.chart) this.chart = {};
        this.chart.renderTo = element;
        try {
            return new $wnd.Highcharts.Chart(this);
        } catch(e) {
            // debugger;
        }
    }-*/;

}