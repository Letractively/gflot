package com.googlecode.gflot.examples.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.googlecode.gflot.examples.client.examples.simple.SimplePlace;
import com.googlecode.gflot.examples.client.mvp.AppActivityMapper;
import com.googlecode.gflot.examples.client.mvp.AppPlaceHistoryMapper;
import com.googlecode.gflot.examples.client.resources.Resources;

public class GFlotExamples
    implements EntryPoint
{
    public void onModuleLoad()
    {
        Resources resources = GWT.create( Resources.class );
        resources.style().ensureInjected();

        EventBus eventBus = new SimpleEventBus();

        MainView mainView = new MainView( eventBus, resources );
        RootLayoutPanel.get().add( mainView );

        ActivityMapper activityMapper = new AppActivityMapper( resources );
        PlaceHistoryMapper placeHistoryMapper = new AppPlaceHistoryMapper();

        PlaceController placeController = new PlaceController( eventBus );
        PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler( placeHistoryMapper );
        placeHistoryHandler.register( placeController, eventBus, new SimplePlace() );

        ActivityManager activityManager = new ActivityManager( activityMapper, eventBus );
        activityManager.setDisplay( mainView.getContainer() );

        placeHistoryHandler.handleCurrentHistory();
    }

}