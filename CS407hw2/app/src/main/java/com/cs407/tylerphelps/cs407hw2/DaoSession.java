package com.cs407.tylerphelps.cs407hw2;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.IdentityScopeType;

import com.cs407.tylerphelps.cs407hw2.Event;
import com.cs407.tylerphelps.cs407hw2.CalDate;

import com.cs407.tylerphelps.cs407hw2.EventDao;
import com.cs407.tylerphelps.cs407hw2.CalDateDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig eventDaoConfig;
    private final DaoConfig calDateDaoConfig;

    private final EventDao eventDao;
    private final CalDateDao calDateDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        eventDaoConfig = daoConfigMap.get(EventDao.class).clone();
        eventDaoConfig.initIdentityScope(type);

        calDateDaoConfig = daoConfigMap.get(CalDateDao.class).clone();
        calDateDaoConfig.initIdentityScope(type);

        eventDao = new EventDao(eventDaoConfig, this);
        calDateDao = new CalDateDao(calDateDaoConfig, this);

        registerDao(Event.class, eventDao);
        registerDao(CalDate.class, calDateDao);
    }
    
    public void clear() {
        eventDaoConfig.getIdentityScope().clear();
        calDateDaoConfig.getIdentityScope().clear();
    }

    public EventDao getEventDao() {
        return eventDao;
    }

    public CalDateDao getCalDateDao() {
        return calDateDao;
    }

}
