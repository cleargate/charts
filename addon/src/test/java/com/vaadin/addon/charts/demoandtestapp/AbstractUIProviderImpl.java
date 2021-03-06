/*
 * Copyright 2012 Vaadin Community.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vaadin.addon.charts.demoandtestapp;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 *
 */
@SuppressWarnings("serial")
public class AbstractUIProviderImpl extends UIProvider {

    @WebServlet(value = "/*", asyncSupported = true, initParams = {
            @WebInitParam(name = "heartbeatInterval", value = "10"),
            @WebInitParam(name = "widgetset", value = "com.vaadin.addon.charts.ChartsWithTimelineWidgetset"),
            @WebInitParam(name = "UIProvider", value = "com.vaadin.addon.charts.demoandtestapp.AbstractUIProviderImpl") })
    public static class Servlet extends VaadinServlet {
    }

    @Override
    public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
        String name = (event.getRequest()).getPathInfo();
        if (name.startsWith("/")) {
            name = name.substring(1);
        }
        if (!"".equals(name) && !name.contains(".ico")
                && name.matches("[A-Za-z/].*")) {
            return TestUI.class;
        }
        return TListUi.class;
    }

}
