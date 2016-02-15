package com.connComm.masters

class UsersStatus {

	String usersStatusName

	static hasMany = [userses: Users]

	static mapping = {
		id column: "USERS_STATUS_ID", generator: "increment"
		version false
	}

	static constraints = {
		usersStatusName maxSize: 50
	}
}
