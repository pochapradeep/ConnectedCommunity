package com.connComm.masters

class CommunityPendingRegistration {

	String firstName
	String lastName
	Integer confirmationCode
	String email
	String phone
	Date codeExpiryDate
	Role role
	CommunityMaster communityMaster

	static belongsTo = [CommunityMaster, Role]

	static mapping = {
		id column: "community_pending_registration_id", generator: "increment"
		communityMaster column:'community_id'
		firstName column:'first_name'
		lastName column: 'last_name'
		confirmationCode column: 'confirmation_code'
		email column: 'email'
		phone column: 'phone'
		codeExpiryDate column: 'code_expiry_date'
		role column:'role_id'
		version false
	}
	
	static constraints = {
		firstName maxSize: 50
		lastName nullable: true, maxSize: 50
		email maxSize: 50
		phone maxSize: 20
		codeExpiryDate nullable: true
	}
}
