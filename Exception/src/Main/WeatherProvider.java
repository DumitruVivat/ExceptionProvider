package Main;

import java.util.Random;

public class WeatherProvider {
    private static double temperatureForToday = generateTemperature();
    private static double[] temperaturesFor10Days = generateTemperaturesForecastFor10Days();

	private static double generateTemperature() {
        Random rd = new Random(); 
        double temperature = 340 * ( rd.nextDouble() - 0.5 );
        return temperature;
    }
    
    public static double[] generateTemperaturesForecastFor10Days() {
    	double[] days = new double[10];
    	for(int i =0;i < days.length;i++) 
    	days[i] = generateTemperature();
    	
    	return days;
    } 
      	
    public static double calculateAvgFor10Days() throws TemperatureDataOutOfRangeException {
    	double avgTemp = getTemperaturesFor10Days()[0];
    	for(int i =0; i < getTemperaturesFor10Days().length; i++)
    		avgTemp = getTemperaturesFor10Days()[i] + avgTemp;
    	
    		return avgTemp/getTemperaturesFor10Days().length;
    }
    //Getter
    public static double getMaxTemperatureFor10Days() throws TemperatureDataOutOfRangeException {
    	double max = getTemperaturesFor10Days()[0];
        for(int i =1; i < getTemperaturesFor10Days().length; i++) {
        	if(max < getTemperaturesFor10Days()[i])
        		max = getTemperaturesFor10Days()[i];
        }
        return max;
    }

    public static double getMinTemperatureFor10Days() throws TemperatureDataOutOfRangeException {
        double min = getTemperaturesFor10Days()[0];
        for(int i =1; i < getTemperaturesFor10Days().length; i++) {
        	if(min > getTemperaturesFor10Days()[i])
        		min = getTemperaturesFor10Days()[i];
        }
        return min;
    }
	public static double[] getTemperaturesFor10Days() throws TemperatureDataOutOfRangeException {
		for (int i = 0; i < temperaturesFor10Days.length; i++) {
			if (temperaturesFor10Days[i] < -150 || temperaturesFor10Days[i] > 150) {
				break;
			} else if (i == temperaturesFor10Days.length -1) {
				return temperaturesFor10Days;
			}
		}
		double[] temperaturesFor10DaysException = new double[temperaturesFor10Days.length];
		for (int i = 0; i < temperaturesFor10DaysException.length; i++) {
			if (temperaturesFor10Days[i] >= -150 && temperaturesFor10Days[i] <= 150) {
				temperaturesFor10DaysException[i] = temperaturesFor10Days[i];
				continue;
			} else if (temperaturesFor10Days[i] >= -152 && temperaturesFor10Days[i] < -150) {
				temperaturesFor10DaysException[i] = -150;
				continue;
			} else if (temperaturesFor10Days[i] > 150 && temperaturesFor10Days[i] <= 152) {
				temperaturesFor10DaysException[i] = 150;
				continue;
			} else {
				throw new TemperatureDataOutOfRangeException();
			}
		}
		return temperaturesFor10DaysException;
	}
	public static double getTemperatureForToday() throws TemperatureDataOutOfRangeException {
		if (temperatureForToday >= -150 && temperatureForToday <= 150) {
			return temperatureForToday;
		}
		double temperatureForTodayException = 0;
		if (temperatureForToday >= -152 && temperatureForToday < -150) {
			temperatureForTodayException = -150;
		} else if (temperatureForToday > 150 && temperatureForToday <= 152) {
			temperatureForTodayException = 150;
		} else {
			throw new TemperatureDataOutOfRangeException();
		}
		return temperatureForTodayException;
	}
}
class TemperatureDataOutOfRangeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6632075034520943219L;

	public TemperatureDataOutOfRangeException() {
		System.out.println("We are sorry!\nThe data is unavailable!");
        System.out.println("\n###################################");
	}
}
