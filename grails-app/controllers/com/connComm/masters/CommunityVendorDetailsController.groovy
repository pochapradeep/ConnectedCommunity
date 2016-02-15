package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CommunityVendorDetailsController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    CommunityVendorDetailsController(){
        super(CommunityVendorDetails)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CommunityVendorDetails.list(params), model:[communityVendorDetailsCount: CommunityVendorDetails.count()]
    }

    def show(CommunityVendorDetails communityVendorDetails) {
        respond communityVendorDetails
    }

    def create() {
        respond new CommunityVendorDetails(params)
    }

    @Transactional
    def save(CommunityVendorDetails communityVendorDetails) {
        if (communityVendorDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (communityVendorDetails.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityVendorDetails.errors, view:'create'
            return
        }

        communityVendorDetails.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'communityVendorDetails.label', default: 'CommunityVendorDetails'), communityVendorDetails.id])
                redirect communityVendorDetails
            }
            '*' { respond communityVendorDetails, [status: CREATED] }
        }
    }

    def edit(CommunityVendorDetails communityVendorDetails) {
        respond communityVendorDetails
    }

    @Transactional
    def update(CommunityVendorDetails communityVendorDetails) {
        if (communityVendorDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (communityVendorDetails.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityVendorDetails.errors, view:'edit'
            return
        }

        communityVendorDetails.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'communityVendorDetails.label', default: 'CommunityVendorDetails'), communityVendorDetails.id])
                redirect communityVendorDetails
            }
            '*'{ respond communityVendorDetails, [status: OK] }
        }
    }

    @Transactional
    def delete(CommunityVendorDetails communityVendorDetails) {

        if (communityVendorDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        communityVendorDetails.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'communityVendorDetails.label', default: 'CommunityVendorDetails'), communityVendorDetails.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'communityVendorDetails.label', default: 'CommunityVendorDetails'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
