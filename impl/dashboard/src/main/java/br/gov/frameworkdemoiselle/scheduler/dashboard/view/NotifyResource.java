/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.scheduler.dashboard.view;

import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author 70744416353
 */
//@PushEndpoint("/quartz")
public class NotifyResource {

    //@OnMessage(encoders = {JSONEncoder.class})
    public String onMessage(String data) {
        return StringEscapeUtils.escapeHtml(data);
    }

}
