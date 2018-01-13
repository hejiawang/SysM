package com.wang.sysm.shiro.helper;

import com.wang.sysm.model.UserInfo;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @auther HeJiawang
 * @date 2018/1/8
 */
public class PasswordHelper {

    private static final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private static final String algorithmName = "md5";
    private static final int hashIterations = 2;

    public static void encryptPassword(UserInfo user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash( algorithmName, user.getPassWord(), ByteSource.Util.bytes(user.getSalt()),
                hashIterations).toHex();
        user.setPassWord(newPassword);
    }
}
