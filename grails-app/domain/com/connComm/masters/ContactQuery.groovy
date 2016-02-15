package com.connComm.masters

class ContactQuery {

	String name
	String phoneNo
	String email
	String address
	QueryStatus queryStatus

	static belongsTo = [QueryStatus]

	static mapping = {
		id column: "CONTACT_QUERY_ID", generator: "increment"
		version false
	}

	static constraints = {
		name maxSize: 50
		phoneNo maxSize: 20
		email maxSize: 50
		address nullable: true
	}
}
