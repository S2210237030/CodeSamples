package at.opr.uebung05;

public class SupportingMember extends AbstractMember{

	public SupportingMember(String name) {
		super(name);
	}

	@Override
	double getIncome() {
		return 100;
	}

	@Override
	double getCosts() {
		return 15;
	}
}
