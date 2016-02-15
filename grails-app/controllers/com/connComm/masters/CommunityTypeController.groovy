package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CommunityTypeController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    CommunityTypeController(){
        super(CommunityType)
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CommunityType.list(params), model:[communityTypeCount: CommunityType.count()]
    }

    def show(CommunityType communityType) {
        respond communityType
    }

    def create() {
        respond new CommunityType(params)
    }

    @Transactional
    def save(CommunityType communityType) {
        if (communityType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (communityType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityType.errors, view:'create'
            return
        }

        communityType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'communityType.label', default: 'CommunityType'), communityType.id])
                redirect communityType
            }
            '*' { respond communityType, [status: CREATED] }
        }
    }

    def edit(CommunityType communityType) {
        respond communityType
    }

    @Transactional
    def update(CommunityType communityType) {
        if (communityType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (communityType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityType.errors, view:'edit'
            return
        }

        communityType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'communityType.label', default: 'CommunityType'), communityType.id])
                redirect communityType
            }
            '*'{ respond communityType, [status: OK] }
        }
    }

    @Transactional
    def delete(CommunityType communityType) {

        if (communityType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        communityType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'communityType.label', default: 'CommunityType'), communityType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'communityType.label', default: 'CommunityType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
