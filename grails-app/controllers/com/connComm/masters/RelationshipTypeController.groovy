package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RelationshipTypeController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['html', 'json']


    RelationshipTypeController(){
        super(RelationshipType)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RelationshipType.list(params), model:[relationshipTypeCount: RelationshipType.count()]
    }

    def show(RelationshipType relationshipType) {
        respond relationshipType
    }

    def create() {
        respond new RelationshipType(params)
    }

    @Transactional
    def save(RelationshipType relationshipType) {
        if (relationshipType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (relationshipType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond relationshipType.errors, view:'create'
            return
        }

        relationshipType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'relationshipType.label', default: 'RelationshipType'), relationshipType.id])
                redirect relationshipType
            }
            '*' { respond relationshipType, [status: CREATED] }
        }
    }

    def edit(RelationshipType relationshipType) {
        respond relationshipType
    }

    @Transactional
    def update(RelationshipType relationshipType) {
        if (relationshipType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (relationshipType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond relationshipType.errors, view:'edit'
            return
        }

        relationshipType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'relationshipType.label', default: 'RelationshipType'), relationshipType.id])
                redirect relationshipType
            }
            '*'{ respond relationshipType, [status: OK] }
        }
    }

    @Transactional
    def delete(RelationshipType relationshipType) {

        if (relationshipType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        relationshipType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'relationshipType.label', default: 'RelationshipType'), relationshipType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'relationshipType.label', default: 'RelationshipType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
