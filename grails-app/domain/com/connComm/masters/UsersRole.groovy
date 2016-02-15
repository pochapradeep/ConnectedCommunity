package com.connComm.masters

class UsersRole {

	Role role
	RoleStatus roleStatus
	Users users

	static belongsTo = [Role, RoleStatus, Users]

	static mapping = {
		id column: "USERS_ROLE_ID", generator: "increment"
		version false
	}
}
