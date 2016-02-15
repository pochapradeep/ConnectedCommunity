package com.connComm.masters

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.rest.RestfulController

@Transactional(readOnly = true)
class CommunityLicenseTypeController extends RestfulController {

    static responseFormats = ['html', 'json']

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    CommunityLicenseTypeController(){
        super(CommunityLicenseType)
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CommunityLicenseType.list(params), model:[communityLicenseTypeCount: CommunityLicenseType.count()]
    }

    def show(CommunityLicenseType communityLicenseType) {
        respond communityLicenseType, formats:['xml', 'json', 'html']
    }

    def create() {
        respond new CommunityLicenseType(params)
    }

    @Transactional
    def save(CommunityLicenseType communityLicenseType) {
        if (communityLicenseType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (communityLicenseType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityLicenseType.errors, view:'create'
            return
        }

        communityLicenseType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'communityLicenseType.label', default: 'CommunityLicenseType'), communityLicenseType.id])
                redirect communityLicenseType
            }
            '*' { respond communityLicenseType, [status: CREATED] }
        }
    }

    def edit(CommunityLicenseType communityLicenseType) {
        respond communityLicenseType
    }

    @Transactional
    def update(CommunityLicenseType communityLicenseType) {
        if (communityLicenseType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (communityLicenseType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityLicenseType.errors, view:'edit'
            return
        }

        communityLicenseType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'communityLicenseType.label', default: 'CommunityLicenseType'), communityLicenseType.id])
                redirect communityLicenseType
            }
            '*'{ respond communityLicenseType, [status: OK] }
        }
    }

    @Transactional
    def delete(CommunityLicenseType communityLicenseType) {

        if (communityLicenseType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        communityLicenseType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'communityLicenseType.label', default: 'CommunityLicenseType'), communityLicenseType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'communityLicenseType.label', default: 'CommunityLicenseType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
