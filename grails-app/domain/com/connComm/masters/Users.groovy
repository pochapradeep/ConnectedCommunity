package com.connComm.masters

class Users {

	String userLoginId
	Byte superAdminFlag
	UsersStatus usersStatus

	static hasMany = [communityAdminGroups: CommunityAdminGroup,
	                  superAdminGroupUserses: SuperAdminGroupUsers,
	                  userResidentMappings: UserResidentMapping,
	                  usersRoles: UsersRole]
	static belongsTo = [UsersStatus]

	static mapping = {
		id column: "USERS_ID", generator: "increment"
		version false
	}

	static constraints = {
		userLoginId maxSize: 50
		superAdminFlag nullable: true
	}
}
