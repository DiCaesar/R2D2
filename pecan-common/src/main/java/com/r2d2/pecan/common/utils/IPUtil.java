package com.r2d2.pecan.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 获取本地IP
 * <p>
 * 1、获取本地IP
 * </p>
 */
@Slf4j
public class IPUtil {

    private static volatile String IP_ADDRESS = "";

    /**
     * 获取本地IP
     *
     * @return IP地址
     */
    public static String getLocalIP() {

        if (StringUtils.isNotBlank(IP_ADDRESS)) {
            return IP_ADDRESS;
        }
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address) {
                        IP_ADDRESS = ip.getHostAddress();
                        return IP_ADDRESS;
                    }
                }
            }
        } catch (SocketException e) {
            log.error("获取本机IP Socket异常:{}", e);
        } catch (Exception e) {
            log.error("获取本机IP异常:{}", e);
        }
        return "127.0.0.1";
    }
}