package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PersonalInformationController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['html', 'json']


    PersonalInformationController(){
        super(PersonalInformation)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PersonalInformation.list(params), model:[personalInformationCount: PersonalInformation.count()]
    }

    def show(PersonalInformation personalInformation) {
        respond personalInformation
    }

    def create() {
        respond new PersonalInformation(params)
    }

    @Transactional
    def save(PersonalInformation personalInformation) {
        if (personalInformation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (personalInformation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond personalInformation.errors, view:'create'
            return
        }

        personalInformation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'personalInformation.label', default: 'PersonalInformation'), personalInformation.id])
                redirect personalInformation
            }
            '*' { respond personalInformation, [status: CREATED] }
        }
    }

    def edit(PersonalInformation personalInformation) {
        respond personalInformation
    }

    @Transactional
    def update(PersonalInformation personalInformation) {
        if (personalInformation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (personalInformation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond personalInformation.errors, view:'edit'
            return
        }

        personalInformation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'personalInformation.label', default: 'PersonalInformation'), personalInformation.id])
                redirect personalInformation
            }
            '*'{ respond personalInformation, [status: OK] }
        }
    }

    @Transactional
    def delete(PersonalInformation personalInformation) {

        if (personalInformation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        personalInformation.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'personalInformation.label', default: 'PersonalInformation'), personalInformation.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'personalInformation.label', default: 'PersonalInformation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
