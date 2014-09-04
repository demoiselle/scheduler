/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.scheduler.dashboard.view;

import br.gov.frameworkdemoiselle.scheduler.dashboard.business.QrtzCronTriggersBC;
import br.gov.frameworkdemoiselle.scheduler.dashboard.business.QrtzFiredTriggersBC;
import br.gov.frameworkdemoiselle.scheduler.dashboard.business.QrtzJobDetailsBC;
import br.gov.frameworkdemoiselle.scheduler.dashboard.business.QrtzSchedulerStateBC;
import br.gov.frameworkdemoiselle.scheduler.dashboard.business.QrtzTriggersBC;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzCronTriggers;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzFiredTriggers;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzJobDetails;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzSchedulerState;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzTriggers;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.util.Beans;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

@ViewController
public class QuartzMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private MindmapNode root;

    private MindmapNode selectedNode;

    private Long timeCheckin = 20l;

    //@Inject
    private QrtzSchedulerStateBC qrtzSchedulerStateBC = Beans.getReference(QrtzSchedulerStateBC.class);
    //@Inject
    private QrtzTriggersBC qrtzTriggersBC = Beans.getReference(QrtzTriggersBC.class);
    //@Inject
    private QrtzJobDetailsBC qrtzJobDetailsBC = Beans.getReference(QrtzJobDetailsBC.class);
    //@Inject
    private QrtzFiredTriggersBC qrtzFiredTriggersBC = Beans.getReference(QrtzFiredTriggersBC.class);

    public QuartzMB() {

    }

    public MindmapNode getRoot() {

        List<QrtzSchedulerState> listSchedulerState = qrtzSchedulerStateBC.findAll();

        String name = listSchedulerState.get(0).getQrtzSchedulerStatePK().getSchedName();
        timeCheckin = (listSchedulerState.get(0).getCheckinInterval() / 1000);

        root = new DefaultMindmapNode(name, name, "E0FFFF", false);
        MindmapNode ips;

        for (QrtzSchedulerState object : listSchedulerState) {

            List<QrtzFiredTriggers> lista = findQrtzFiredTriggers(object.getQrtzSchedulerStatePK().getInstanceName());

            if (lista != null && !lista.isEmpty()) {

                ips = new DefaultMindmapNode(object.getQrtzSchedulerStatePK().getInstanceName(), object.getQrtzSchedulerStatePK().getInstanceName() + " # Last Checkin -" + convertTime(object.getLastCheckinTime()), "00FF7F", true);

                for (QrtzFiredTriggers qrtzFiredTriggers : lista) {
                    ips.addNode(new DefaultMindmapNode(qrtzFiredTriggers.getTriggerName(), " # Fire time " + convertTime(qrtzFiredTriggers.getFiredTime()) + " # Sched time " + convertTime(qrtzFiredTriggers.getSchedTime()), "FF0000", true));
                }

            } else {
                ips = new DefaultMindmapNode(object.getQrtzSchedulerStatePK().getInstanceName(), object.getQrtzSchedulerStatePK().getInstanceName() + " # Last Checkin -" + convertTime(object.getLastCheckinTime()), "D3D3D3", true);
            }

            root.addNode(ips);
        }
        return root;
    }

    private List<QrtzFiredTriggers> findQrtzFiredTriggers(String instanceName) {
        return qrtzFiredTriggersBC.findQrtzFiredTriggers(instanceName);
    }

    public Long getTimeCheckin() {
        return timeCheckin;
    }

    public List<QrtzJobDetails> getListQrtzJobDetails() {
        return qrtzJobDetailsBC.findAll();
    }

    public List<QrtzTriggers> getListQrtzTriggerses() {
        return qrtzTriggersBC.findAll();
    }

    public MindmapNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void onNodeDblselect(SelectEvent event) {
        this.selectedNode = (MindmapNode) event.getObject();
    }

    public void onNodeSelect(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();
    }

    public String convertTime(long time) {
        if (time > 0) {
            Date date = new Date(time);
            Format format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return format.format(date);
        }
        return "-";
    }
}
