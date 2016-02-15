package com.connComm.masters

class CommunityType {

	String communityTypeName

	static hasMany = [communityMasters: CommunityMaster]

	static mapping = {
		id column: "COMMUNITY_TYPE_ID", generator: "increment"
		version false
	}

	static constraints = {
		communityTypeName maxSize: 50
	}
}
