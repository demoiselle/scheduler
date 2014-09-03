/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.scheduler.dashboard.business;

import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzCronTriggers;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzCronTriggersPK;
import br.gov.frameworkdemoiselle.scheduler.dashboard.persistence.QrtzCronTriggersDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

/**
 *
 * @author 70744416353
 */
@BusinessController
public class QrtzCronTriggersBC extends DelegateCrud<QrtzCronTriggers, QrtzCronTriggersPK, QrtzCronTriggersDAO> {

    private static final long serialVersionUID = 1L;

    public QrtzCronTriggers finQrtzCronTriggers(String instanceName) {
        return getDelegate().finQrtzCronTriggers(instanceName);
    }

}
