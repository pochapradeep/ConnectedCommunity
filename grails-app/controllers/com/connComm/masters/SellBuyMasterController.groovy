package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SellBuyMasterController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    SellBuyMasterController(){
        super(SellBuyMaster)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SellBuyMaster.list(params), model:[sellBuyMasterCount: SellBuyMaster.count()]
    }

    def show(SellBuyMaster sellBuyMaster) {
        respond sellBuyMaster
    }

    def create() {
        respond new SellBuyMaster(params)
    }

    @Transactional
    def save(SellBuyMaster sellBuyMaster) {
        if (sellBuyMaster == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sellBuyMaster.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sellBuyMaster.errors, view:'create'
            return
        }

        sellBuyMaster.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sellBuyMaster.label', default: 'SellBuyMaster'), sellBuyMaster.id])
                redirect sellBuyMaster
            }
            '*' { respond sellBuyMaster, [status: CREATED] }
        }
    }

    def edit(SellBuyMaster sellBuyMaster) {
        respond sellBuyMaster
    }

    @Transactional
    def update(SellBuyMaster sellBuyMaster) {
        if (sellBuyMaster == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sellBuyMaster.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sellBuyMaster.errors, view:'edit'
            return
        }

        sellBuyMaster.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sellBuyMaster.label', default: 'SellBuyMaster'), sellBuyMaster.id])
                redirect sellBuyMaster
            }
            '*'{ respond sellBuyMaster, [status: OK] }
        }
    }

    @Transactional
    def delete(SellBuyMaster sellBuyMaster) {

        if (sellBuyMaster == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        sellBuyMaster.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sellBuyMaster.label', default: 'SellBuyMaster'), sellBuyMaster.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sellBuyMaster.label', default: 'SellBuyMaster'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
