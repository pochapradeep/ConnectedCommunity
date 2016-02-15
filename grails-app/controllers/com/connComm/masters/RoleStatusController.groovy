package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RoleStatusController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    RoleStatusController(){
        super(RoleStatus)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RoleStatus.list(params), model:[roleStatusCount: RoleStatus.count()]
    }

    def show(RoleStatus roleStatus) {
        respond roleStatus
    }

    def create() {
        respond new RoleStatus(params)
    }

    @Transactional
    def save(RoleStatus roleStatus) {
        if (roleStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (roleStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond roleStatus.errors, view:'create'
            return
        }

        roleStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'roleStatus.label', default: 'RoleStatus'), roleStatus.id])
                redirect roleStatus
            }
            '*' { respond roleStatus, [status: CREATED] }
        }
    }

    def edit(RoleStatus roleStatus) {
        respond roleStatus
    }

    @Transactional
    def update(RoleStatus roleStatus) {
        if (roleStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (roleStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond roleStatus.errors, view:'edit'
            return
        }

        roleStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'roleStatus.label', default: 'RoleStatus'), roleStatus.id])
                redirect roleStatus
            }
            '*'{ respond roleStatus, [status: OK] }
        }
    }

    @Transactional
    def delete(RoleStatus roleStatus) {

        if (roleStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        roleStatus.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'roleStatus.label', default: 'RoleStatus'), roleStatus.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'roleStatus.label', default: 'RoleStatus'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
