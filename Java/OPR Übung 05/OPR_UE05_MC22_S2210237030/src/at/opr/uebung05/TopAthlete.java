package at.opr.uebung05;

public class TopAthlete extends ActiveMember {
	
	public TopAthlete(int activity, String name) {
		super(name, activity);
	}

	@Override
	double getIncome() {
		return 10;
	}

	@Override
	double getCosts() {
		return activity * 5;
	}	
}
