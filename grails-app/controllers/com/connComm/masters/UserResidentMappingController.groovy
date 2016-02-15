package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserResidentMappingController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    UserResidentMappingController(){
        super(UserResidentMapping)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserResidentMapping.list(params), model:[userResidentMappingCount: UserResidentMapping.count()]
    }

    def show(UserResidentMapping userResidentMapping) {
        respond userResidentMapping
    }

    def create() {
        respond new UserResidentMapping(params)
    }

    @Transactional
    def save(UserResidentMapping userResidentMapping) {
        if (userResidentMapping == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userResidentMapping.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userResidentMapping.errors, view:'create'
            return
        }

        userResidentMapping.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userResidentMapping.label', default: 'UserResidentMapping'), userResidentMapping.id])
                redirect userResidentMapping
            }
            '*' { respond userResidentMapping, [status: CREATED] }
        }
    }

    def edit(UserResidentMapping userResidentMapping) {
        respond userResidentMapping
    }

    @Transactional
    def update(UserResidentMapping userResidentMapping) {
        if (userResidentMapping == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userResidentMapping.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userResidentMapping.errors, view:'edit'
            return
        }

        userResidentMapping.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userResidentMapping.label', default: 'UserResidentMapping'), userResidentMapping.id])
                redirect userResidentMapping
            }
            '*'{ respond userResidentMapping, [status: OK] }
        }
    }

    @Transactional
    def delete(UserResidentMapping userResidentMapping) {

        if (userResidentMapping == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        userResidentMapping.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userResidentMapping.label', default: 'UserResidentMapping'), userResidentMapping.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userResidentMapping.label', default: 'UserResidentMapping'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
