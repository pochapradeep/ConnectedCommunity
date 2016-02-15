package com.connComm.masters

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class ResidentRelationMapping implements Serializable {

	Integer primaryResidentId
	Integer secondaryResidentId
	ResidentMaster residentMasterByPrimaryResidentId
	RelationshipType relationshipType
	ResidentMaster residentMasterBySecondaryResidentId

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append primaryResidentId
		builder.append secondaryResidentId
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null) return false
		def builder = new EqualsBuilder()
		builder.append primaryResidentId, other.primaryResidentId
		builder.append secondaryResidentId, other.secondaryResidentId
		builder.isEquals()
	}

	static belongsTo = [RelationshipType, ResidentMaster]

	static mapping = {
		id composite: ["primaryResidentId", "secondaryResidentId"]
		version false
	}
}
