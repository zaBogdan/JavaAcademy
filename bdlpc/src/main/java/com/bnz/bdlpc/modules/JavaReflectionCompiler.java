package com.bnz.bdlpc.modules;

import com.bnz.shared.models.QuestionResponse;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class JavaReflectionCompiler implements ICompiler{
    private String response = "Successfully compiled function";
    public int compile(QuestionResponse questionResponse) {
        String absolutPath = System.getProperty("user.dir");
        File root = new File(absolutPath + "/bdlpc_java");
        File fileSource = new File(root, "test/"+questionResponse.getUserId()+"_"+questionResponse.getQuestionUID()+"/HelloWorld.java");
        fileSource.getParentFile().mkdirs();
        try {
            Files.writeString(fileSource.toPath(), questionResponse.getResponse());

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            compiler.run(null, null, null, fileSource.getPath());
            try(URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() })) {
                Class<?> cls = Class.forName("HelloWorld", true, classLoader);
                Object instance = cls.getConstructor().newInstance();
                System.out.println(instance);
            }
        } catch (Exception e) {
            System.err.println(e);
            response = e.toString();
            return 0;
        }

        return 10;
    }
    public String getResponse() {
        return response;
    }
}
