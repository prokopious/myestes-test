package util;

import java.util.Calendar;

public class CheckHoliday {

	public static boolean isHoliday(Calendar cal){
		
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			return true;
		}
		
		/**
		 * Check New Years Day (1st of January)
		 */
		if (cal.get(Calendar.MONTH) == Calendar.JANUARY
			&& cal.get(Calendar.DAY_OF_MONTH) == 1) {
			return true;
		}
		
		/**
		 * check Memorial Day (last Monday of May)
		 */
		
		if (cal.get(Calendar.MONTH) == Calendar.MAY
			&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
			&& cal.get(Calendar.DAY_OF_MONTH) > (31 - 7) ) {
			return true;
		}
		
		/**
		 * check Independence Day ( 4th of July )
		 */
		if (cal.get(Calendar.MONTH) == Calendar.JULY
			&& cal.get(Calendar.DAY_OF_MONTH) == 4) {
			return true;
		}
		
		/**
		 * check Labor Day (1st Monday of September)
		 */
		if (cal.get(Calendar.MONTH) == Calendar.SEPTEMBER
			&& cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 1
			&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			return true;
		}
		
		/**
		 * check Thanksgiving (4th Thursday of November)
		 */
		if (cal.get(Calendar.MONTH) == Calendar.NOVEMBER
			&& cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 4
			&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
			return true;
		}
		
		/**
		 * check Day After Thanksgiving
		 */
		if (cal.get(Calendar.MONTH) == Calendar.NOVEMBER
			&& cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 4
			&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			return true;
		}
		
		
		
		/**
		 * Check Christmas Eve (24th December)
		 */
		if (cal.get(Calendar.MONTH) == Calendar.DECEMBER
			&& cal.get(Calendar.DAY_OF_MONTH) == 24) {
			return true;
		}
		
		/**
		 * check Christmas Day (25th of December)
		 */
		if (cal.get(Calendar.MONTH) == Calendar.DECEMBER
			&& cal.get(Calendar.DAY_OF_MONTH) == 25) {
			return true;
		}
		
		/**
		 * It's a bussiness day
		 */
		return false;
		
		
	}


}
