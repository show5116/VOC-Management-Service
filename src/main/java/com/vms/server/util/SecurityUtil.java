package com.vms.server.util;

import com.vms.server.domain.entity.adm.AdmUserInfo;
import com.vms.server.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Enumeration;

public class SecurityUtil {

    public static AdmUserInfo getLoginUserInfo() {
        CustomUserDetails accountContext = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountContext.getMember();
    }

    public static String encryptSha512( String text ) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(text.getBytes());

        StringBuilder builder = new StringBuilder();

        for (byte b : md.digest()) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public static String getServerIp() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while(interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();

                byte[] hardwareAddress = ni.getHardwareAddress();

                boolean virtual = false;
                if(hardwareAddress != null) {
                    if(hardwareAddress[0] != -44)
                        virtual = true;
                }
                if(virtual) continue;

                for(InterfaceAddress addr : ni.getInterfaceAddresses()) {
                    InetAddress address = addr.getAddress();
                    if(address.isSiteLocalAddress()) {
                        return addr.getAddress().toString().replace("/", "");
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static String getClientIp() throws UnknownHostException {

        String ip;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        ip = request.getHeader("X-Forwarded-For");


        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-RealIP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if(ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) {
            InetAddress address = InetAddress.getLocalHost();
            //ip = address.getHostName()+"/"+address.getHostAddress();
            ip = address.getHostAddress();
        }

        return ip;
    }

    public static String getLocalMacAddress() {
        String result = "";
        InetAddress ip;

        try {
            ip = InetAddress.getLocalHost();

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();

//            System.out.println("host addr : " + ip.getHostAddress());
//            System.out.println("host name : " + ip.getHostName());
//            System.out.println("addr : " + ip.getAddress());
//            System.out.println("CanonicalHostName : "+ ip.getCanonicalHostName());

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            result = sb.toString();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String AESAlg = "AES/CBC/PKCS5Padding";
    private static final String iv = "siliconmitustpms"; // 16byte

    /**
     * @param text
     * key: 32byte (required!!!)
     */
    public static String encryptAES256(String text, String key) throws Exception {

        Cipher cipher = Cipher.getInstance(AESAlg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     *
     * @param cipherText
     * *** encrypt using key ****
     */
    public static String decryptAES256(String cipherText, String key) throws Exception {
        if( key == null || key.trim().length() < 1 ){
            throw new Exception("유효하지않은 key 입니다.");
        }

        if( cipherText == null || cipherText.trim().length() < 1 ){
            throw new Exception("decode 문자가 없습니다.");
        }

        Cipher cipher = Cipher.getInstance(AESAlg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, StandardCharsets.UTF_8);
    }

}
