package com.connComm.masters

class ResidentProof {

	String idProofDocName
	byte[] idProofDoc
	String addressProofDocName
	byte[] addressProofDoc
	CommunityPendingRegistration communityPendingRegistration

	static belongsTo = [CommunityPendingRegistration]

	static mapping = {
		id column: "resident_proof_id", generator: "increment"
		
		columns {
			communityPendingRegistration column:'community_pending_registration_id'
		}
		
		version false
	}
}
