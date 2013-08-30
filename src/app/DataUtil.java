package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataUtil {
	public static int costPerM3 = 0;
	public static int costPerKg = 0;
	public static int costPerM2 = 0;
	public static double reFactor = 0;
	
	public static Map<String, Foundation> foundations = new ConcurrentHashMap<String, Foundation>();
	public static Map<String, Column> columns = new ConcurrentHashMap<String, Column>();
	public static Map<String, Beam> beams = new ConcurrentHashMap<String, Beam>();
	public static Map<String, Slab> slabs = new ConcurrentHashMap<String, Slab>();
	
	public static List<String> getFListAsStringList(){
		return new ArrayList<String>(foundations.keySet());
	}
	
	public static List<String> getCListAsStringList(){
		return new ArrayList<String>(columns.keySet());
	}
	
	public static List<String> getBListAsStringList(){
		return new ArrayList<String>(beams.keySet());
	}
	
	public static List<String> getSListAsStringList(){
		return new ArrayList<String>(slabs.keySet());
	}
	
	public static int getTotalFCost(){
		int sum=0;
		for (Foundation f : foundations.values())
			sum += f.getTotalCost();
		
		return sum;
	}
	
	public static int getTotalCCost(){
		int sum = 0;
		for (Column c : columns.values())
			sum += c.getTotalCost();
		
		return sum;
	}
	
	public static int getTotalBCost(){
		int sum = 0;
		for (Beam b : beams.values())
			sum += b.getTotalCost();
		
		return sum;
	}
	
	public static int getTotalSCost(){
		int sum=0;
		for (Slab s : slabs.values())
			sum += s.getTotalCost();
		
		return sum;
	}
	
	public static int getTotalCost(){
		return getTotalFCost() + getTotalCCost() + getTotalBCost() + getTotalSCost();
	}
	
    public static final Map<Integer, Double> sizeFactorMap;
    static {
        Map<Integer, Double> aMap = new HashMap<Integer, Double>();
        aMap.put(6, 0.222);
        aMap.put(8, 0.395);
        aMap.put(10, 0.617);
        aMap.put(12, 0.888);
        aMap.put(14, 1.209);
        aMap.put(16, 1.588);
        aMap.put(18, 2.000);
        aMap.put(20, 2.468);
        aMap.put(22, 2.986);
        aMap.put(24, 3.554);
        aMap.put(28, 4.840);
        aMap.put(32, 6.320);
        sizeFactorMap = Collections.unmodifiableMap(aMap);
    }
    
	public static double convertMMtoM (double num){
		return num/1000;
	}
	
}
