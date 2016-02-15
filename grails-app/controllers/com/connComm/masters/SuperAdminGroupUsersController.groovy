package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SuperAdminGroupUsersController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    SuperAdminGroupUsersController(){
        super(SuperAdminGroupUsers)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SuperAdminGroupUsers.list(params), model:[superAdminGroupUsersCount: SuperAdminGroupUsers.count()]
    }

    def show(SuperAdminGroupUsers superAdminGroupUsers) {
        respond superAdminGroupUsers
    }

    def create() {
        respond new SuperAdminGroupUsers(params)
    }

    @Transactional
    def save(SuperAdminGroupUsers superAdminGroupUsers) {
        if (superAdminGroupUsers == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (superAdminGroupUsers.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond superAdminGroupUsers.errors, view:'create'
            return
        }

        superAdminGroupUsers.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'superAdminGroupUsers.label', default: 'SuperAdminGroupUsers'), superAdminGroupUsers.id])
                redirect superAdminGroupUsers
            }
            '*' { respond superAdminGroupUsers, [status: CREATED] }
        }
    }

    def edit(SuperAdminGroupUsers superAdminGroupUsers) {
        respond superAdminGroupUsers
    }

    @Transactional
    def update(SuperAdminGroupUsers superAdminGroupUsers) {
        if (superAdminGroupUsers == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (superAdminGroupUsers.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond superAdminGroupUsers.errors, view:'edit'
            return
        }

        superAdminGroupUsers.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'superAdminGroupUsers.label', default: 'SuperAdminGroupUsers'), superAdminGroupUsers.id])
                redirect superAdminGroupUsers
            }
            '*'{ respond superAdminGroupUsers, [status: OK] }
        }
    }

    @Transactional
    def delete(SuperAdminGroupUsers superAdminGroupUsers) {

        if (superAdminGroupUsers == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        superAdminGroupUsers.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'superAdminGroupUsers.label', default: 'SuperAdminGroupUsers'), superAdminGroupUsers.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'superAdminGroupUsers.label', default: 'SuperAdminGroupUsers'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
