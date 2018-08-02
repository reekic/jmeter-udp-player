package com.polycom.sampler;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPPlayerSampler extends AbstractSampler {
    private static final Logger log = LoggerFactory.getLogger(UDPPlayerSampler.class);

    private DatagramChannel channel;

    public UDPPlayerSampler(){
        super();
        setName("UDP Player Sampler");
    }

    public void setIp(String ip){
        this.setProperty("UDPPLAYER.IP", ip);
    }

    public void setPort(String port) {
        this.setProperty("UDPPLAYER.PORT", port);
    }

    public void setLoop(String loop) {
        this.setProperty("UDPPLAYER.LOOP", loop);
    }

    public void setIgnoreResponse(boolean ignoreResponse) {
        this.setProperty("UDPPLAYER.IGNORERESPONSE",ignoreResponse);
    }

    public void setRequestData(String data) {
        this.setProperty("UDPPLAYER.REQUESTDATA", data);
    }

    public String getIp() {

        return this.getPropertyAsString("UDPPLAYER.IP");
    }

    public String getPort() {
        return this.getPropertyAsString("UDPPLAYER.PORT");

    }

    public String getLoop() {

        return this.getPropertyAsString("UDPPLAYER.LOOP");
    }

    public boolean getIgnoreResponse() {
        return this.getPropertyAsBoolean("UDPPLAYER.IGNORERESPONSE");
    }

    public String getRequestData() {
        return this.getPropertyAsString("UDPPLAYER.REQUESTDATA");
    }

    @Override
    public SampleResult sample(Entry entry) {
        SampleResult sr = new SampleResult();
        log.info("IP:"+ getIp());
        log.info("port:"+ getPort());
        log.info("loop:" + getLoop());
        log.info("response:"+getIgnoreResponse());

        int loop = Integer.parseInt(getLoop());
        String data = getRequestData();

        try {
            channel = getChannel();
            BufferedReader br = new BufferedReader(new StringReader(data));
            String line = null;
            br.mark(data.length()+1);
            ByteBuffer sendBuff = ByteBuffer.allocateDirect(1500);
            while(loop-- > 0) {
                while ((line = br.readLine()) != null) {
                    channel.write(HexStringUDPDecoder.encode(line));
                    Thread.sleep(6);
                    sendBuff.clear();
                }
                br.reset();
            }
            channel.close();
        }catch (Exception e) {

        } finally {

        }
        return sr;
    }


    public DatagramChannel getChannel() throws IOException {
        DatagramChannel c;
        c = DatagramChannel.open();

        c.connect(new InetSocketAddress(getIp(), Integer.parseInt(getPort())));
        return c;
    }







}
