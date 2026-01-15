package pack4;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

public class TC_timedate {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		DateTimeFormatter formatter=DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("dd/mm/yy");

		Instant currenttime=Instant.now();
		System.out.println("current Time:"+ currenttime);
		
		LocalDate now=LocalDate.now();
		LocalDate Independence=LocalDate.of(1947, 8, 15);
		Period period=Independence.until(now);
		
		System.out.println("format:"+ now.format(formatter1));
		System.out.println("Now:"+now);
		System.out.println("Independence:"+Independence);
		System.out.println("Tomorrow:"+now.plusDays(1));
		System.out.println("LastMonth:"+now.minusMonths(1));
		System.out.println("Leapyear:"+now.isLeapYear());
		System.out.println("move to 30th day of the month:"+now.withDayOfMonth
				(30));
		System.out.println("Period:"+period);
		System.out.println("Days:"+period.get(ChronoUnit.DAYS));
		System.out.println("Months:"+period.get(ChronoUnit.MONTHS));
		System.out.println("Years:"+period.get(ChronoUnit.YEARS));

		
		ZonedDateTime ct =ZonedDateTime.now();
		ZonedDateTime ctinparis =ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		System.out.println("India:"+ ct);
		System.out.println("Paris:"+ctinparis);

		

		

	}

}
