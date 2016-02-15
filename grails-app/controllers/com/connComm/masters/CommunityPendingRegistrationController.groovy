package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CommunityPendingRegistrationController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['html', 'json']


    CommunityPendingRegistrationController(){
        super(CommunityPendingRegistration)
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CommunityPendingRegistration.list(params), model:[communityPendingRegistrationCount: CommunityPendingRegistration.count()]
    }

    def show(CommunityPendingRegistration communityPendingRegistration) {
        respond communityPendingRegistration
    }

    def create() {
        respond new CommunityPendingRegistration(params)
    }

    @Transactional
    def save(CommunityPendingRegistration communityPendingRegistration) {
        if (communityPendingRegistration == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (communityPendingRegistration.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityPendingRegistration.errors, view:'create'
            return
        }

        communityPendingRegistration.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'communityPendingRegistration.label', default: 'CommunityPendingRegistration'), communityPendingRegistration.id])
                redirect communityPendingRegistration
            }
            '*' { respond communityPendingRegistration, [status: CREATED] }
        }
    }

    def edit(CommunityPendingRegistration communityPendingRegistration) {
        respond communityPendingRegistration
    }

    @Transactional
    def update(CommunityPendingRegistration communityPendingRegistration) {
        if (communityPendingRegistration == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (communityPendingRegistration.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityPendingRegistration.errors, view:'edit'
            return
        }

        communityPendingRegistration.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'communityPendingRegistration.label', default: 'CommunityPendingRegistration'), communityPendingRegistration.id])
                redirect communityPendingRegistration
            }
            '*'{ respond communityPendingRegistration, [status: OK] }
        }
    }

    @Transactional
    def delete(CommunityPendingRegistration communityPendingRegistration) {

        if (communityPendingRegistration == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        communityPendingRegistration.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'communityPendingRegistration.label', default: 'CommunityPendingRegistration'), communityPendingRegistration.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'communityPendingRegistration.label', default: 'CommunityPendingRegistration'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
