
public class State {

	protected String stateName;
	protected long population;
	protected long deaths;
	
	
	public State(String name ,long popu , long deaths)
	{
		this.stateName = name;
		this.population = popu;
		this.deaths = deaths;
	}


	public String getStateName() {
		return stateName;
	}


	public void setStateName(String stateName) {
		this.stateName = stateName;
	}


	public long getPopulation() {
		return population;
	}


	public void setPopulation(long population) {
		this.population = population;
	}


	public long getDeaths() {
		return deaths;
	}


	public void setDeaths(long deaths) {
		this.deaths = deaths;
	}
	
	
}
