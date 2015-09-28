import java.util.*;
import java.lang.*;
import java.text.SimpleDateFormat;

public class CalendarExample { 
	
	private static ArrayList<Map<String, ArrayList<String>>> temp = new ArrayList<>();

    public static int day(int M, int D, int Y) {
    	//http://katzentier.de/_misc/perpetual_calendar.htm
        int x = Y + Y/4 - Y/100 + Y/400;
        int m = M + 12 * ((14 - M) / 12) - 2;
        int d = (D + x + (31*m)/12) % 7;
        return d;
    }

    // return true if the given year is a leap year
    public static boolean isLeapYear(int year) {
        if  ((year % 4 == 0) && (year % 100 != 0)) return true;
        if  (year % 400 == 0) return true;
        return false;
    }
    
    public static void printCalendar(GregorianCalendar cal) {
        int M = cal.get(Calendar.MONTH)+1;    // month (Jan = 1, Dec = 12)
        int Y = cal.get(Calendar.YEAR);    // year

        // months[i] = name of month i
        String[] months = {
            "",                               // leave empty so that months[1] = "January"
            "January", "February", "March",
            "April", "May", "June",
            "July", "August", "September",
            "October", "November", "December"
        };

        // days[i] = number of days in month i
        int[] days = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };

        // check for leap year
        if (M == 2 && isLeapYear(Y)) days[M] = 29;

        // print calendar header
        System.out.println("   " + months[M] + " " + Y);
        System.out.println("Sun Mon Tue Wed Thur Fri Sat");

        // starting day
        int d = day(M, 1, Y);

        // print the calendar
        for (int i = 0; i < d; i++)
            System.out.print("    ");
        for (int i = 1; i <= days[M]; i++) {
        	// if we're at the end of the week or the date equals the number of days for the month, println();
            System.out.printf("%3d ", i);
            if (((i + d) % 7 == 0) || (i == days[M])) System.out.println();
        }
        System.out.println("Enter P for previous day or N for next day or Menu for Main Menu: ");

    }
    public static void printDay(GregorianCalendar cal) {
    	Locale locale = Locale.US;
    	System.out.print(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale));
    	System.out.print(", ");
    	System.out.print(cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, locale));
    	System.out.print(" ");
    	System.out.print(cal.get(Calendar.DAY_OF_MONTH));
    	System.out.print(", ");
    	System.out.print(cal.get(Calendar.YEAR));
    	System.out.println();
    	
    }
    
    public static void resetCalendar(GregorianCalendar cal) {
    	GregorianCalendar newCal = new GregorianCalendar();
    	cal.set(newCal.get(Calendar.YEAR), newCal.get(Calendar.MONTH), newCal.get(Calendar.DAY_OF_MONTH));
    }
    
    public static ArrayList<Integer> dateParser (Scanner sc) {
    	System.out.println("Enter date (MM/DD/YYYY): ");
    	ArrayList<Integer> dateStuff = new ArrayList<>();
		String date = sc.nextLine();
		
		int month = Integer.parseInt(date.substring(0, 2))-1;
		int day = Integer.parseInt(date.substring(3, 5));
		int year =Integer.parseInt(date.substring(6, 10));
		dateStuff.add(year);
		dateStuff.add(month);
		dateStuff.add(day);
		return dateStuff;

    }
    public static void saveEvents(Map<String, ArrayList<String>> e) {
    	temp.add(e);
    }
    
    public static String format(GregorianCalendar calendar){
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }
    public static void processInput(String input, GregorianCalendar cal) {
    	Map<String, ArrayList<String>> events = new HashMap<String, ArrayList<String>>();
    	Scanner sc = new Scanner(System.in);
    	if(input.equalsIgnoreCase("V")) {
			System.out.println("[D]ay view or [M]view? ");
			input = sc.nextLine();
			if(input.equalsIgnoreCase("M")) {
				printCalendar(cal);
				input = sc.nextLine();
				while(!input.equalsIgnoreCase("menu")) {
					if(input.equalsIgnoreCase("p")) {
			    		cal.add(Calendar.MONTH, -1);
			    		printCalendar(cal);
			    		System.out.println();
			    	} else if(input.equalsIgnoreCase("n")) {
			    		cal.add(Calendar.MONTH,1);
			   			printCalendar(cal);
			    		System.out.println();
			    	}
					input = sc.nextLine();
					processInput(input, cal);
				}

			} else if (input.equalsIgnoreCase("D")) {
				printDay(cal);
				input = sc.nextLine();
				while(!input.equalsIgnoreCase("menu")) {
					if(input.equalsIgnoreCase("p")) {
			    		cal.add(Calendar.DAY_OF_MONTH, -1);
			    		printDay(cal);
			    		System.out.println();
			    	} else if(input.equalsIgnoreCase("n")) {
			    		cal.add(Calendar.DAY_OF_MONTH,1);
			   			printDay(cal);
			    		System.out.println();
			    	}
					input = sc.nextLine();
					processInput(input, cal);
				}

			
			processInput(input,cal);
			} 
    	}else if (input.equalsIgnoreCase("menu")) {
			System.out.println("Select one of the following options: \n[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
			input = sc.nextLine();
			processInput(input, cal);
		} else if (input.equalsIgnoreCase("G")) {
			ArrayList<Integer> datestuff = dateParser(sc);
			cal.set(datestuff.get(0), datestuff.get(1), datestuff.get(2));
			printDay(cal);
			ArrayList<Map<String, ArrayList<String>>> dateEvents = temp;
			if(!dateEvents.isEmpty()) { // if there are dates associated with a day, print them
				
				for(int i = 0; i < dateEvents.size(); i++) {
					Set<String> keySet =dateEvents.get(i).keySet();
					for(String key: keySet) {
						ArrayList<String> value = dateEvents.get(i).get(key);
						String title = value.get(0);
						String startTime = value.get(1);
						if(value.size() > 1) {
							String endTime = value.get(2);
							System.out.println(title + "  " +startTime + " - " + endTime);
							
						} else {
							System.out.println(title + "  " +startTime);
						}
						
						
					}
					
					
				}
			}
			
			resetCalendar(cal);
			
			
			System.out.println("Select one of the following options: \n[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
			input = sc.nextLine();
			processInput(input,cal);
		} else if (input.equalsIgnoreCase("C")) {
			ArrayList<Integer> datestuff = dateParser(sc);
			GregorianCalendar dateToSet = new GregorianCalendar(datestuff.get(0), datestuff.get(1), datestuff.get(2));
			
			ArrayList<String> eventData = new ArrayList<>();
			System.out.println("Enter a title for your event: ");
			input = sc.nextLine(); // get the title 
			eventData.add(input); 
			// eventData.add(dateToSet.toString()); 
			
			System.out.println("[REQUIRED] Enter a starting time for your event (24 Hour Format): "); // get the starting time
			input = sc.nextLine();
			eventData.add(input);
			
			System.out.println("[OPTIONAL] Enter an ending time for your event (24 Hour Format): "); // get the ending time
			input = sc.nextLine();
			eventData.add(input);
			String dateKey = format(dateToSet);
			events.put(dateKey, eventData);
			saveEvents(events);
			
			System.out.println("Select one of the following options: \n[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
			input = sc.nextLine();
			processInput(input,cal);
		}
    	
    }
    


	public static void main(String[] args) {
    	GregorianCalendar cal = new GregorianCalendar();
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Select one of the following options: \n[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
    	String input = sc.nextLine();
    	while(!input.equalsIgnoreCase("Q")) {
    		processInput(input,cal);
    		if(input.equalsIgnoreCase("Q")) {
    			break;
    		}
    	}
    	
    }
}


