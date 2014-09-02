/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.scheduler.dashboard.business;

import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzJobDetails;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzJobDetailsPK;
import br.gov.frameworkdemoiselle.scheduler.dashboard.persistence.QrtzJobDetailsDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

/**
 *
 * @author 70744416353
 */
@BusinessController
public class QrtzJobDetailsBC extends DelegateCrud<QrtzJobDetails, QrtzJobDetailsPK, QrtzJobDetailsDAO> {

    private static final long serialVersionUID = 1L;

}
