package com.connComm.masters

class Role {

	String roleName
	Boolean defaultFlag
	CommunityMaster communityMaster

	static hasMany = [communityPendingRegistrations: CommunityPendingRegistration,
	                  usersRoles: UsersRole]
	static belongsTo = [CommunityMaster]

	static mapping = {
		id column: "ROLE_ID", generator: "increment"
		columns {
			communityMaster column:'community_id'
		}
		version false
	}

	static constraints = {
		roleName maxSize: 50
	}
}
