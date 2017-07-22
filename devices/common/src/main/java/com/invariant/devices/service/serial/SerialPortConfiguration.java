package com.invariant.devices.service.serial;

import jssc.SerialPort;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * 15.07.2017.
 */
public class SerialPortConfiguration {

    private String name;

    private int baudRate;

    private int dataBits;

    private int stopBits;

    private int parity;

    public String getName() {
        return name;
    }

    public int getBaudRate() {
        return baudRate;
    }

    public int getDataBits() {
        return dataBits;
    }

    public int getStopBits() {
        return stopBits;
    }

    public int getParity() {
        return parity;
    }


    public SerialPortConfiguration(String name) {
        this.name = name;
    }

    public static ConfigurationBuilder getBuilder(){
        return new ConfigurationBuilder();
    }

    public static class ConfigurationBuilder{

        private String name;

        private String baudRate;

        private String dataBits;

        private String stopBits;

        private String parity;

        public ConfigurationBuilder setName(String value) {
            this.name = value;
            return this;
        }

        public ConfigurationBuilder setBaudRate(String value) {
            this.baudRate = value;
            return this;
        }

        public ConfigurationBuilder setDataBits(String value) {
            this.dataBits = value;
            return this;
        }

        public ConfigurationBuilder setStopBits(String value) {
            this.stopBits = value;
            return this;
        }

        public ConfigurationBuilder setParity(String value) {
            this.parity = value;
            return this;
        }

        public SerialPortConfiguration build(){
            SerialPortConfiguration configuration = new SerialPortConfiguration(name);

            int baudRate = NumberUtils.toInt(this.baudRate, SerialPort.BAUDRATE_9600);
            configuration.baudRate = baudRate;

            int dataBits = NumberUtils.toInt(this.dataBits, SerialPort.DATABITS_8);
            configuration.dataBits = dataBits;

            int stopBits = NumberUtils.toInt(this.stopBits, SerialPort.STOPBITS_1);
            configuration.stopBits = stopBits;

            int parity = NumberUtils.toInt(this.parity, SerialPort.PARITY_NONE);
            configuration.parity = parity;

            return configuration;
        }
    }
}
