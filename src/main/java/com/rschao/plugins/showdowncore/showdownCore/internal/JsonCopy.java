package com.rschao.plugins.showdowncore.showdownCore.internal;

import com.rschao.plugins.showdowncore.showdownCore.ShowdownCore;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class JsonCopy {

    public static File getJsonFileCopy(String name){
        ShowdownCore plugin = ShowdownCore.getPlugin(ShowdownCore.class);
        InputStream is = plugin.getResource("template.json");
        File file = new File(plugin.getDataFolder() + "/generated/", name);
        file.getParentFile().mkdirs();
        try{
            Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (FileNotFoundException e) {
            // handle exception here
        } catch (IOException e) {
            // handle exception here
        }
        return file;
    }
}
