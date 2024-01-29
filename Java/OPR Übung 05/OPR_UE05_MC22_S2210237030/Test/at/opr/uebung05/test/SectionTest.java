package at.opr.uebung05.test;

import at.opr.uebung05.AbstractMember;
import at.opr.uebung05.AmateurAthlete;
import at.opr.uebung05.ChairMember;
import at.opr.uebung05.HonoraryMember;
import at.opr.uebung05.Section;
import at.opr.uebung05.SupportingMember;
import at.opr.uebung05.TopAthlete;
import at.opr.uebung05.Trainer;

public class SectionTest {

	public static void main(String[] args) {
		
		AbstractMember m1 = new SupportingMember("Niklas");
		AbstractMember m2 = new AmateurAthlete(4, "Moritz");
		AbstractMember m3 = new Trainer(9, "Tobias");
		AbstractMember m4 = new HonoraryMember("Tobias");
		AbstractMember m5 = new ChairMember(2, "Sandra");
		AbstractMember m6 = new TopAthlete(0, "Antonia");
		AbstractMember m7 = new TopAthlete(7, "Teodor");
		AbstractMember m8 = new AmateurAthlete(11, "Laura");
		Section s1 = new Section("Fußball");
		Section s2 = new Section("Volleyball");
		Section s3 = new Section("Tennis");
		Section s4 = new Section("Basketball");
		
		s1.addMember(m1);
		s1.addMember(m2);
		s1.addMember(m3);
		s1.addMember(m4);
		
		s2.addMember(m4);
		s2.addMember(m5);
		s3.addMember(m6);
		
		s1.addMember(s2);
		s2.addMember(s3);
		s1.addMember(s4);
		
		s4.addMember(m7);
		s4.addMember(m8);
		
		System.out.println(s1.toString(true));
		
		System.out.println("Übersicht Verein " + s1.name + ":");
		System.out.println(s1.getIncome());
		System.out.println(s1.getCosts());
		System.out.println(s1.getSurplus());
		System.out.println();
		
		System.out.println("Übersicht Verein " + s2.name + ":");
		System.out.println(s2.getIncome());
		System.out.println(s2.getCosts());
		System.out.println(s2.getSurplus());
		System.out.println();
		
		System.out.println("Übersicht Verein " + s3.name + ":");
		System.out.println(s3.getIncome());
		System.out.println(s3.getCosts());
		System.out.println(s3.getSurplus());
		System.out.println();
		
		System.out.println("Übersicht Verein " + s4.name + ":");
		System.out.println(s4.getIncome());
		System.out.println(s4.getCosts());
		System.out.println(s4.getSurplus());
		
		if(s1.isMember(m1)) {
			System.out.println();
			System.out.println("Member found: " + m1.name);
		} else {
			System.out.println();
			System.out.println("Member not found: " + m1.name);
		}
		
		if(s2.isMember(m6)) {
			System.out.println();
			System.out.println("Member found: " + m6.name);
		} else {
			System.out.println();
			System.out.println("Member not found: " + m6.name);
		}
		
		System.out.println();
		
		s1.removeMember(m2);
		s2.removeMember(m4);
		
		System.out.println();
		System.out.println(s1.toString(true));
		
	}
}
