import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.*;
import java.text.SimpleDateFormat;

public class MyCalendarTester { 
	
	private static ArrayList<Map<String, ArrayList<String>>> temp = new ArrayList<>();
	
	/**
	 * Creates a day object using supplied data
	 * @param M An integer value representing a month
	 * @param D An integer value representing a day
	 * @param Y An integer value representing a year
	 * @return An integer value that corresponds with the correct day of the week
	 */
    public static int day(int M, int D, int Y) {
    	//http://katzentier.de/_misc/perpetual_calendar.htm
        int x = Y + Y/4 - Y/100 + Y/400;
        int m = M + 12 * ((14 - M) / 12) - 2;
        int d = (D + x + (31*m)/12) % 7;
        return d;
    }
    /**
     * Checks if the year is a leap year
     * @param year An integer value representing a year
     * @return True if the year is a leap year
     */
    public static boolean isLeapYear(int year) {
        if  ((year % 4 == 0) && (year % 100 != 0)) return true;
        if  (year % 400 == 0) return true;
        return false;
    }
    
    /**
     * Prints a calendar to the console according to the format specified
     * @param cal A GregorianCalendar object
     */
    public static void printCalendar(GregorianCalendar cal) {
        int M = cal.get(Calendar.MONTH)+1;   
        int Y = cal.get(Calendar.YEAR);  

        String[] months = {
            "",                               // will have to subtract 1 to achieve correct month at a given index.  For example, months[4] is May.
            "January", "February", "March",
            "April", "May", "June",
            "July", "August", "September",
            "October", "November", "December"
        };

        
        int[] days = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };

        
        if (M == 2 && isLeapYear(Y)) days[M] = 29;

        // print calendar header
        System.out.println("   " + months[M] + " " + Y);
        System.out.println("Sun Mon Tue Wed Thur Fri Sat");
        GregorianCalendar now = new GregorianCalendar();
        int today = now.get(Calendar.DAY_OF_MONTH);
        int month = now.get(Calendar.MONTH)+1; // +1 for offset of index
        // starting day
        int d = day(M, 1, Y);

