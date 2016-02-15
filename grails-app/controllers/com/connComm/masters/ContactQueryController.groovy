package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ContactQueryController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    ContactQueryController(){
        super(ContactQuery)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ContactQuery.list(params), model:[contactQueryCount: ContactQuery.count()]
    }

    def show(ContactQuery contactQuery) {
        respond contactQuery
    }

    def create() {
        respond new ContactQuery(params)
    }

    @Transactional
    def save(ContactQuery contactQuery) {
        if (contactQuery == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (contactQuery.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contactQuery.errors, view:'create'
            return
        }

        contactQuery.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contactQuery.label', default: 'ContactQuery'), contactQuery.id])
                redirect contactQuery
            }
            '*' { respond contactQuery, [status: CREATED] }
        }
    }

    def edit(ContactQuery contactQuery) {
        respond contactQuery
    }

    @Transactional
    def update(ContactQuery contactQuery) {
        if (contactQuery == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (contactQuery.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contactQuery.errors, view:'edit'
            return
        }

        contactQuery.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'contactQuery.label', default: 'ContactQuery'), contactQuery.id])
                redirect contactQuery
            }
            '*'{ respond contactQuery, [status: OK] }
        }
    }

    @Transactional
    def delete(ContactQuery contactQuery) {

        if (contactQuery == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        contactQuery.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'contactQuery.label', default: 'ContactQuery'), contactQuery.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contactQuery.label', default: 'ContactQuery'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
