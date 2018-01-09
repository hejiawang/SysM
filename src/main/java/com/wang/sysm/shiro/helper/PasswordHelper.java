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

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";
    private final int hashIterations = 2;

    public void encryptPassword(UserInfo user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash( algorithmName, user.getPassWord(), ByteSource.Util.bytes(user.getSalt()),
                hashIterations).toHex();
        user.setPassWord(newPassword);
    }
}
