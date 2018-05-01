package com.ubs.opsit.interviews;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeConverterImpl implements TimeConverter {

	private static final Logger LOG = LoggerFactory.getLogger(TimeConverterImpl.class);
	private static final String Y = "Y";
	private static final String O = "O";
	private static final String R = "R";
	private static final String ERROR = "ERROR";
	
	@Override
    public String convertTime(String aTime) {
    	
		if(null == aTime || aTime.isEmpty()) {
			LOG.info("Error: Invalid input time");
			return ERROR;
		}
		
		String[] array = aTime.split(":");
		if(array.length < 3) {
			LOG.info("Error: Invalid number of time component");
			return ERROR;
		}
		
		String topBlinkingLightInBerlinClockFormat = getTopBlinkingLightInBerlinClockFormat(array[2]);
		String hoursInBerlinClockFormat = getHoursInBerlinClockFormat(array[0]);
		String minsInBerlinClockFormat = getMinsInBerlinClockFormat(array[1]);
		
		if(ERROR.equals(topBlinkingLightInBerlinClockFormat) ||
				ERROR.equals(hoursInBerlinClockFormat) ||
				ERROR.equals(minsInBerlinClockFormat)) {
			return ERROR;
		}
		
		StringBuilder timeInBerlinClockFormat = new StringBuilder();
		timeInBerlinClockFormat.append(topBlinkingLightInBerlinClockFormat)
								.append(System.lineSeparator())
								.append(hoursInBerlinClockFormat)
								.append(System.lineSeparator())
								.append(minsInBerlinClockFormat);
		
		return timeInBerlinClockFormat.toString();
    }
	
	private String getTopBlinkingLightInBerlinClockFormat(String seconds) {
		
		int secs = 0;
		if(Utils.isStringNullOrEmpty(seconds)) {
			LOG.info("Error: Seconds are empty");
			return ERROR;
			
		} else {
			try {
				secs = Integer.parseInt(seconds);
			} catch (NumberFormatException nfe) {
				
				LOG.info("Error: Invalid input for seconds");
				return ERROR;
			}
		}

		String topBlinkingLightInBerlinClockFormat = "";
		if(secs%2 == 0) {
			topBlinkingLightInBerlinClockFormat = Y;
		} else {
			topBlinkingLightInBerlinClockFormat = O;
		}
		
		return topBlinkingLightInBerlinClockFormat;
	}
	
	private String getHoursInBerlinClockFormat(String hoursStr) {
		
		int hours = 0;
		if(Utils.isStringNullOrEmpty(hoursStr)) {
			LOG.info("Error: Hours are empty");
			return ERROR;
			
		} else {
			try {
				hours = Integer.parseInt(hoursStr);
				
			} catch (NumberFormatException nfe) {
				LOG.info("Error: Invalid input for hours");
				return ERROR;
			}
		}
		
		String arrForFiveHours[] = {O, O, O, O};
		String arrForOneHour[] = {O, O, O, O};
		int fiveHoursCount = hours/5;
		int oneHourCount = hours%5;
		
		for(int i=0; i<fiveHoursCount; i++) {
			arrForFiveHours[i] = R;
		}
		
		for(int i=0; i<oneHourCount; i++) {
			arrForOneHour[i] = R;
		}
		
		StringBuilder hoursInBerlinClockFormat = new StringBuilder();
		hoursInBerlinClockFormat.append(String.join("", arrForFiveHours)).append(System.lineSeparator()).append(String.join("", arrForOneHour)); 
		
		return hoursInBerlinClockFormat.toString();
	}
	
	private String getMinsInBerlinClockFormat(String minsStr) {
		
		int mins = 0;
		if(Utils.isStringNullOrEmpty(minsStr)) {
			LOG.info("Error: Mins are empty");
			return ERROR;
			
		} else {
			try {
				mins = Integer.parseInt(minsStr);
				
			} catch (NumberFormatException nfe) {
				LOG.info("Error: Invalid input for mins");
				return ERROR;
			}
		}
		
		String arrForFiveMins[] = {O, O, O, O, O, O, O, O, O, O, O};
		String arrForOneMin[] = {O, O, O, O};
		int fiveMinsCount = mins/5;
		int oneMinCount = mins%5;
		
		for(int i=0; i<fiveMinsCount; i++) {
			
			if((i+1)%3 == 0) {
				arrForFiveMins[i] = R;
			} else {
				arrForFiveMins[i] = Y;
			}
		}
		
		for(int i=0; i<oneMinCount; i++) {
			arrForOneMin[i] = Y;
		}
		
		StringBuilder minsInBerlinClockFormat = new StringBuilder();
		minsInBerlinClockFormat.append(String.join("", arrForFiveMins)).append(System.lineSeparator()).append(String.join("", arrForOneMin));
		
		return minsInBerlinClockFormat.toString();
	}

}
