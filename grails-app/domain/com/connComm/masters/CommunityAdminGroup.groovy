package com.connComm.masters

class CommunityAdminGroup {

	Integer adminPriorityOrder
	CommunityMaster communityMaster
	Users users

	static belongsTo = [CommunityMaster, Users]

	static mapping = {
		id column: "COMMUNITY_ADMIN_GROUP_ID", generator: "increment"
		version false
	}
}
