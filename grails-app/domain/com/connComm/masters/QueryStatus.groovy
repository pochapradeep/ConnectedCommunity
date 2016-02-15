package com.connComm.masters

class QueryStatus {

	String queryStatusName

	static hasMany = [contactQueries: ContactQuery]

	static mapping = {
		id column: "QUERY_STATUS_ID", generator: "increment"
		version false
	}

	static constraints = {
		queryStatusName maxSize: 20
	}
}
