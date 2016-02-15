package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UsersStatusController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    UsersStatusController(){
        super(UsersStatus)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UsersStatus.list(params), model:[usersStatusCount: UsersStatus.count()]
    }

    def show(UsersStatus usersStatus) {
        respond usersStatus
    }

    def create() {
        respond new UsersStatus(params)
    }

    @Transactional
    def save(UsersStatus usersStatus) {
        if (usersStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usersStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usersStatus.errors, view:'create'
            return
        }

        usersStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usersStatus.label', default: 'UsersStatus'), usersStatus.id])
                redirect usersStatus
            }
            '*' { respond usersStatus, [status: CREATED] }
        }
    }

    def edit(UsersStatus usersStatus) {
        respond usersStatus
    }

    @Transactional
    def update(UsersStatus usersStatus) {
        if (usersStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usersStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usersStatus.errors, view:'edit'
            return
        }

        usersStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usersStatus.label', default: 'UsersStatus'), usersStatus.id])
                redirect usersStatus
            }
            '*'{ respond usersStatus, [status: OK] }
        }
    }

    @Transactional
    def delete(UsersStatus usersStatus) {

        if (usersStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        usersStatus.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'usersStatus.label', default: 'UsersStatus'), usersStatus.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usersStatus.label', default: 'UsersStatus'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
