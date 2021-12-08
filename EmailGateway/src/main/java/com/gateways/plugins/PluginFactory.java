package com.gateways.plugins;

import java.io.InputStream;
import java.util.Properties;

public class PluginFactory {
    FileResourcesUtils fileResourcesUtils;

    private class FileResourcesUtils {
        private InputStream getFileFromResourceAsStream(String fileName) {

            // The class loader that loaded the class
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);

            // the stream holding the file content
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found! " + fileName);
            } else {
                return inputStream;
            }
        }
    }

    public Properties initialize() {
        Properties props = new Properties();
        String fileName = "sendVerification.properties";
        fileResourcesUtils = new FileResourcesUtils();

        try {
            InputStream input = fileResourcesUtils.getFileFromResourceAsStream(fileName);
            props.load(input);
            return props;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Object getPlugin(Class iface) {
        Properties props = this.initialize();
        String impName = props.getProperty(iface.getName());
        if (impName == null) {
            throw new RuntimeException("implementation not specified for " +
                    iface.getName() + " in PluginFactory properties");
        }
        try {
            return Class.forName(impName).newInstance();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException("factory unable to construct instance of " +
                    iface.getName());
        }
        return null;
    }
}




