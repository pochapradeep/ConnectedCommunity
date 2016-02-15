package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CommunityStatusController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['html', 'json']


    CommunityStatusController(){
        super(CommunityStatus)
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CommunityStatus.list(params), model:[communityStatusCount: CommunityStatus.count()]
    }

    def show(CommunityStatus communityStatus) {
        respond communityStatus
    }

    def create() {
        respond new CommunityStatus(params)
    }

    @Transactional
    def save(CommunityStatus communityStatus) {
        if (communityStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (communityStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityStatus.errors, view:'create'
            return
        }

        communityStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'communityStatus.label', default: 'CommunityStatus'), communityStatus.id])
                redirect communityStatus
            }
            '*' { respond communityStatus, [status: CREATED] }
        }
    }

    def edit(CommunityStatus communityStatus) {
        respond communityStatus
    }

    @Transactional
    def update(CommunityStatus communityStatus) {
        if (communityStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (communityStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityStatus.errors, view:'edit'
            return
        }

        communityStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'communityStatus.label', default: 'CommunityStatus'), communityStatus.id])
                redirect communityStatus
            }
            '*'{ respond communityStatus, [status: OK] }
        }
    }

    @Transactional
    def delete(CommunityStatus communityStatus) {

        if (communityStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        communityStatus.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'communityStatus.label', default: 'CommunityStatus'), communityStatus.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'communityStatus.label', default: 'CommunityStatus'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
