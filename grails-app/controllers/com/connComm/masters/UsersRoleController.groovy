package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UsersRoleController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    UsersRoleController(){
        super(UsersRole)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UsersRole.list(params), model:[usersRoleCount: UsersRole.count()]
    }

    def show(UsersRole usersRole) {
        respond usersRole
    }

    def create() {
        respond new UsersRole(params)
    }

    @Transactional
    def save(UsersRole usersRole) {
        if (usersRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usersRole.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usersRole.errors, view:'create'
            return
        }

        usersRole.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usersRole.label', default: 'UsersRole'), usersRole.id])
                redirect usersRole
            }
            '*' { respond usersRole, [status: CREATED] }
        }
    }

    def edit(UsersRole usersRole) {
        respond usersRole
    }

    @Transactional
    def update(UsersRole usersRole) {
        if (usersRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usersRole.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usersRole.errors, view:'edit'
            return
        }

        usersRole.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usersRole.label', default: 'UsersRole'), usersRole.id])
                redirect usersRole
            }
            '*'{ respond usersRole, [status: OK] }
        }
    }

    @Transactional
    def delete(UsersRole usersRole) {

        if (usersRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        usersRole.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'usersRole.label', default: 'UsersRole'), usersRole.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usersRole.label', default: 'UsersRole'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
