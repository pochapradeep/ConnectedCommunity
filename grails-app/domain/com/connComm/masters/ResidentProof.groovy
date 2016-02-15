package com.connComm.masters

class ResidentProof {

	String idProofDocName
	byte[] idProofDoc
	String addressProofDocName
	byte[] addressProofDoc
	ResidentMaster residentMaster

	static belongsTo = [ResidentMaster]

	static mapping = {
		id column: "resident_proof_id", generator: "increment"
		version false
	}
}
