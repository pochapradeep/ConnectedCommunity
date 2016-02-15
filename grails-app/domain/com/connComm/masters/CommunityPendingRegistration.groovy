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
