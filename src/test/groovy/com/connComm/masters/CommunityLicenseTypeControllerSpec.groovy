package com.connComm.masters

import grails.test.mixin.*
import spock.lang.*

@TestFor(CommunityLicenseTypeController)
@Mock(CommunityLicenseType)
class CommunityLicenseTypeControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.communityLicenseTypeList
            model.communityLicenseTypeCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.communityLicenseType!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def communityLicenseType = new CommunityLicenseType()
            communityLicenseType.validate()
            controller.save(communityLicenseType)

        then:"The create view is rendered again with the correct model"
            model.communityLicenseType!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            communityLicenseType = new CommunityLicenseType(params)

            controller.save(communityLicenseType)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/communityLicenseType/show/1'
            controller.flash.message != null
            CommunityLicenseType.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def communityLicenseType = new CommunityLicenseType(params)
            controller.show(communityLicenseType)

        then:"A model is populated containing the domain instance"
            model.communityLicenseType == communityLicenseType
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def communityLicenseType = new CommunityLicenseType(params)
            controller.edit(communityLicenseType)

        then:"A model is populated containing the domain instance"
            model.communityLicenseType == communityLicenseType
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/communityLicenseType/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def communityLicenseType = new CommunityLicenseType()
            communityLicenseType.validate()
            controller.update(communityLicenseType)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.communityLicenseType == communityLicenseType

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            communityLicenseType = new CommunityLicenseType(params).save(flush: true)
            controller.update(communityLicenseType)

        then:"A redirect is issued to the show action"
            communityLicenseType != null
            response.redirectedUrl == "/communityLicenseType/show/$communityLicenseType.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/communityLicenseType/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def communityLicenseType = new CommunityLicenseType(params).save(flush: true)

        then:"It exists"
            CommunityLicenseType.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(communityLicenseType)

        then:"The instance is deleted"
            CommunityLicenseType.count() == 0
            response.redirectedUrl == '/communityLicenseType/index'
            flash.message != null
    }
}
