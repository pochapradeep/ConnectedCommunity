package com.connComm.masters

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class ResidentRelationMapping implements Serializable {

	ResidentMaster residentMasterByPrimaryResidentId
	RelationshipType relationshipType
	ResidentMaster residentMasterBySecondaryResidentId

	static belongsTo = [RelationshipType, ResidentMaster]

	static mapping = {
		
		columns {
			residentMasterByPrimaryResidentId column:'resident_master_id'
			residentMasterBySecondaryResidentId column:'resident_master_id', updateable: false, insertable: false
		}
		version false
	}
}
