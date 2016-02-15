class UrlMappings {

    static mappings = {
        "/communityLicenseTypes"(resources:'communityLicenseType')
        "/addressTemplates"(resources:"addressTemplate")
        "/addressTemplateGroups"(resources:"addressTemplateGroup")
        "/communityAdminGroups"(resources:"communityAdminGroup")
        "/communityMaster"(resources:"communityMaster")
        "/communityPendingRegistrations"(resources:"communityPendingRegistration")
        "/communityStatus"(resources:"communityStatus")
        "/communityTypes"(resources:"communityType")
        "/communityVendorDetails"(resources:"communityVendorDetails")
        "/contactQuery"(resources:"contactQuery")
        "/personalInformation"(resources:"personalInformation")
        "/productCategory"(resources:"productCategory")
        "/queryStatus"(resources:"queryStatus")
        "/recurringTripTypes"(resources:"recurringTripType")
        "/registeredCities"(resources:"registeredCities")
        "/relationshipTypes"(resources:"relationshipType")
        "/residenceUnits"(resources:"residenceUnit")
        "/residentCustomFields"(resources:"residentCustomField")
        "/residentCustomFieldValues"(resources:"residentCustomFieldValues")
        "/residentLocations"(resources:"residentLocation")
        "/residentMasters"(resources:"residentMaster")
        "/residentProofs"(resources:"residentProof")
        "/residentRelationMappings"(resources:"residentRelationMapping")
        "/residentStatus"(resources:"residentStatus")
        "/roles"(resources:"role")
        "/roleStatus"(resources:"roleStatus")
        "/sellBuyMasters"(resources:"sellBuyMaster")
        "/sellingStatus"(resources:"sellingStatus")
        "/superAdminGroupUsers"(resources:"superAdminGroupUsers")
        "/tripRegistry"(resources:"tripRegistry")
        "/tripSchedules"(resources:"tripSchedule")
        "/userResidentMappings"(resources:"userResidentMapping")
        "/users"(resources:"users")
        "/usersRoles"(resources:"usersRole")
        "/usersStatus"(resources:"usersStatus")
        "/vehicleDetails"(resources:"vehicleDetails")
        "/vehicleTypes"(resources:"vehicleType")
        "/vendorTypes"(resources:"vendorType")
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
