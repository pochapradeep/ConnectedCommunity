package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CommunityMasterController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['html', 'json']


    CommunityMasterController(){
        super(CommunityMaster)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CommunityMaster.list(params), model:[communityMasterCount: CommunityMaster.count()]
    }

    def show(CommunityMaster communityMaster) {
        respond communityMaster
    }

    def create() {
        respond new CommunityMaster(params)
    }

    @Transactional
    def save(CommunityMaster communityMaster) {
        if (communityMaster == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (communityMaster.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityMaster.errors, view:'create'
            return
        }

        communityMaster.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'communityMaster.label', default: 'CommunityMaster'), communityMaster.id])
                redirect communityMaster
            }
            '*' { respond communityMaster, [status: CREATED] }
        }
    }

    def edit(CommunityMaster communityMaster) {
        respond communityMaster
    }

    @Transactional
    def update(CommunityMaster communityMaster) {
        if (communityMaster == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (communityMaster.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityMaster.errors, view:'edit'
            return
        }

        communityMaster.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'communityMaster.label', default: 'CommunityMaster'), communityMaster.id])
                redirect communityMaster
            }
            '*'{ respond communityMaster, [status: OK] }
        }
    }

    @Transactional
    def delete(CommunityMaster communityMaster) {

        if (communityMaster == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        communityMaster.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'communityMaster.label', default: 'CommunityMaster'), communityMaster.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'communityMaster.label', default: 'CommunityMaster'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
