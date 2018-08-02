package com.polycom.control.gui;

import org.apache.jmeter.gui.util.HorizontalPanel;
import org.apache.jmeter.gui.util.VerticalPanel;
import javax.swing.*;
import java.awt.*;


public class UDPPlayerPanel extends JPanel {

    private JLabel serverLabel = new JLabel("Server: ");
    private JTextField serverField = new JTextField();

    private JLabel portLabel = new JLabel("Port: ");
    private JTextField portField = new JTextField();

    private JLabel loopLabel = new JLabel("Loop: ");
    private JTextField loopField = new JTextField();

    private JLabel ignoreResponseLabel = new JLabel("Ignore Response? ");
    private JCheckBox ignoreResponseCheckBox = new JCheckBox("", true);

    private JLabel requestDataLabel = new JLabel("Request Data: ");
    private JTextArea requestDataArea = new JTextArea();


    public UDPPlayerPanel() {
        setLayout(new BorderLayout(0, 15));
        //setBorder(makeBorder());
        //add(makeTitlePanel(), BorderLayout.NORTH);

        VerticalPanel mainPanel = new VerticalPanel();

        JPanel basicPanels = new HorizontalPanel();
        basicPanels.add(createServerPanel());
        basicPanels.add(createPortPanel());

        mainPanel.add(basicPanels);

        JPanel optionPanel = new HorizontalPanel();
        optionPanel.add(createLoopPanel());
        optionPanel.add(createIgnoreResponsePanel());

        mainPanel.add(optionPanel);

        mainPanel.add(createRequestDatePannel());

        add(mainPanel, BorderLayout.CENTER);
    }

    public String getIp() {
        return serverField.getText();
    }
    public void setIp(String ip) {
        serverField.setText(ip);
    }

    public String getPort() { return portField.getText(); }
    public void setPort(String port) {
        portField.setText(port);
    }

    public String getLoop() {
        return loopField.getText();
    }
    public void setLoop(String loop) {
        loopField.setText(loop);
    }

    public boolean getIgnoreResponse() {
        return ignoreResponseCheckBox.isSelected();
    }
    public void setIgnoreResponseCheckBox(boolean check) {
        ignoreResponseCheckBox.setSelected(check);
    }

    public String getResquestData() {return requestDataArea.getText();};
    public void setRequestData(String data) {requestDataArea.setText(data);}
    public int getRequestDataLineNum() {return requestDataArea.getLineCount();}

    private JPanel createServerPanel() {
        serverLabel.setLabelFor(serverField);
        JPanel serverPanel = new JPanel(new BorderLayout(5, 0));
        serverPanel.add(serverLabel, BorderLayout.WEST);
        serverPanel.add(serverField, BorderLayout.CENTER);
        return serverPanel;
    }

    private JPanel createPortPanel() {
        portLabel.setLabelFor(portField);
        JPanel portPanel = new JPanel(new BorderLayout(5, 0));
        portPanel.add(portLabel, BorderLayout.WEST);
        portPanel.add(portField, BorderLayout.CENTER);
        return portPanel;
    }

    private JPanel createLoopPanel() {
        loopLabel.setLabelFor(loopField);
        JPanel loopPanel = new JPanel(new BorderLayout(5, 0));
        loopPanel.add(loopLabel, BorderLayout.WEST);
        loopPanel.add(loopField, BorderLayout.CENTER);
        return loopPanel;
    }

    private JPanel createIgnoreResponsePanel() {
        ignoreResponseLabel.setLabelFor(ignoreResponseCheckBox);
        JPanel ignorePanel = new JPanel(new FlowLayout());
        ignorePanel.add(ignoreResponseLabel);
        ignorePanel.add(ignoreResponseCheckBox);
        return ignorePanel;
    }

    private JPanel createRequestDatePannel() {
        requestDataLabel.setLabelFor(requestDataArea);
        JPanel requestDataPanel = new JPanel(new BorderLayout(5, 0));
        requestDataPanel.add(requestDataLabel, BorderLayout.WEST);
        requestDataPanel.add(requestDataArea,BorderLayout.CENTER);
        return requestDataPanel;
    }

}
