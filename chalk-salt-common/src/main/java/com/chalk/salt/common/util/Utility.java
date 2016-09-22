package com.chalk.salt.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class Utility.
 */
public class Utility {

    /**
     * Gets the client info.
     *
     * @param clientIpAddresses the client ip addresses
     * @return the client info
     * @throws UnknownHostException the unknown host exception
     */
    public static List<InetAddress> convertIpAddressesToInetAddresses(final List<String> clientIpAddresses) throws UnknownHostException {
        final List<InetAddress> inetAddresses = new ArrayList<InetAddress>(clientIpAddresses.size());
        for (final String clientIpAddress : clientIpAddresses) {
            final InetAddress inetAddress = InetAddress.getByName(clientIpAddress);
            inetAddresses.add(inetAddress);
        }
        return inetAddresses;
    }

    /**
     * Gets the comma seperated host name.
     *
     * @param clientIpAddresses the client ip addresses
     * @return the comma seperated host name
     * @throws UnknownHostException the unknown host exception
     */
    public static String getCommaSeperatedHostName(final List<String> clientIpAddresses) throws UnknownHostException {
        final List<InetAddress> inetAddresses = convertIpAddressesToInetAddresses(clientIpAddresses);
        final StringBuilder ipAddresses = new StringBuilder();

        for (int counter = 0; counter < inetAddresses.size(); counter++) {
            ipAddresses.append(inetAddresses.get(counter).getHostAddress());
            if (counter + 1 != inetAddresses.size()) {
                ipAddresses.append(",");
            }
        }
        return ipAddresses.toString();
    }

    /**
     * Convert date to string.
     *
     * @param format the format
     * @param date the date
     * @return the string
     */
    public static String convertDateToString(final String format, final Date date) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

}
