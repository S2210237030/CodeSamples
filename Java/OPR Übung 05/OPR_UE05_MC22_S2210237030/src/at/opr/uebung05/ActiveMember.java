package at.opr.uebung05;

public abstract class ActiveMember extends AbstractMember {

	public int activity;

	public ActiveMember(String name, int activity) {
		super(name);
		this.activity = activity;
	}

}
