/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package course.factory.repository;

import course.repository.impl.MemberInformationRepositoryImpl;
import course.repository.itf.MemberInformationRepository;

public class MemberInformationRepositoryFactory {
	private static final MemberInformationRepository memberInformationRepository = new MemberInformationRepositoryImpl();
	
	public static MemberInformationRepository getMemberInformationRepository() {
		return memberInformationRepository;
	}
}
