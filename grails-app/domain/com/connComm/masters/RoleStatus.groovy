package com.connComm.masters

class RoleStatus {

	String roleStatusName

	static hasMany = [usersRoles: UsersRole]

	static mapping = {
		id column: "ROLE_STATUS_ID", generator: "increment"
		version false
	}

	static constraints = {
		roleStatusName maxSize: 50
	}
}
