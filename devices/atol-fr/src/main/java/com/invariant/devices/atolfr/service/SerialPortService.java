package com.invariant.devices.atolfr.service;

import com.invariant.devices.atolfr.service.serial.PortReader;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 * Created by agumenyuk on 12.07.2017.
 */
public class SerialPortService {

    /**
     * USB to COM
     */
    public static final String SERIAL_USB_0 = "ttyACM0";

    /**
     * USB to COM
     */
    public static final String SERIAL_USB_1 = "ttyACM1";

    /**
     * В отсутствие DI, инстанс
     */
    private static SerialPortService instance;

    public static final String PORT_NAME = "/dev/" + SERIAL_USB_1;

    public static SerialPortService getInstance() {
        if(instance == null){
            instance = new SerialPortService();
        }
        return instance;
    }

    public SerialPort getPort(){
        SerialPort serialPort = new SerialPort(PORT_NAME);
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            serialPort.addEventListener(new PortReader(serialPort), SerialPort.MASK_RXCHAR);
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
        return serialPort;
    }

    /**
     * Аналог dmesg | grep tty
     * @return
     */
    public String[] getPortNames(){
        return SerialPortList.getPortNames();
    }
}
