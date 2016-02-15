package com.connComm.masters

class RelationshipType {

	String relationshipTypeName

	static hasMany = [residentRelationMappings: ResidentRelationMapping]

	static mapping = {
		id column: "RELATIONSHIP_TYPE_ID", generator: "increment"
		version false
	}

	static constraints = {
		relationshipTypeName maxSize: 50
	}
}
