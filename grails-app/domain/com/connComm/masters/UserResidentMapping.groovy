package com.connComm.masters

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class UserResidentMapping implements Serializable {

	Integer usersId
	Integer residentMasterId
	ResidentMaster residentMaster
	Users users
	ResidentStatus residentStatus

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append usersId
		builder.append residentMasterId
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null) return false
		def builder = new EqualsBuilder()
		builder.append usersId, other.usersId
		builder.append residentMasterId, other.residentMasterId
		builder.isEquals()
	}

	static belongsTo = [ResidentMaster, ResidentStatus, Users]

	static mapping = {
		id composite: ["usersId", "residentMasterId"]
		version false
		columns {
			residentMaster column: 'residentMasterId',insert: false, update: false
			users column : 'usersId', insert: false, update: false
		}
	}
}