        // print the calendar
        for (int i = 0; i < d; i++)
            System.out.print("    ");
        for (int i = 1; i <= days[M]; i++) {
        	// if we're at the end of the week or the date equals the number of days for the month, println();
        	if (i == today && month == M) {
        		System.out.print("["+ i + "]");
        	} else {
        		System.out.printf("%3d ", i);
        	}
            
            if (((i + d) % 7 == 0) || (i == days[M])) System.out.println(); // print a new line if we're at the end of the week
        }
        System.out.println("Enter P for previous month or N for next month or Menu for Main Menu: ");

    }
    /**
     * Prints a day from the calendar according to the format specified
     * @param cal The calendar object to collect data from
     */
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
    	System.out.println("Enter P for previous day or N for next day or Menu for Main Menu: ");
    }
    /**
     * This method reverts changes made to the calendar and resets to the present day
     * @param cal The new calendar set with the current date
     */
    public static void resetCalendar(GregorianCalendar cal) {
    	GregorianCalendar newCal = new GregorianCalendar();
    	cal.set(newCal.get(Calendar.YEAR), newCal.get(Calendar.MONTH), newCal.get(Calendar.DAY_OF_MONTH));
    }
    /**
     * This method formats data supplied by the user to correctly represent a date
     * @param sc
     * @return
     */
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
    /**
     * This method prevents calendar data from being over-ridden
     * @param e A map of 
     */
    public static void saveEvents(Map<String, ArrayList<String>> e) {
    	temp.add(e);
    }
    /**
     * Formats a date according to the format specified
     * @param calendar
     * @return
     */
    public static String format(GregorianCalendar calendar){
        SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/YYYY");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }
    /**
     * Controls the execution of the program with user supplied input
     * @param input The input to process
     * @param cal The calendar to modify
     * @param sc A scanner to accept user input
     */
    public static void processInput(String input, GregorianCalendar cal, Scanner sc) {
    	Map<String, ArrayList<String>> events = new HashMap<String, ArrayList<String>>();
    	// Scanner sc = new Scanner(System.in);
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
					processInput(input, cal, sc);
				}
				processInput(input, cal, sc);

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
					processInput(input, cal, sc);
				}

			
			processInput(input, cal, sc);
			} 
    	}else if (input.equalsIgnoreCase("menu")) {
			System.out.println("Select one of the following options: \n[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
			input = sc.nextLine();
			processInput(input, cal, sc);
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
			processInput(input, cal, sc);
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
			processInput(input, cal, sc);
		} else if (input.equalsIgnoreCase("E")) {
			
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
							System.out.println(title + "  " + key + " " +startTime + " - " + endTime);
						} else {
							System.out.println(title + "  " +startTime);
						}						
					}
				}
			}
			System.out.println("Select one of the following options: \n[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
			input = sc.nextLine();
			processInput(input, cal, sc);
			
		} else if (input.equalsIgnoreCase("D")) {
			ArrayList<Map<String, ArrayList<String>>> dateEvents = temp;
			System.out.println("[S]elected or [A]ll?");
			input = sc.nextLine();
			if(input.equalsIgnoreCase("s")) {
				System.out.println("Enter date (MM/DD/YYYY): ");
				input = sc.nextLine();
				System.out.println(dateEvents.isEmpty());
				if(!dateEvents.isEmpty()) { // if there are dates associated with a day, print them
					for(int i = 0; i < dateEvents.size(); i++) {
						Set<String> keySet =dateEvents.get(i).keySet();
						for(String key: keySet) {
							if(input.equals(key)) {
								temp.remove(dateEvents.get(i));
								System.out.println("Event Deleted");
							}					
						}
					}
				}
			} else if (input.equalsIgnoreCase("a")) {
				if(!dateEvents.isEmpty()) { // if there are dates associated with a day, print them
					for(int i = 0; i < dateEvents.size(); i++) {
						Set<String> keySet =dateEvents.get(i).keySet();
						for(String key: keySet) {
							temp.removeAll(dateEvents);
							System.out.println("All events deleted");
						}
					}
				}
			}
			
			
			System.out.println("Select one of the following options: \n[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
			input = sc.nextLine();
			processInput(input, cal, sc);
			
		} else if (input.equalsIgnoreCase("L")) {
			int month = 0;
			int day = 0;
			int year = 0;
			ArrayList<String> eventData = new ArrayList<>();
			
			File file = new File("/Users/spivotron/Desktop/Eclipse/CalendarExample/bin/input.txt");
		    try {
		        Scanner fileScan = new Scanner(file);
		        while (fileScan.hasNext()) {
		        	
		        	
		            
		            month = fileScan.nextInt()-1;
		            day   = fileScan.nextInt();
		            year  = fileScan.nextInt();
		           
		            
		           
		            
		            GregorianCalendar dateToSet = new GregorianCalendar(year, month, day);
		            String dateKey = format(dateToSet);
		            
		            fileScan.nextLine();
		            input = fileScan.nextLine(); // get the title 
					eventData.add(input); 
					
					input = fileScan.nextLine(); // get the starting time
					eventData.add(input);
					
					input = fileScan.nextLine(); // get the ending time
					eventData.add(input);
		          
		            events.put(dateKey, eventData);
					
		            
		        }
		        saveEvents(events);
		        
		    } 
		    catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
		
			System.out.println("Select one of the following options: \n[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
			input = sc.nextLine();
			processInput(input, cal, sc);
			
		} else if (input.equalsIgnoreCase("Q")) {
			ArrayList<Map<String, ArrayList<String>>> dateEvents = temp;
			try {
				PrintStream out = new PrintStream(new FileOutputStream("events.txt"));
				if(!dateEvents.isEmpty()) {
					for(int i = 0; i < dateEvents.size(); i++) { // -1 solves duplication problem
						
						Set<String> keySet =dateEvents.get(i).keySet();
						System.out.println(keySet.size());
						
						for(String key: keySet) {
							ArrayList<String> value = dateEvents.get(i).get(key);
							String title = value.get(0);
							String startTime = value.get(1);
							
							if(value.get(2) != null || !value.get(2).equals("undefined")) {
								String endTime = value.get(2);
								out.println(title + "  " + key + " " +startTime + " - " + endTime);
							} else {
								out.println(title + "  " +startTime);
							}	
							
						}
					}
				}
				out.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("Select one of the following options: \n[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
			input = sc.nextLine();
			processInput(input, cal, sc);
			
		}
    	
    }
    


	public static void main(String[] args) {
    	GregorianCalendar cal = new GregorianCalendar();
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Select one of the following options: \n[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
    	String input = sc.nextLine();
    	while(!input.equalsIgnoreCase("T")) {
    		processInput(input,cal, sc);
    		
    	}
    	
    }
}


