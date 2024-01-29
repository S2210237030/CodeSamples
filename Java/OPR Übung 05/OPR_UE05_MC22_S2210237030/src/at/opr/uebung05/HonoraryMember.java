package at.opr.uebung05;

public class HonoraryMember extends AbstractMember {

	public HonoraryMember(String name) {
		super(name);
	}

	@Override
	double getIncome() {
		return 0;
	}

	@Override
	double getCosts() {
		return 20;
	}
}
