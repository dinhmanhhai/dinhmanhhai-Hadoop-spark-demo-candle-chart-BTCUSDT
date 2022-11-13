package io.saagie.example.hdfs;


import com.google.gson.Gson;
import io.saagie.example.hdfs.dto.TradeData;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory
            .getLogger(Main.class);

    public static void main(String[] args) {
        try {
//            // ===
//            readAndWriteToAnotherFile("demofile2.txt");
//
            // === read file by line
            String data = readFileAsString("demofile2.txt");
            List<String> list = readFileAsListString("demofile2.txt");
            Gson g = new Gson();
            List<TradeData> listObject = new ArrayList<>();
            for(String v : list){
                TradeData s = g.fromJson(v, TradeData.class);
                // === Hadoop
                writeAndSaveFile(s.toString());
                listObject.add(s);
            }
        } catch (Exception ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
    }

    private static void writeAndSaveFile(String writeData) throws Exception {
        //Get the filesystem - HDFS
        String path = "/data/hdfs/example/1st/";
        String fileName = "test.csv";
        FileSystem fs = setupFileSystem();

        //==== Create folder if not exists
        Path workingDir = fs.getWorkingDirectory();

        //Create a path
        Path newFolderPath = new Path(path);
        Path hdfswritepath = new Path(newFolderPath + "/" + fileName);

        if (!fs.exists(newFolderPath)) {
            // Create new Directory
            fs.mkdirs(newFolderPath);
        }
        if (!fs.exists(hdfswritepath)) {
            // Create new Directory
            fs.create(hdfswritepath, true);
            System.exit(1);
        }

        //Init output stream
        FSDataOutputStream outputStream = fs.append(hdfswritepath);

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        bufferedWriter.write(writeData);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    private static FileSystem setupFileSystem() throws IOException {
        //HDFS URI
        String hdfsuri = "hdfs://52.139.185.44:9000";

        // ====== Init HDFS File System Object
        Configuration conf = new Configuration();
        // Set FileSystem URI
        conf.set("fs.defaultFS", hdfsuri);
        // Because of Maven
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
        // Set HADOOP user
        System.setProperty("HADOOP_USER_NAME", "root");
        System.setProperty("hadoop.home.dir", "/");
        //Get the filesystem - HDFS
        return FileSystem.get(URI.create(hdfsuri), conf);
    }

    public static String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static List<String> readFileAsListString(String fileName) throws Exception {
        List<String> data = new ArrayList<>();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                data.add(line);
            }
            myReader.close();
            new FileOutputStream("demofile2.txt").close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return new ArrayList<>();
        }
        return data;
    }

    private static void readAndWriteToAnotherFile(String filename) throws IOException {
        File inputFile = new File(filename);
        File tempFile = new File("new" + filename);

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = "";
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine;
            writer.write(currentLine + System.getProperty("line.separator"));
            lineToRemove = trimmedLine;
        }
        writer.close();
        reader.close();
    }
}
