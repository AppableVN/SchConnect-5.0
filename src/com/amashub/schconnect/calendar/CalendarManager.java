package com.amashub.schconnect.calendar;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.Activity;
import android.content.ContentValues;
import android.location.Location;
import android.net.Uri;
import android.provider.CalendarContract.Attendees;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;

public class CalendarManager {

	private final static String EVENURISTRING = "content://com.android.calendar/events";
	private final static int ENTRIES_TENTATIVE_STATUS = 0;
	private final static int CONFIRMED_STATUS = 1;
	private final static int CANCELED_STATUS = 2;
	
	private final static int CONFIDENTIAL = 1;
	private final static int PRIVATE = 2;
	private final static int PUBLIC = 3;
	
	private final static int OPAQUE = 0;
	private final static int TRANSPARENT = 1;
	
	public static long pushAppointmentsToCalender(Activity curActivity, boolean isNeedRemind, boolean isNeedMailService){
		ContentValues eventValues = new ContentValues();
		
		Calendar cal = Calendar.getInstance();
		TimeZone tz = cal.getTimeZone();
		
		eventValues.put("calendar"+Events._ID, 1); // id, We need to choose from
		        // our mobile for primary
		        // its 1
		eventValues.put(Events.TITLE, "test event title");
		eventValues.put(Events.DESCRIPTION, "test event description");
		eventValues.put(Events.EVENT_LOCATION, "AppableVN");
		
		long startDate = System.currentTimeMillis();
		
		long endDate = startDate + 1000 * 60 * 60; // For next 1hr
		
		eventValues.put(Events.DTSTART, startDate);
		eventValues.put(Events.DTEND, endDate);
		eventValues.put(Events.ALL_DAY, 1);
		// values.put("allDay", 1); //If it is bithday alarm or such
		// kind (which should remind me for whole day) 0 for false, 1
		// for true
		eventValues.put(Events.STATUS, ENTRIES_TENTATIVE_STATUS); // This information is
		// sufficient for most
		// entries tentative (0),
		// confirmed (1) or canceled
		// (2):
		eventValues.put(Events.ACCESS_LEVEL, Events.ACCESS_CONFIDENTIAL); // visibility to default (0),
		        // confidential (1), private
		        // (2), or public (3)
		eventValues.put(Events.HAS_ALARM, 1); // 0 for false, 1 for true
		eventValues.put(Events.EVENT_TIMEZONE, tz.getID());
		
		
		
		Uri eventUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(EVENURISTRING), eventValues);
		long eventID = Long.parseLong(eventUri.getLastPathSegment());
		
		if (isNeedRemind) {
			/***************** Event: Reminder(with alert) Adding reminder to event *******************/
			
			String reminderUriString = "content://com.android.calendar/reminders";
			
			ContentValues reminderValues = new ContentValues();
			
			reminderValues.put("event"+Events._ID, eventID);
			reminderValues.put(Reminders.MINUTES, 5); // Default value of the
			            // system. Minutes is a
			            // integer
			reminderValues.put(Reminders.METHOD, Reminders.METHOD_ALERT); // Alert Methods: Default(0),
			            // Alert(1), Email(2),
			            // SMS(3)
			
			Uri reminderUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(reminderUriString), reminderValues);
		}
		
		/***************** Event: Meeting(without alert) Adding Attendies to the meeting *******************/
		
		if (isNeedMailService) {
			String attendeuesesUriString = "content://com.android.calendar/attendees";
			
			/********
			* To add multiple attendees need to insert ContentValues multiple
			* times
			***********/
			ContentValues attendeesValues = new ContentValues();
			
			attendeesValues.put("event"+Events._ID, eventID);
			attendeesValues.put(Attendees.ATTENDEE_NAME, "xxxxx"); // Attendees name
			attendeesValues.put(Attendees.ATTENDEE_EMAIL, "yyyy@gmail.com");// Attendee
			                                            // E
			                                            // mail
			                                            // id
			attendeesValues.put(Attendees.ATTENDEE_RELATIONSHIP, Attendees.RELATIONSHIP_NONE); // Relationship_Attendee(1),
			                        // Relationship_None(0),
			                        // Organizer(2),
			                        // Performer(3),
			                        // Speaker(4)
			attendeesValues.put(Attendees.ATTENDEE_TYPE, Attendees.TYPE_OPTIONAL);
			attendeesValues.put(Attendees.ATTENDEE_STATUS, Attendees.ATTENDEE_STATUS_ACCEPTED);
			
			Uri attendeuesesUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(attendeuesesUriString), attendeesValues);
		}
		
		return eventID;
	}
}
