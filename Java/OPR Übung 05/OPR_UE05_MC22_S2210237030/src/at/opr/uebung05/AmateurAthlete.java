package at.opr.uebung05;

public class AmateurAthlete extends ActiveMember {

	public AmateurAthlete(int activity, String name) {
		super(name, activity);
	}

	@Override
	double getIncome() {
		return 25;
	}

	@Override
	double getCosts() {
		return activity * 2.5;
	}
}
