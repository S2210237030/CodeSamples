package at.opr.uebung05;

public class Trainer extends ActiveMember {

	public Trainer(int activity, String name) {
		super(name, activity);
	}

	@Override
	double getIncome() {
		return 10;
	}

	@Override
	double getCosts() {
		return activity * 40;
	}
}
