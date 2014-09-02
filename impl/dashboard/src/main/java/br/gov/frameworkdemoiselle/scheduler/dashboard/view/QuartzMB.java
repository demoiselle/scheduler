/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.scheduler.dashboard.view;

import br.gov.frameworkdemoiselle.scheduler.dashboard.business.QrtzJobDetailsBC;
import br.gov.frameworkdemoiselle.scheduler.dashboard.business.QrtzSchedulerStateBC;
import br.gov.frameworkdemoiselle.scheduler.dashboard.business.QrtzTriggersBC;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzJobDetails;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzSchedulerState;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzTriggers;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.util.Beans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

@ViewController
public class QuartzMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private MindmapNode root;

    private MindmapNode selectedNode;

    private final static String CHANNEL = "/quartz";

    private List<QrtzSchedulerState> listSchedulerState = new ArrayList<QrtzSchedulerState>();
    private List<QrtzJobDetails> listQrtzJobDetails = new ArrayList<QrtzJobDetails>();
    private List<QrtzTriggers> listQrtzTriggerses = new ArrayList<QrtzTriggers>();

    //@Inject
    private QrtzSchedulerStateBC qrtzSchedulerStateBC = Beans.getReference(QrtzSchedulerStateBC.class);
    //@Inject
    private QrtzTriggersBC qrtzTriggersBC = Beans.getReference(QrtzTriggersBC.class);
    //@Inject
    private QrtzJobDetailsBC qrtzJobDetailsBC = Beans.getReference(QrtzJobDetailsBC.class);

    public QuartzMB() {
        updateScheduler();
    }

    private void updateScheduler() {

        listQrtzTriggerses = qrtzTriggersBC.findAll();
        listQrtzJobDetails = qrtzJobDetailsBC.findAll();
        listSchedulerState = qrtzSchedulerStateBC.findAll();

        String name = listSchedulerState.get(0).getQrtzSchedulerStatePK().getSchedName();

        root = new DefaultMindmapNode(name, name, "FFCC00", false);

        for (QrtzSchedulerState object : listSchedulerState) {
            MindmapNode ips = new DefaultMindmapNode(object.getQrtzSchedulerStatePK().getInstanceName(), object.getQrtzSchedulerStatePK().getInstanceName() + " # Last Checkin -" + new Date(object.getLastCheckinTime()), "6e9ebf", true);
            root.addNode(ips);
        }
    }

    public List<QrtzSchedulerState> getListSchedulerState() {
        return listSchedulerState;
    }

    public List<QrtzJobDetails> getListQrtzJobDetails() {
        return listQrtzJobDetails;
    }

    public List<QrtzTriggers> getListQrtzTriggerses() {
        return listQrtzTriggerses;
    }

    public void send() {
        //EventBus eventBus = EventBusFactory.getDefault().eventBus();
        // eventBus.publish(CHANNEL, listQrtzSchedulerState());
    }

    public MindmapNode getRoot() {
        return root;
    }

    public MindmapNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void onNodeSelect(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();

        //populate if not already loaded
        if (node.getChildren().isEmpty()) {
            Object label = node.getLabel();
            // aqui busca o fired
            if (label.equals("NS(s)")) {
                for (int i = 0; i < 25; i++) {
                    node.addNode(new DefaultMindmapNode("ns" + i + ".google.com", "Namespace " + i + " of Google", "82c542", false));
                }
            } else if (label.equals("IPs")) {
                for (int i = 0; i < 18; i++) {
                    node.addNode(new DefaultMindmapNode("1.1.1." + i, "IP Number: 1.1.1." + i, "fce24f", false));
                }
            } else if (label.equals("Malware")) {
                for (int i = 0; i < 18; i++) {
                    String random = UUID.randomUUID().toString();
                    node.addNode(new DefaultMindmapNode("Malware-" + random, "Malicious Software: " + random, "3399ff", false));
                }
            }
        }
    }

    public void onNodeDblselect(SelectEvent event) {
        this.selectedNode = (MindmapNode) event.getObject();
    }
}
