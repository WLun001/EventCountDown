package com.example.weilun.eventcountdown;
/**
 * Created by Wei Lun on 8/1/2017.
 */

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Event implements java.io.Serializable {
    private long id;
    private String title;
    private String description;
    private Date date;
    private boolean notify;

    public Event() {
        this(0, "", "", Calendar.getInstance(), false);
    }

    public Event(long id, String title, String description, Date date, boolean notify) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.notify = notify;
    }

    public Event(long id, String title, String description, Calendar calendar, boolean notify) {
        this.id = id;
        this.title = title;
        this.description = description;
        setDate(calendar);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Calendar calendar) {
        calendar.set(Calendar.MILLISECOND, 0);
        this.date = new Date(calendar.getTimeInMillis());
    }

    public Calendar getDateAsCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        return calendar;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Countdown getCountdown() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.MILLISECOND, 0);

        long duration = date.getTime() - today.getTimeInMillis();

        Countdown countdown = new Countdown();
        countdown.durationInMillis = duration;
        countdown.days = TimeUnit.MILLISECONDS.toDays(duration);
        countdown.hours = TimeUnit.MILLISECONDS.toHours(duration) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
        countdown.minutes = TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
        countdown.seconds = TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));

        return countdown;
    }

    public class Countdown {
        private long durationInMillis;
        private long days;
        private long hours;
        private long minutes;
        private long seconds;

        public long getDurationInMillis() {
            return durationInMillis;
        }

        public long getDays() {
            return Math.abs(days);
        }

        public long getHours() {
            return Math.abs(hours);
        }

        public long getMinutes() {
            return Math.abs(minutes);
        }

        public long getSeconds() {
            return Math.abs(seconds);
        }
    }
}