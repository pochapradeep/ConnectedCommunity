package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class QueryStatusController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['html', 'json']


    QueryStatusController(){
        super(QueryStatus)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond QueryStatus.list(params), model:[queryStatusCount: QueryStatus.count()]
    }

    def show(QueryStatus queryStatus) {
        respond queryStatus
    }

    def create() {
        respond new QueryStatus(params)
    }

    @Transactional
    def save(QueryStatus queryStatus) {
        if (queryStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (queryStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond queryStatus.errors, view:'create'
            return
        }

        queryStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'queryStatus.label', default: 'QueryStatus'), queryStatus.id])
                redirect queryStatus
            }
            '*' { respond queryStatus, [status: CREATED] }
        }
    }

    def edit(QueryStatus queryStatus) {
        respond queryStatus
    }

    @Transactional
    def update(QueryStatus queryStatus) {
        if (queryStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (queryStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond queryStatus.errors, view:'edit'
            return
        }

        queryStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'queryStatus.label', default: 'QueryStatus'), queryStatus.id])
                redirect queryStatus
            }
            '*'{ respond queryStatus, [status: OK] }
        }
    }

    @Transactional
    def delete(QueryStatus queryStatus) {

        if (queryStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        queryStatus.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'queryStatus.label', default: 'QueryStatus'), queryStatus.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'queryStatus.label', default: 'QueryStatus'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
