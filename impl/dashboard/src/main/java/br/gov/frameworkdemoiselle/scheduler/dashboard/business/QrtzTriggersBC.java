/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.scheduler.dashboard.business;

import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzTriggers;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzTriggersPK;
import br.gov.frameworkdemoiselle.scheduler.dashboard.persistence.QrtzTriggersDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

/**
 *
 * @author 70744416353
 */
@BusinessController
public class QrtzTriggersBC extends DelegateCrud<QrtzTriggers, QrtzTriggersPK, QrtzTriggersDAO> {

    private static final long serialVersionUID = 1L;

}
