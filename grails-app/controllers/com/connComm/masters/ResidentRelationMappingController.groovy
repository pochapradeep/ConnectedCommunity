package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResidentRelationMappingController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    ResidentRelationMappingController(){
        super(ResidentRelationMapping)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ResidentRelationMapping.list(params), model:[residentRelationMappingCount: ResidentRelationMapping.count()]
    }

    def show(ResidentRelationMapping residentRelationMapping) {
        respond residentRelationMapping
    }

    def create() {
        respond new ResidentRelationMapping(params)
    }

    @Transactional
    def save(ResidentRelationMapping residentRelationMapping) {
        if (residentRelationMapping == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residentRelationMapping.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residentRelationMapping.errors, view:'create'
            return
        }

        residentRelationMapping.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'residentRelationMapping.label', default: 'ResidentRelationMapping'), residentRelationMapping.id])
                redirect residentRelationMapping
            }
            '*' { respond residentRelationMapping, [status: CREATED] }
        }
    }

    def edit(ResidentRelationMapping residentRelationMapping) {
        respond residentRelationMapping
    }

    @Transactional
    def update(ResidentRelationMapping residentRelationMapping) {
        if (residentRelationMapping == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residentRelationMapping.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residentRelationMapping.errors, view:'edit'
            return
        }

        residentRelationMapping.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'residentRelationMapping.label', default: 'ResidentRelationMapping'), residentRelationMapping.id])
                redirect residentRelationMapping
            }
            '*'{ respond residentRelationMapping, [status: OK] }
        }
    }

    @Transactional
    def delete(ResidentRelationMapping residentRelationMapping) {

        if (residentRelationMapping == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        residentRelationMapping.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'residentRelationMapping.label', default: 'ResidentRelationMapping'), residentRelationMapping.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'residentRelationMapping.label', default: 'ResidentRelationMapping'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
