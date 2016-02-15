package com.connComm.masters

class PersonalInformation {

	String firstName
	String lastName
	Date dob
	Byte gender
	String phoneNo
	String email
	String bloodGroup
	Boolean displayPersonalInfoFlag

	static hasMany = [residentMasters: ResidentMaster]

	static mapping = {
		id column: "PERSONAL_INFORMATION_ID", generator: "increment"
		version false
	}

	static constraints = {
		firstName maxSize: 50
		lastName maxSize: 50
		dob nullable: true
		gender nullable: true
		phoneNo nullable: true, maxSize: 20
		email nullable: true, maxSize: 50
		bloodGroup nullable: true, maxSize: 10
		displayPersonalInfoFlag nullable: true
	}
}
