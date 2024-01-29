package at.opr.uebung05;

public class ChairMember extends AbstractMember {

	int competence;

	public ChairMember(int competence, String name) {
		super(name);
		this.competence = competence;
	}

	@Override
	double getIncome() {
		return competence * 100;
	}

	@Override
	double getCosts() {
		return getIncome() * 0.2;
	}
}
