/*
 * 
 * 
 * 
 * 
 */
public class State {

	
	private String name;
	private String capitol;
	private String region;
	private String population;
	private String numOfCovidCases;
	private String numOfCovidDeaths;
	private String householdIncome;
	private String crimeRate;
	private double CFR;
	private double caseRate;
	
	
	public State(String name) {
		
		this.name = name;
	}
	
	/**
	 * @param name
	 * @param capitol
	 * @param region
	 * @param population
	 * @param numOfCovidCases
	 * @param numOfCovidDeaths
	 * @param householdIncome
	 * @param crimeRate
	 */
	public State(String name, String capitol ,String region , String population , String numOfCovidCases, String numOfCovidDeaths, String householdIncome , String crimeRate) {
		
		setName(name);
		setCapitol(capitol);
		setRegion(region);
		setPopulation(population);
		setNumOfCovidCases(numOfCovidCases);
		setNumOfCovidDeaths(numOfCovidDeaths);
		setHouseholdIncome(householdIncome);
		setCrimeRate(crimeRate);
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the capitol
	 */
	public String getCapitol() {
		return capitol;
	}


	/**
	 * @param capitol the capitol to set
	 */
	public void setCapitol(String capitol) {
		this.capitol = capitol;
	}


	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}


	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}


	/**
	 * @return the population
	 */
	public String getPopulation() {
		return population;
	}


	/**
	 * @param population the population to set
	 */
	public void setPopulation(String population) {
		this.population = population;
	}


	/**
	 * @return the numOfCovidCases
	 */
	public String getNumOfCovidCases() {
		return numOfCovidCases;
	}


	/**
	 * @param numOfCovidCases the numOfCovidCases to set
	 */
	public void setNumOfCovidCases(String numOfCovidCases) {
		this.numOfCovidCases = numOfCovidCases;
	}


	/**
	 * @return the numOfCovidDeaths
	 */
	public String getNumOfCovidDeaths() {
		return numOfCovidDeaths;
	}


	/**
	 * @param numOfCovidDeaths the numOfCovidDeaths to set
	 */
	public void setNumOfCovidDeaths(String numOfCovidDeaths) {
		this.numOfCovidDeaths = numOfCovidDeaths;
	}


	/**
	 * @return the householdIncome
	 */
	public String getHouseholdIncome() {
		return householdIncome;
	}


	/**
	 * @param householdIncome the householdIncome to set
	 */
	public void setHouseholdIncome(String householdIncome) {
		this.householdIncome = householdIncome;
	}


	/**
	 * @return the crimeRate
	 */
	public String getCrimeRate() {
		return crimeRate;
	}
	public void setCFR(double CFR) {
		
		this.CFR = CFR;
	}
	public double getCFR() {
		return CFR;
	}
	public void setCaseRate(double caseRate) {
		this.caseRate = caseRate;
	}
	public double getCaseRate() {
		return caseRate;
	}


	/**
	 * @param crimeRate the crimeRate to set
	 */
	public void setCrimeRate(String crimeRate) {
		this.crimeRate = crimeRate;
	}
	public String toString() {
		
		return String.format(" %13s\t\t%12s\t\t%14s\t\t%8s\t\t%5.5f\t\t%5.5f\t\t%5s\t\t%4s ", getName() ,
				getCapitol() ,
				getRegion() , getPopulation() , 
				getCaseRate() ,getCFR() , getHouseholdIncome() ,
				getCrimeRate());
	}
}
