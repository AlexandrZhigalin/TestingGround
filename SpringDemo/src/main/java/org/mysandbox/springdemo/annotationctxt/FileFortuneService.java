package org.mysandbox.springdemo.annotationctxt;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class FileFortuneService implements FortuneService {

    private BufferedReader br;
    private List<String> fortunes = new ArrayList<>();

    public FileFortuneService() {
    }

    // Define init method
    @PostConstruct
    public void Init() throws IOException {

        System.out.println("\n[Init Hook] Bean initialization\n" + this.getClass().getName());

        File file = new File(getClass()
                .getClassLoader()
                .getResource("fortunes-data.txt")
                .getFile());

        //Check if the file exists
        if (file.exists()) {

            // Initialize array list
            fortunes = new ArrayList<>();

            // Read fortunes from the file
            try {

                br = new BufferedReader(
                        new FileReader(file));

                String tempLine;

                // Read every single line from the file
                while ((tempLine = br.readLine()) != null) {
                    fortunes.add(tempLine);
                }

            } catch (IOException ioe) {
                throw ioe;
            }

        }

    }

    // Define destroy method
    @PreDestroy
    public void Destroy() throws IOException {

        System.out.println("\n[Destroy Hook] Bean destruction\n" + this.getClass().getName());

        try {

            // Close the stream
            if (br != null) {
                br.close();
            }

        } catch (IOException ioe) {
            throw ioe;
        }


    }

    @Override
    public String getFortune() {

        if (!fortunes.isEmpty()) {
            int randomIndex = new Random().nextInt(fortunes.size());
            return fortunes.get(randomIndex);
        }

        return "Today is your lucky day!";
    }
}
