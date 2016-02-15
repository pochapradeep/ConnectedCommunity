package com.connComm.masters

class SuperAdminGroupUsers {

	String superAdminGroupUsersFirstName
	String superAdminGroupUsersLastName
	String superAdminGroupUsersPhoneNo
	String superAdminGroupUsersEmail
	Users users

	static belongsTo = [Users]

	static mapping = {
		id column: "SUPER_ADMIN_GROUP_USERS_ID", generator: "increment"
		version false
	}

	static constraints = {
		superAdminGroupUsersFirstName maxSize: 50
		superAdminGroupUsersLastName maxSize: 50
		superAdminGroupUsersPhoneNo maxSize: 20
		superAdminGroupUsersEmail maxSize: 50
	}
}
