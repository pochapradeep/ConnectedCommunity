package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CommunityAdminGroupController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['html', 'json']


    CommunityAdminGroupController(){
        super(CommunityAdminGroup)
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CommunityAdminGroup.list(params), model:[communityAdminGroupCount: CommunityAdminGroup.count()]
    }

    def show(CommunityAdminGroup communityAdminGroup) {
        respond communityAdminGroup
    }

    def create() {
        respond new CommunityAdminGroup(params)
    }

    @Transactional
    def save(CommunityAdminGroup communityAdminGroup) {
        if (communityAdminGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (communityAdminGroup.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityAdminGroup.errors, view:'create'
            return
        }

        communityAdminGroup.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'communityAdminGroup.label', default: 'CommunityAdminGroup'), communityAdminGroup.id])
                redirect communityAdminGroup
            }
            '*' { respond communityAdminGroup, [status: CREATED] }
        }
    }

    def edit(CommunityAdminGroup communityAdminGroup) {
        respond communityAdminGroup
    }

    @Transactional
    def update(CommunityAdminGroup communityAdminGroup) {
        if (communityAdminGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (communityAdminGroup.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityAdminGroup.errors, view:'edit'
            return
        }

        communityAdminGroup.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'communityAdminGroup.label', default: 'CommunityAdminGroup'), communityAdminGroup.id])
                redirect communityAdminGroup
            }
            '*'{ respond communityAdminGroup, [status: OK] }
        }
    }

    @Transactional
    def delete(CommunityAdminGroup communityAdminGroup) {

        if (communityAdminGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        communityAdminGroup.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'communityAdminGroup.label', default: 'CommunityAdminGroup'), communityAdminGroup.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'communityAdminGroup.label', default: 'CommunityAdminGroup'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
