package com.connComm.masters

class ResidentMaster {

	Boolean residentOwnerFlag
	Boolean primaryUserFlag
	Boolean residingFlag
	ResidenceUnit residenceUnit
	CommunityMaster communityMaster
	PersonalInformation personalInformation
	ResidentStatus residentStatus

	static hasMany = [residentCustomFieldValueses: ResidentCustomFieldValues,
	                  residentLocations: ResidentLocation,
	                  residentRelationMappingsForPrimaryResidentId: ResidentRelationMapping,
	                  residentRelationMappingsForSecondaryResidentId: ResidentRelationMapping,
	                  sellBuyMastersForBuyerId: SellBuyMaster,
	                  sellBuyMastersForSellerId: SellBuyMaster,
	                  tripRegistries: TripRegistry,
	                  tripSchedules: TripSchedule,
	                  userResidentMappings: UserResidentMapping,
	                  vehicleDetailses: VehicleDetails,
	                  vendorTypes: VendorType]
	static belongsTo = [CommunityMaster, PersonalInformation, ResidenceUnit, ResidentStatus,VendorType]

	// TODO you have multiple hasMany references for class(es) [ResidentRelationMapping, SellBuyMaster] 
	//      so you'll need to disambiguate them with the 'mappedBy' property:
	static mappedBy = [residentRelationMappingsForPrimaryResidentId: "residentMasterByPrimaryResidentId",
	                   residentRelationMappingsForSecondaryResidentId: "residentMasterBySecondaryResidentId",
	                   sellBuyMastersForBuyerId: "residentMasterByBuyerId",
	                   sellBuyMastersForSellerId: "residentMasterBySellerId"]

	
	static mapping = {
		id column: "RESIDENT_MASTER_ID", generator: "increment"
		
		columns {
			communityMaster column:'community_id'
		}
		version false
	}
}
