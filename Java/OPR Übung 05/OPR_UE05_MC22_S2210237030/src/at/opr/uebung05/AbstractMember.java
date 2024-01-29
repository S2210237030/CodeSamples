package at.opr.uebung05;

public abstract class AbstractMember implements Comparable<AbstractMember> {

	public String name;

	public int compareTo(AbstractMember m) {
		return name.compareTo(m.name);
	}

	public AbstractMember(String name) {
		this.name = name;
	}

	abstract double getIncome();

	abstract double getCosts();

	double getSurplus() {
		return getIncome() - getCosts();
	}

	String toString(boolean ascending) {
		return String.format("%6s", "Name: ") + String.format("%-12s", name) + " | " + String.format("%9s", " Income: ")
				+ String.format("%8s", "€ " + getIncome()) + " | " + String.format("%8s", " Costs: ")
				+ String.format("%8s", "€ " + getCosts()) + " | " + String.format("%10s", " Surplus: ")
				+ String.format("%8s", "€ " + getSurplus());
	}
}
