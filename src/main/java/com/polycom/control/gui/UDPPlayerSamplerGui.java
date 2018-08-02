package com.polycom.control.gui;

import com.polycom.sampler.UDPPlayerSampler;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class UDPPlayerSamplerGui extends AbstractSamplerGui{
    private static final Logger log = LoggerFactory.getLogger(UDPPlayerSamplerGui.class);
    private UDPPlayerPanel udpPlayerConfigGui = new UDPPlayerPanel();

    public UDPPlayerSamplerGui() {
        super();

        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());

        this.add(makeTitlePanel(), BorderLayout.NORTH);
        this.add(udpPlayerConfigGui, BorderLayout.CENTER);
    }

    public String getStaticLabel() {
        return "UDP Player";
    }

    /*
    * configure the UI layer  the value from the TestElement(sampler)
    *
    **/
    @Override
    public void configure(TestElement e) {
        super.configure(e);
        UDPPlayerSampler sampler = (UDPPlayerSampler)e;
        udpPlayerConfigGui.setIp(sampler.getIp());
        udpPlayerConfigGui.setPort(sampler.getPort());
        udpPlayerConfigGui.setLoop(sampler.getLoop());
        udpPlayerConfigGui.setIgnoreResponseCheckBox(sampler.getIgnoreResponse());
        udpPlayerConfigGui.setRequestData(sampler.getRequestData());
    }
    /**
     * modify the test element(it means the sampler)
     */
    @Override
    public TestElement createTestElement() {
        UDPPlayerSampler sampler = new UDPPlayerSampler();
        modifyTestElement(sampler);
        return sampler;
    }

    @Override
    public String getLabelResource() {
        return "UDP Player";
    }

    @Override
    public void modifyTestElement(TestElement testElement) {
        this.configureTestElement(testElement);
        if(testElement instanceof UDPPlayerSampler) {
            UDPPlayerSampler sampler = (UDPPlayerSampler) testElement;
            sampler.setIp(udpPlayerConfigGui.getIp());
            sampler.setPort(udpPlayerConfigGui.getPort());
            sampler.setLoop(udpPlayerConfigGui.getLoop());
            sampler.setIgnoreResponse(udpPlayerConfigGui.getIgnoreResponse());
            sampler.setRequestData(udpPlayerConfigGui.getResquestData());
        }
    }

    public void clearGui() {
        super.clearGui();
        this.udpPlayerConfigGui.setIp("");
        this.udpPlayerConfigGui.setPort("");
        this.udpPlayerConfigGui.setLoop("");
        this.udpPlayerConfigGui.setIgnoreResponseCheckBox(true);
        this.udpPlayerConfigGui.setRequestData("");
    }
}
