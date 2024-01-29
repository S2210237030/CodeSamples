package at.opr.uebung05;

public class Section extends AbstractMember {

	@SuppressWarnings("rawtypes")
	BinarySearchTree t;

	@SuppressWarnings("rawtypes")
	public Section(String name) {
		super(name);
		t = new BinarySearchTree();
	}

	@Override
	public double getIncome() {
		double income = 0;
		@SuppressWarnings("unchecked")
		Comparable<AbstractMember>[] c = t.toArray(true);
		for (int i = 0; i < c.length; i++) {
			income += ((AbstractMember) c[i]).getIncome();
		}
		return income;
	}

	@Override
	public double getCosts() {
		double costs = 0;
		@SuppressWarnings("unchecked")
		Comparable<AbstractMember>[] c = t.toArray(true);
		for (int i = 0; i < c.length; i++) {
			costs += ((AbstractMember) c[i]).getCosts();
		}
		return costs;
	}

	@Override
	public double getSurplus() {
		return getIncome() - getCosts();
	}

	@Override
	public String toString() {
		return this.toString(true);
	}

	public String toString(boolean ascending) {
		int rec = 0;
		int indent = 0;
		return this.toString(ascending, rec, indent);
	}

	public String toString(boolean ascending, int rec, int indent) {
		StringBuilder b = new StringBuilder();
		b.append("Verein " + name + ":\n");
		@SuppressWarnings("unchecked")
		Comparable<AbstractMember>[] c = t.toArray(true);
		for (int i = 0; i < c.length; i++) {
			b.append("\n");
			if (c[i] instanceof Section) {
				indent += 5;
				for (int j = 0; j < indent; j++) {
					b.append(" ");
				}
				b.append(((Section) c[i]).toString(true, ++rec, indent));
				rec--;
				indent -= 5;
			} else {
				if (rec > 0) {
					for (int j = 0; j < indent; j++) {
						b.append(" ");
					}
				}
				b.append(((AbstractMember) c[i]).toString(true));
				b.append("\n");
			}
		}
		return b.toString();
	}

	@SuppressWarnings("unchecked")
	public boolean addMember(AbstractMember m) {
		if (contains(m)) {
			return false;
		} else if (m instanceof ActiveMember) {
			if (((ActiveMember) m).activity > 10 || ((ActiveMember) m).activity < 0) {
				return false;
			}
		} else if (m instanceof ChairMember) {
			if (((ChairMember) m).competence > 10 || ((ChairMember) m).competence < 0) {
				return false;
			}
		}
		return t.insert(m);
	}

	@SuppressWarnings("unchecked")
	public boolean removeMember(AbstractMember m) {
		if (contains(m)) {
			return t.remove(m);
		}
		return false;
	}

	public boolean isMember(AbstractMember m) {
		return contains(m);
	}

	public boolean contains(AbstractMember m) {
		@SuppressWarnings("unchecked")
		Comparable<AbstractMember>[] c = t.toArray(true);
		for (int i = 0; i < c.length; i++) {
			if (((AbstractMember) c[i]).name == m.name) {
				return true;
			}
		}
		return false;
	}

}
