package com.example.weilun.eventcountdown;

/**
 * Created by Wei Lun on 8/1/2017.
 */

import android.provider.BaseColumns;

public class EventContract {
    public static class EventEntry implements BaseColumns {
        public static final String TABLE_NAME = "event";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_NOTIFY = "notify";
    }
}