package com.connComm.masters

class CommunityStatus {

	String communityStatusName

	static hasMany = [communityMasters: CommunityMaster]

	static mapping = {
		id column: "COMMUNITY_STATUS_ID", generator: "increment"
		version false
	}

	static constraints = {
		communityStatusName maxSize: 50
	}
}
