/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.scheduler.dashboard.persistence;

import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzTriggers;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzTriggersPK;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

/**
 *
 * @author 70744416353
 */
@PersistenceController
public class QrtzTriggersDAO extends JPACrud<QrtzTriggers, QrtzTriggersPK> {

    private static final long serialVersionUID = 1L;

}
